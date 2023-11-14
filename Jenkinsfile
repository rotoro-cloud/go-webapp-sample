pipeline {
  agent any
  stages {
    stage('dev') {
      steps {
        echo 'ok'
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