pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        sh 'go test ./...'
      }
    }

  }
  tools {
    go 'go-1.19'
  }
  environment {
    GO111MODULE = 'on'
  }
}