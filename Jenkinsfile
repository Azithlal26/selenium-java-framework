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

                    bat """
                    call C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd clean test ^
                    -Dbrowser=${params.BROWSER} ^
                    -Denv=${params.ENV} ^
                    -Dheadless=${params.HEADLESS}
                    """
                }
            }
        }

        stage('SonarQube Analysis') {

            steps {

                withSonarQubeEnv('SonarQube') {

                      bat """
                        call C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd clean verify sonar:sonar ^
                        -DskipTests=true ^
                        -Dsonar.projectKey=SJF ^
                        -Dsonar.projectName=SJF
                        """
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

                junit 'target/surefire-reports/*.xml'

                allure(
                    includeProperties: false,
                    jdk: '',
                    results: [[path: 'allure-results']]
                )

                publishHTML([
                            allowMissing: true,
                            alwaysLinkToLastBuild: true,
                            keepAll: true,
                            reportDir: 'reports',
                            reportFiles: 'ExtentReport.html',
                            reportName: 'Extent Report'
                        ])

                archiveArtifacts artifacts: 'reports/**/*.*', fingerprint: true

                archiveArtifacts artifacts: 'screenshots/**/*.*', fingerprint: true

                archiveArtifacts artifacts: 'logs/**/*.*', fingerprint: true
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
    }
}