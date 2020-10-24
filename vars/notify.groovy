def call() {
    success {
        script {
            if (currentBuild.getPreviousBuild()?.result != 'SUCCESS') {
                // Uncomment desired notification

                //slackSend (color: "#008800", message: "Build ${env.JOB_NAME} is back to normal.")
                //emailext (to: "qa@example.com", subject: "Build ${env.JOB_NAME} is back to normal.", body: "Build ${env.JOB_NAME} is back to normal.")
            }
        }
    }
    failure {
        // Uncomment desired notification
        // Section must not be empty, you can delete the sleep once you set notification
        sleep 1
        //slackSend (color: "#AA0000", message: "Build ${env.BUILD_NUMBER} of ${env.JOB_NAME} ${currentBuild.result} (<${env.BUILD_URL}|Open>)")
        //emailext (to: "qa@example.com", subject: "Build ${env.JOB_NAME} failed!", body: "Build ${env.BUILD_NUMBER} of ${env.JOB_NAME} ${currentBuild.result}\nSee ${env.BUILD_URL}")
    }
}
