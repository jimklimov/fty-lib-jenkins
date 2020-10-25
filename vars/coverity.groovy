def analysis() {
    stages {
        stage('Compile') {
            steps {
                sh '''
                    rm -rf build_coverity
                    mkdir -p build_coverity
                    cmake -DCMAKE_BUILD_TYPE=Release \
                        -B $PWD/build_coverity
                    coverity.sh --build $PWD/build_coverity
                    '''
            }
        }
        stage('Analyse') {
            steps {
                sh '''
                    coverity.sh --analyse $PWD/build_coverity
                    '''
                sh '''
                    coverity-warning-parser.py $PWD/build_coverity $PWD
                    '''
            }
        }
        stage('Commit') {
            when {
                beforeAgent true
                anyOf {
                    branch 'master'
                    branch 'release/*'
                }
            }
            steps {
                sh '''
                    COV_GIT_URL=$(git remote -v | egrep '^origin' | awk '{print $2}' | head -1)
                    COV_GIT_PROJECT_NAME=$(basename ${COV_GIT_URL} | sed 's#.git##g')
                    COV_GIT_BRANCH=$(echo ${BRANCH_NAME} | sed 's#/#_#g')
                    COV_GIT_COMMIT_ID=$(git rev-parse --short HEAD)
                    coverity.sh --commit $PWD/build_coverity "${COV_GIT_PROJECT_NAME}" "${COV_GIT_BRANCH}" "${COV_GIT_COMMIT_ID}"
                '''
            }
        }
    }
}
