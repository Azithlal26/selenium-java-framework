pipeline {

    agent any

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
                    bat '"C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd" clean test'
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
            emailText(
                    subject: "SUCCESS : ${env.JOB_NAME}",
                    body: "Build Passed",
                    to: "azithlaltsthorali@gmail.com"
                )
        }

        failure {
            emailText(
                    subject: "FAILED : ${env.JOB_NAME}",
                    body: "Build Passed",
                    to: "azithlaltsthorali@gmail.com"
                            )
        }
    }
}