def call(String comment = '', String email_recipients = '', String buildStatus = 'STARTED') {
    //Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'
    
    echo comment
    echo email_recipients
    echo buildStatus
    
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
    
    if (params.SLACK_NOTIFICATION == true) {
        //slackSend(color: color, message: msg)
        echo "notify slack works"
    }
    if (email_recipients =! '') {
        //notifyMail(email_recipients, msg)
        echo email_recipients
    }
}
