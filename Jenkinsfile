pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        sh 'go test ./...'
      }
    }

  }
  environment {
    GO111MODULE = 'on'
  }
}