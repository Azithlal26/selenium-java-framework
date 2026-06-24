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
                bat '"C:\\Tools\\apache-maven-3.9.16\\bin\\mvn.cmd" clean test'
            }
        }
    }

    post {
        success {
            echo 'Build Successful'
        }

        failure {
            echo 'Build Failed'
        }
    }
}