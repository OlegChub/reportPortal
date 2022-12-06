pipeline {
    agent any
    tools {
        maven 'Maven'
    }
    triggers {pollSCM('*****')}
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
                withCredentials([usernamePassword(credentialsId: 'reportportal_creds', usernameVariable: 'user', passwordVariable: 'pass')]) {
                sh "mvn -DuserName=$user -Dpassword=$pass test"
                }
            }
        }

        stage("Deploy") {
            steps {
                echo "Deploying the app ..."
            }
        }

    }
    post{
        always {
                    archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true
                    allure includeProperties: false, jdk: '',
                    results: [[path: 'target/allure-results']]
                }
    }
}