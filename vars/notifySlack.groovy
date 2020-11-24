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
    
    def RESULT_GOOD = "ANALYSIS: `${env.JOB_NAME}` #${env.BUILD_NUMBER}: All good!"
    def RESULT_BAD = "ANALYSIS: `${env.JOB_NAME}` #${env.BUILD_NUMBER}: Not good at all!"
    def DEPLOY = "DEPLOY: `${env.JOB_NAME}` #${env.BUILD_NUMBER}: Prepared for modification"

    slackSend(color: color, message: msg)
    
    
}
