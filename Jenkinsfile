pipeline {
  agent any
  environment {
    GO111MODULE = 'on'
    GOROOT='/usr/local/go'
  }
  stages {
    stage('test') {
      steps {
        sh 'go test ./...'
      }
    }
  }

}
