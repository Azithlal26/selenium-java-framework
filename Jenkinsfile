pipeline {

//     triggers {
//             cron('H/30 * * * *')
//     }

    options {
        buildDiscarder(logRotator(
            numToKeepStr: '20',
            artifactNumToKeepStr: '10'
        ))
    }

    agent any

    tools {
    jdk 'JDK21'
    }


    parameters {

            choice(
                name: 'BROWSER',
                choices: ['chrome', 'firefox', 'edge'],
                description: 'Select Browser'
            )

            choice(
                name: 'ENV',
                choices: ['qa', 'uat', 'stage'],
                description: 'Select Environment'
            )

            booleanParam(
                name: 'HEADLESS',
                defaultValue: false,
                description: 'Run in Headless Mode'
            )
        }

    stages {

        stage('Checkout') {
            steps {
                git branch: 'main',
                    url: 'https://github.com/Azithlal26/selenium-java-framework.git'
            }
        }

        stage('Build & Test') {

            steps {

                script {
                                currentBuild.displayName =
                                    "#${BUILD_NUMBER} ${params.BROWSER} ${params.ENV}"
                            }

                withEnv([
                    'JAVA_HOME=C:\\Users\\Azithlal\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot',
                    'PATH+JAVA=C:\\Users\\Azithlal\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot\\bin'
                ]) {

                    bat 'java -version'

                    bat '''
                    if exist allure-results rmdir /S /Q allure-results
                    if exist allure-report rmdir /S /Q allure-report
                    '''

                    bat """
                    call C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd clean verify ^
                    -Dbrowser=${params.BROWSER} ^
                    -Denv=${params.ENV} ^
                    -Dheadless=${params.HEADLESS}
                    """
                }
            }
        }

        stage('Debug After Tests') {
            steps {
                script {
                    echo "After Tests = ${currentBuild.currentResult}"
                }
            }
        }

        stage('Code Coverage') {
            steps {
                jacoco(
                    execPattern: 'target/jacoco.exec',
                    classPattern: 'target/classes',
                    sourcePattern: 'src/main/java'
                )
            }
        }

        stage('Verify Reports') {
            steps {
                bat 'dir target\\surefire-reports'
            }
        }

        stage('SonarQube Analysis') {

            steps {

                withSonarQubeEnv('SonarQube') {

                      bat """
                        call C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd ^
                         org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121:sonar ^
                        -DskipTests ^
                        -Dsonar.projectKey=SJF ^
                        -Dsonar.projectName=SJF
                        """
                    }
                }
            }

            stage('Debug After Sonar') {
                steps {
                    script {
                        echo "After Sonar = ${currentBuild.currentResult}"
                    }
                }
            }

        stage('Quality Gate') {

            steps {

                timeout(time: 5, unit: 'MINUTES') {

                     waitForQualityGate abortPipeline: true
                }
            }
        }
    }

    post {

        always {

                script {
                    echo "Before JUnit = ${currentBuild.currentResult}"
                }

                junit(
                    testResults: 'target/surefire-reports/*.xml',
                    allowEmptyResults: false,
                    skipPublishingChecks: true
                )

                script {
                    echo "After JUnit = ${currentBuild.currentResult}"
                }

                allure(
                    includeProperties: false,
                    jdk: '',
                    results: [
                            [path: 'allure-results']
                        ]
                )

                script {
                    echo "After Allure = ${currentBuild.currentResult}"
                }

                publishHTML([
                            allowMissing: true,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'reports',
                            reportFiles: 'ExtentReport.html',
                            reportName: 'Extent Report'
                        ])

                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/site/jacoco',
                    reportFiles: 'index.html',
                    reportName: 'JaCoCo Coverage'
                ])

                script {
                    echo "After HTML = ${currentBuild.currentResult}"
                }

                archiveArtifacts(
                    artifacts: 'reports/**/*.*,screenshots/**/*.*,logs/**/*.*',
                    fingerprint: true,
                    allowEmptyArchive: true
                )

                script {
                    echo "After Archive = ${currentBuild.currentResult}"
                }
            }

        success {
            emailext(
                subject: "SUCCESS : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                Build Passed

                Job: ${env.JOB_NAME}
                Build: ${env.BUILD_NUMBER}

                URL: ${env.BUILD_URL}
                """,
                to: "azithlaltsthorali@gmail.com"
            )
        }

        failure {
            emailext(
                subject: "FAILED : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: """
                Build Failed

                Job: ${env.JOB_NAME}
                Build: ${env.BUILD_NUMBER}

                URL: ${env.BUILD_URL}
                """,
                to: "azithlaltsthorali@gmail.com"
            )
        }

        unstable {
             emailext(
                  subject: "UNSTABLE : ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                  body: """
                  Build is UNSTABLE

                  Job: ${env.JOB_NAME}
                  Build: ${env.BUILD_NUMBER}

                  URL: ${env.BUILD_URL}
                  """,
                  to: "azithlaltsthorali@gmail.com"
             )
        }
    }
}