pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    stages {

        stage("Build") {
            steps {
                echo "Building the app ..."
                sh 'mvn test-compile'
            }
        }

        stage("Test") {
            steps {
                echo "Testing the app ..."
            }
        }

        stage("Deploy") {
            steps {
                echo "Deploying the app ..."
            }
        }
    }
}