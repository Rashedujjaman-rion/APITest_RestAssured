pipeline {
    agent any

    tools {
        jdk 'JDK_11'  // Use the JDK configured in Jenkins
        maven 'Maven_3.9.4'  // Use Maven configured in Jenkins
    }

    environment {
        MAVEN_HOME = 'C:\\java\\apache-maven-3.9.4'
        PATH = "${MAVEN_HOME}\\bin;${env.PATH}"
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Rashedujjaman-rion/APITest_RestAssured.git'
            }
        }

        stage('Install Dependencies') {
            steps {
                bat 'mvn clean install -DskipTests'
            }
        }

        stage('Run API Tests') {
            steps {
                echo "Running tests with Maven"
                bat 'mvn test'  // Ensure that this command is executing correctly
            }
        }

       stage('Publish Reports') {
                   post {
                       always {
                           echo "Publishing TestNG and JUnit reports"
                           junit '**/target/surefire-reports/*.xml'  // Publish JUnit test results
                           allure includeProperties: false, results: [[path: './allure-results']]  // Publish Allure report
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
