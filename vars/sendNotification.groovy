def call() {
  
    if (params.SLACK_NOTIFICATION == true) {
        notifySlack()
    }
    if (params.EMAIL_NOTIFICATION == true) {
        notifyMail()
    }
    if (params.SMS_NOTIFICATION == true) {
        notifySMS()
    }    
}

def msg() {
    if ("${params.MSG}" == ""){
        msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"
    }
    else{
        msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}\n message: ${params.MSG}"
    }
}

def sendMessage() {
  if (params.SLACK_NOTIFICATION == true) {
      slackSend(message: msg)
  }
  if (params.EMAIL_NOTIFICATION == true) {
      mailSend(message: msg)
  }
  if (params.SMS_NOTIFICATION == true) {
      smsSend(message: msg)
  }    
}


def notifyMail(String email_recipients) {
    echo "send mail works"
    def mail_list = ["${params.EMAIL_RECEPIENT_1}", "${params.EMAIL_RECEPIENT_2}"]
    echo "Mail will be send to '${mail_list}'"

    // def status, logRegex
    //
    // switch (currentBuild.currentResult) {
    //     case 'SUCCESS':
    //         status = 'successed'
    //         logRegex = 'SUCCESS'
    //         break
    //
    //     case 'UNSTABLE':
    //         status = 'unstable'
    //         logRegex = 'FAILURE'
    //         break
    //
    //     case 'FAILURE':
    //         status = 'failed'
    //         logRegex = 'FAILURE'
    //         break
    //
    //     case 'ABORTED':
    //         status = 'canceled'
    //         logRegex = 'ABORTED'
    //         break
    // }
    // emailext(subject: "Build $status - ${JOB_NAME} #${BUILD_NUMBER} ",
    //     body: """ <p> Build $status on Job:
    //     <a style = "font-size:14px;text-decoration:underline;font-weight:bold" href="${BUILD_URL}/console">${
    //         JOB_NAME
    //     } - build# ${BUILD_NUMBER} </a></p>
    //     <p> <pre> \${BUILD_LOG_REGEX, regex = "^.*?$logRegex.*?\$", linesBefore = 25, linesAfter = 150, maxMatches = 10, showTruncatedLines = false, escapeHtml = false} </pre></p> """
    //     , mimeType: 'text/html'
    //     , from: '"Jenkins server" <foo@acme.org>'
    //     , to: "$recipients")
}
def notifySlack(String buildStatus = 'STARTED') {
    echo "notify slack works"
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

    //def msg = "${buildStatus}: `${env.JOB_NAME}` #${env.BUILD_NUMBER}:\n${env.BUILD_URL}"

    slackSend(color: color, message: msg)
}
def notifySMS(String sms_recipients){
    echo "send SMS works"
    def sms_list = ["${params.SMS_RECEPIENT_1}", "${SMS_RECEPIENT_2}"]
    echo "SMS will be send to '${sms_list}'"
}
