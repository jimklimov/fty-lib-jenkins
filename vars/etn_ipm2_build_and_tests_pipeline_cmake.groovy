def call() {

    agentLabel = "devel-image && x86_64"

    pipeline {
        agent {
            label agentLabel
        }

        stages {
            stage('Build & Analysis') {
                parallel {
                    stage('Release Build') {
                        stages {
                            stage('Build without tests') {
                                steps {
                                    script {
                                        build.cmake_release()
                                    }
                                }
                            }
                        }
                    }

                    stage('Debug Build') {
                        stages {
                            stage('Build with tests') {
                                steps {
                                    script {
                                        build.cmake_debug()
                                    }
                                }
                            }

                            stage('Tests') {
                                steps {
                                    script {
                                        test.dummy_test()
                                    }
                                }
                            }

                            stage('Memchecks') {
                                steps {
                                    script {
                                        test.dummy_test()
                                    }
                                }
                            }
                        }
                    }

                    stage('Analyse with Coverity') {
                        when {
                            beforeAgent true
                            anyOf {
                                branch 'master'
                                branch "release/*"
                                changeRequest()
                            }
                        }
                        steps{
                            script {
                                coverity.analysis()
                            }
                        }
                        post {
                            always {
                                recordIssues (
                                    enabledForFailure: true,
                                    aggregatingResults: true,
                                    qualityGates: [[threshold: 1, type: 'DELTA_ERROR', fail: true]],
                                    tools: [issues(name: "Coverity Analysis",pattern: '**/tmp_cov_dir/output/*.errors.json')]
                                )
                            }
                        }
                    }
                }
            }

            stage ('deploy if appropriate') {
                steps {
                    script {
                        deploy()
                    }
                }
            }
        }
        post {
            success {
                script {
                    notify.success()
                }
            }
            failure {
                script {
                    notify.failure()
                }
            }
        }

    }


}
