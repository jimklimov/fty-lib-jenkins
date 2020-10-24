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
                                    build.cmake_release()
                                }
                            }
                        }
                    }

                    stage('Debug Build') {
                        stages {
                            stage('Build with tests') {
                                steps {
                                    build.cmake_debug()
                                }
                            }

                            stage('Tests') {
                                steps {
                                    test.dummy_test()
                                }
                            }

                            stage('Memchecks') {
                                test.dummy_test()
                            }
                        }
                    }

                    stage('Analyse with Coverity') {
                        coverity.analysis()
                    }
                }
            }

            stage ('deploy if appropriate') {
                steps {
                    deploy()
                }
            }
        }
        post {
            notify()
        }

    }


}
