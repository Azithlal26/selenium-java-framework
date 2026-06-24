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

                archiveArtifacts artifacts: 'reports/**/*.*', fingerprint: true

                archiveArtifacts artifacts: 'screenshots/**/*.*', fingerprint: true

                archiveArtifacts artifacts: 'logs/**/*.*', fingerprint: true
            }

        success {
            echo 'Build Successful'
        }

        failure {
            echo 'Build Failed'
        }
    }
}