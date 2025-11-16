pipeline {
    agent any
    
    environment {
        DOCKER_REGISTRY = 'your-registry'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }
    
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }
        
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        
        stage('Build Docker Images') {
            steps {
                script {
                    def services = ['discovery-service', 'config-service', 'gateway-service', 
                                    'beneficiaire-service', 'virement-service', 'chatbot-service']
                    for (service in services) {
                        sh "docker build -t ${DOCKER_REGISTRY}/${service}:${IMAGE_TAG} -f ${service}/Dockerfile ."
                    }
                }
            }
        }
        
        stage('Push Docker Images') {
            steps {
                script {
                    def services = ['discovery-service', 'config-service', 'gateway-service', 
                                    'beneficiaire-service', 'virement-service', 'chatbot-service']
                    for (service in services) {
                        sh "docker push ${DOCKER_REGISTRY}/${service}:${IMAGE_TAG}"
                    }
                }
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f k8s/'
            }
        }
    }
    
    post {
        always {
            cleanWs()
        }
        success {
            echo 'Pipeline réussi!'
        }
        failure {
            echo 'Pipeline échoué!'
        }
    }
}

