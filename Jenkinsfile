pipeline {
    agent any

    tools {
        jdk 'JDK_17'  // Ensure this matches your JDK configuration in Jenkins
        maven 'Maven_3.9.4'  // Ensure this matches your Maven configuration in Jenkins
    }

    environment {
        MAVEN_HOME = 'C:\\java\\apache-maven-3.9.4'
        PATH = "${MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Rashedujjaman-rion/APITest_RestAssured.git'
                script {
                    echo "Code checked out successfully!"
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                echo "Running Maven install"
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run API Tests') {
            steps {
                echo "Running tests with Maven"
                bat 'mvn test -Dtest=UserTests'  // Specify your test class here if needed
            }
        }

        stage('Publish TestNG & Allure Reports') {
            post {
                always {
                    echo "Publishing reports"
                    junit '**/target/surefire-reports/*.xml'  // TestNG Reports
                    allure includeProperties: false, results: [[path: './allure-results']]
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
