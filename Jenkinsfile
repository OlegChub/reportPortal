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
                withCredentials([usernamePassword(credentialsId: 'reportportal_creds', usernameVariable: 'user', passwordVariable: 'pass')]) {
                    sh "mvn -DuserName=$user -Dpassword=$pass test"
                }
            }
        }
        post {
            always {
                archiveArtifacts artifacts: 'target/surefire-reports/*.xml', fingerprint: true
                allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
                    }
            }
    }
}