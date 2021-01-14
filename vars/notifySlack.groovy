
def call(String buildStatus = 'STARTED') {
    // Build status of null means success.
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

    def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
  
    def slack
    if ("${params.SLACK_NOTIFICATION}" == true) {
        slackSend(color: color, message: msg)
        echo "Slack notification sended"
    }
    def email
    if ("${params.EMAIL_NOTIFICATION}" == true) {
        echo "Email sended"
    }
    def sms
    if ("${params.SMS_NOTIFICATION}" == true) {
        echo "SMS sended"
       
    Send(slack: true, email: true, sms: true)
}
