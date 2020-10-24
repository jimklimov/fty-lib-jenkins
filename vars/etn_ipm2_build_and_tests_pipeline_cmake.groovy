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
                        steps{
                            script {
                                coverity.analysis()
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
            script {
                notify()
            }
        }

    }


}
