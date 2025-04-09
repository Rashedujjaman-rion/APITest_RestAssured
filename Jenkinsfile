pipeline {
    agent any

   environment {
       MAVEN_HOME = 'C:\\java\\apache-maven-3.9.4'
       PATH = "${MAVEN_HOME}\\bin;${env.PATH}"
   }


    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Rashedujjaman-rion/APITest_RestAssured.git' // Update with your repo
            }
        }

        stage('Install Dependencies') {
            steps {
                sh 'mvn clean install -DskipTests'
            }
        }

        stage('Run API Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Publish TestNG & Allure Reports') {
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'  // TestNG Reports
                    allure includeProperties: false, results: [[path: '/allure-results']]
                }
            }
        }
    }

    post {
        success {
            echo '✅ API Tests Passed!'
        }
        failure {
            echo '❌ Tests Failed! Check the reports.'
        }
    }
}
