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
                sh "mkdir -p ${env.WORKSPACE}/target/allure-results"
                }
            }
        }

        stage("Deploy") {
            steps {
                echo "Deploying the app ..."
            }
        }
        stage('ALLURE') {
                    steps {
                        sh """
                        ls -a ${WORKSPACE}
                        """
                        script {
                            ws("${WORKSPACE}/portal-pipeline_jenkins_ci_setup/target/"){
                                allure([
                                    includeProperties: false,
                                    jdk: '',
                                    properties: [],
                                    reportBuildPolicy: 'ALWAYS',
                                    results: [[path: "allure-results"]]
                                ])
                            }
                        }
                    }
                }
    }
//     post{
//         always{
//             allure([
//                 includeProperties: false, jdk: '', results: [[path: "${env.WORKSPACE}/target/allure-results"]]
//                 ])
//         }
//     }
}