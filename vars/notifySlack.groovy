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
    
    //def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
    
    slackSend(color: color, message: msg)
}
def call(String message = msg) {
    
    def msg
    
    if (slackSend == '') {
        msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}
    } else if (slackSend == 'RESULT_GOOD') {
        msg = "ANALYSIS: `${env.JOB_NAME}` #${env.BUILD_NUMBER}: All good!"
    } else if (slackSend == 'RESULT_GOOD') {
        msg = "ANALYSIS: `${env.JOB_NAME}` #${env.BUILD_NUMBER}: Not good at all!"
    } else if (slackSend == 'RESULT_GOOD') {
        msg = "DEPLOY: `${env.JOB_NAME}` #${env.BUILD_NUMBER}: Prepared for modification"
