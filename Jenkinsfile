pipeline {
    agent none
    environment {
        REPO_SCHEME = "http://"
        REPO_ADDRESS = "git-server:3000/max/web-app.git"
        REPO_URL = "${REPO_SCHEME}${REPO_ADDRESS}"
        IMAGE_NAME = "myprivateregistry.com/node-app"
    }
    stages {

        stage('Git') {
            agent any
            steps {
                echo "Bring the Code"
                git([url: "${REPO_URL}", branch: "main", credentialsId: "gitea-max"])
                script {
                    env.TAG = sh(script: "cat package.json | jq -r .version", returnStdout: true).trim()
                    env.COMMIT = sh(script: 'git rev-parse --short HEAD', returnStdout: true).trim()
                }
            }
        }
    }
}
