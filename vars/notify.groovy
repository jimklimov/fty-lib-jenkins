def success() {
    script {
        if (currentBuild.getPreviousBuild()?.result != 'SUCCESS') {
            // Uncomment desired notification
            //slackSend (color: "#008800", message: "Build ${env.JOB_NAME} is back to normal.")
            //emailext (to: "qa@example.com", subject: "Build ${env.JOB_NAME} is back to normal.", body: "Build ${env.JOB_NAME} is back to normal.")
        }
    }
}

def failure() {
    sleep 1
}
