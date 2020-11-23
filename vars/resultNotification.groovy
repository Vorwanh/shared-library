def call() {
//Sends Slack notification about the result of the build
    always {
        notifySlack(currentBuild.result)
    }
}
