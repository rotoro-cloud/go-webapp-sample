pipeline {
  agent any

  tools {
    go 'go-1.20'
  }
  
  stages {
    stage('test') {
      steps {
        git(url: 'https://github.com/rotoro-cloud/go-webapp-sample.git', branch: 'master')
        sh 'go test ./...'
      }
    }

  }
  environment {
    GO111MODULE = 'on'
  }
}
