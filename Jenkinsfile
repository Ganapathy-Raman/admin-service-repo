pipeline {

    agent any
 
    stages {

        stage('Checkout') {

            steps {

                git branch: 'master', url: 'https://github.com/Ganapathy-Raman/admin-service-repo.git'

            }

        }
 
        stage('Build') {

            steps {

                bat 'mvn clean install'

            }

        }
 
        stage('Test') {

            steps {

                bat 'mvn test'

            }

        }
 
        stage('Docker Build') {

            steps {

                script {

                    docker.build('tapz_admin_img', '-f Dockerfile .')

                }

            }

        }
 
        stage('Run Docker') {

            steps {

                bat 'docker run -d -p 5373:5373 --name admin_service tapz_admin_img'

            }

        }



    }

}
 
