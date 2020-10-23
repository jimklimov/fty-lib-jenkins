def test(String project = "Jenkins test") {
    pipeline {
        agent none

        stages {
            stage('Build') {
                steps {
                    echo "Building ${project} ..."
                }
            }
            stage('Test') {
                steps {
                    echo "Testing ${project} ..."
                }
            }
            stage('Deploy') {
                steps {
                    echo "Deploying ${project} ..."
                }
            }
        }
    }
}
