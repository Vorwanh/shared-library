def call(String buildStatus = 'STARTED', email_recipients = 'jenkins.OASIS@gmail.com',comment = 'I am changing my mind....') {
    //Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'

    def color
    if (buildStatus == 'STARTED') {
        color = '#87CEFA'
    } else if (buildStatus == 'SUCCESS') {
        color = '#BDFFC3'
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFFE89'
    } else {
        color = '#FF9FA1'
    }

    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}\n${comment}"
    echo msg
    
    if (params.SLACK_NOTIFICATION == true) {
        //slackSend(color: color, message: msg)
        echo "notify slack works"
    }
    if (params.EMAIL_NOTIFICATION == true) {
        notifyMail(email_recipients: "jan.OASIS@gmail.com", message: msg)
    }
}
