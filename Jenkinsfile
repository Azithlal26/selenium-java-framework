pipeline {

    agent any

    parameters {

            choice(
                name: 'BROWSER',
                choices: ['chrome', 'firefox'],
                description: 'Select Browser'
            )

            choice(
                name: 'ENV',
                choices: ['qa', 'uat'],
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
                withEnv([
                    'JAVA_HOME=C:\\Users\\Azithlal\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot',
                    'PATH+JAVA=C:\\Users\\Azithlal\\AppData\\Local\\Programs\\Eclipse Adoptium\\jdk-21.0.11.10-hotspot\\bin'
                ]) {

                    bat 'java -version'

                    bat """
                    call C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd" clean test ^
                    -Dbrowser=${params.BROWSER} ^
                    -Denv=${params.ENV} ^
                    -Dheadless=${params.HEADLESS}
                    """
                }
            }
        }
    }

    post {

        always {

                junit 'target/surefire-reports/*.xml'

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