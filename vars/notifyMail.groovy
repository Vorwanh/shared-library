def call(String email_recipients) {
    echo "send mail works"
    def mail_list = ["${params.EMAIL_RECEPIENT_1}", "${params.EMAIL_RECEPIENT_2}"]
    echo "Mail will be send to '${mail_list}'"

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
    //
        case 'ABORTED':
            status = 'canceled'
            logRegex = 'ABORTED'
            break
    }
    emailext(subject: "Build $status - ${JOB_NAME} #${BUILD_NUMBER} ",
        body: """ <p> Build $status on Job:
        <a style = "font-size:14px;text-decoration:underline;font-weight:bold" href="${BUILD_URL}/console">${
            JOB_NAME
        } - build# ${BUILD_NUMBER} </a></p>
        <p> <pre> \${BUILD_LOG_REGEX, regex = "^.*?$logRegex.*?\$", linesBefore = 25, linesAfter = 150, maxMatches = 10, showTruncatedLines = false, escapeHtml = false} </pre></p> """
        , mimeType: 'text/html'
        , from: '"Jenkins server" <jenkins.oasis@gmail.com>'
        , to: "$email_recipients")
}