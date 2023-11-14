pipeline {
  agent any
  stages {
    stage('test') {
      steps {
        git(url: 'https://github.com/rotoro-cloud/go-webapp-sample.git', branch: 'master')
        sh 'go test ./...'
      }
    }

    stage('build') {
      steps {
        echo 'ok'
      }
    }

  }
  tools {
    go 'go-1.20'
  }
}