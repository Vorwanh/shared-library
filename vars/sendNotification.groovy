def call(String buildStatus = 'STARTED', email_recipients= '${EMAIL_RECEPIENT_1}', message= "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}\n${params.MSG}") {
    //Build status of null means success.
    buildStatus = buildStatus ?: 'SUCCESS'
    
    if email_recipients = "" {
        params.EMAIL_NOTIFICATION == false
    }

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
    
    if (params.SLACK_NOTIFICATION == true) {
        slackSend(color: color, message)
        echo "notify slack works"
    }
    if (params.EMAIL_NOTIFICATION == true) {
        notifyMail(email_recipients, message)
    }
    //if (params.SMS_NOTIFICATION == true) {
        //notifySMS()
    //} 
}
