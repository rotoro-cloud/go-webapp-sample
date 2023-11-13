pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        git(url: 'https://github.com/rotoro-cloud/go-webapp-sample.git', branch: 'master')
        sh 'go test ./...'
      }
    }

  }
  tools {
    go 'go-1.20'
  }
}