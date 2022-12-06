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

        stage("Deploy") {
            steps {
                echo "Deploying the app ..."
            }
        }

    }
    post{
        always{
            allure([
                   includeProperties: false,
                   jdk: '',
                   properties: [[key: 'allure.tests.management.pattern', value: 'http://tms.company.com/%s'],
                   [key: 'allure.issues.tracker.pattern', value: 'http://bagtracker.company.com/%s']],
                   results: [[path: '**/target/allure-results']]
//                 includeProperties: false, jdk: '', results: [[path: "**/target/allure-results"]]
                ])
        }
    }
}