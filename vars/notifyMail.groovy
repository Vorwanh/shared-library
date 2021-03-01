def call(String email_recipients = 'jenkins.OASIS@gmail.com', String message = 'Fear leads to anger. Anger leads to hate. Hate leads to suffering.') {
    echo "send mail started"

    echo email_recipients
    echo message
    def status, logRegex

    switch (currentBuild.currentResult) {
        case 'SUCCESS':
            status = 'successed'
            logRegex = 'SUCCESS'
            break

        case 'UNSTABLE':
            status = 'unstable'
            logRegex = 'FAILURE'
            break

        case 'FAILURE':
            status = 'failed'
            logRegex = 'FAILURE'
            break
    
        case 'ABORTED':
            status = 'canceled'
            logRegex = 'ABORTED'
            break
    }
    emailext(subject: "Build $status - ${JOB_NAME} #${BUILD_NUMBER} ",
        body: """ <p> Hey mate! </p> <p> I just wanned to inform you about 2 things. </p> <p> First is that the build <b> ${JOB_NAME} #${BUILD_NUMBER} </b> has <b> $status </b>.</p> <p>You can find more information about the job under the following link:
        <i><a style = "font-size:14px;text-decoration:underline;font-weight:bold" href="${BUILD_URL}/console">${
            JOB_NAME
        } - build# ${BUILD_NUMBER} </a> </i> </p> <p> Second one is that the shared libraries for notifications works well! Yuhuuuu! </p> <p> And one more thing...! </p> <p> <b> You are awesome! </b> </p>
        <p> <pre> \${BUILD_LOG_REGEX, regex = "^.*?$logRegex.*?\$", linesBefore = 25, linesAfter = 150, maxMatches = 10, showTruncatedLines = false, escapeHtml = false} </pre></p> """
        , mimeType: 'text/html'
        , from: '"Jenkins server" <jenkins.oasis@gmail.com>'
        , to: "$email_recipients")
}
