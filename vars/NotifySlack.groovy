
#!/usr/bin/env groovy

def call(String buildStatus) {
  if ( buildStatus == "STARTED" ) {
    slackSend color: "#87CEFA", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER}: ${env.BUILD_URL} started"
  }
  else if( buildStatus == "SUCCESS" ) {
    slackSend color: "#BDFFC3", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER}: ${env.BUILD_URL} was success"
  }
  else if( buildStatus == "UNSTABLE" ) {
    slackSend color: "#FFFE89", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER}: ${env.BUILD_URL} was unstable"
  }
  else {
    slackSend color: "#FF9FA1", message: "Job: ${env.JOB_NAME} with buildnumber ${env.BUILD_NUMBER}: ${env.BUILD_URL} is waiting for next step"
  }
}
