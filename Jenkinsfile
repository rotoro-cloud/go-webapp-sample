pipeline {
    agent none
    environment {
        REPO_URL = "http://git-server:3000/max/web-app.git"
        HOME = '.'
    }
    stages {
        stage('Git') {
          agent any
          steps {
            echo "Bring the Code"
            git([url: "${REPO_URL}", branch: "main", credentialsId: "gitea-max"])
          }
        }

        stage('Build') {
            agent {
                docker { 
                    image 'node:14-alpine'
                    reuseNode true
                }
            }
            steps {
                echo "Get NodeJS Dependencies"
                sh 'npm install'
            }
        }
        stage('Prepare') {
            agent any
            steps {
                echo "Before Tests"
                sh 'mkdir artifacts || true'
                sh 'mkdir .dependency-check || true'
            }
        }

        stage('Lint') {
            agent {
                docker { 
                    image 'node:14-alpine'
                    reuseNode true
                }
            }
            steps {
                echo "Check Syntax"
                sh 'npm run lint'
            }
        }
        
        stage('Audit'){
            agent {
                docker { 
                    image 'owasp/dependency-check-action' 
                    args '-u root:root -v cve:/usr/share/dependency-check/data --entrypoint=""'
                    reuseNode true
                }
            }
            steps {
                echo "Check Dependencies"
                sh "/usr/share/dependency-check/bin/dependency-check.sh -o artifacts/dependency-check-report.html -s ./node_modules"
            }
            
            post {
                success {
			           sh "chmod 777 artifacts/dependency-check-report.html"
                }
            }
        }
            
        stage('Test') {
            agent {
                docker { 
                    image 'node:14-alpine'
                    reuseNode true
                }
            }
            steps {
                echo "App Unit Tests"
                sh 'npm test'
            }
        }

        stage('Version') {
            agent {
                docker { 
                    image 'node:14-alpine'
                    reuseNode true
                }
            }
            steps {
                echo "Increment App Version"
                sh 'npm version patch -m "Integrated with Jenkins version %s" --force'
            }
        }
        
        stage('Artifact') {
            steps {
                echo "Build Docker Image"
                script {
                    app = docker.build("myprivateregistry.com/node-app")
                }
            }
        }
        
        stage('Dev') {
            environment {
                 APP_PORT = '3500'
            }
            steps {
                echo "Development Deploy"
                script {
                    docker.image('myprivateregistry.com/node-app').withRun('-p 30080:${APP_PORT} -e APP_PORT=${APP_PORT} ') { c ->
                        sh "docker logs ${c.id}"
                        sh "docker exec ${c.id} env"
                        sh "while ! curl localhost:30080; do sleep 2; done"
                        sh "curl localhost:30080/api/v1"
                    } 
                }
            }
        }
    }
}
