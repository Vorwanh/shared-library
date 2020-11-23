def call() {
//Sends Slack notification about the result of the build
    post {
        always {
            notifySlack(currentBuild.result)
        }
    }
}
