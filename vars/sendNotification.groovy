def call(comment = 'Fear is the path to the dark side.', email_recipients = 'jenkins.OASIS@gmail.com', buildStatus = 'STARTED') {
    //Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'

    echo email_recipients
    echo comment
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

    def message = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}\n${comment}"
    
    if (params.SLACK_NOTIFICATION == true) {
        //slackSend(color: color, message: msg)
        echo "notify slack works"
    }
    if (params.EMAIL_NOTIFICATION == true) {
        notifyMail(email_recipients, message)
        echo email_recipients
    }
}
