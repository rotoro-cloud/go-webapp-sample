def call(String projectName, String hubUser) {
    withCredentials([usernamePassword(
        credentialsId: "docker_cred",
        usernameVariable: "USER",
        passwordVariable: "PASS"
        )]) {
            sh "docker login -u '$USER' -p '$PASS' || true"
        }
    sh "docker pull ${hubUser}/${projectName}:latest"
}
