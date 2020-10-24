def dummy_test() {
    echo "Dummy test..."
}

def unit_tests() {
    // cmakeBuild(
    //     buildType: "Release",
    //     buildDir: 'build-release-tests',
    //     sourceDir: '.',
    //     cleanBuild: true,
    //     cmakeArgs: '-DBUILD_TESTING=ON',
    //     installation: 'InSearchPath',
    //     steps: [
    //         [args: 'test', withCmake: true]
    //         [args: '--parallel $(($(nproc) + 1))', withCmake: true]
    //     ]
    // )
    sh '''
        rm -rf build-release-tests
        mkdir -p build-release-tests
        cmake -DCMAKE_BUILD_TYPE=Release \
            -DBUILD_TESTING=ON \
            -B build-release-tests
        cmake --build build-release-tests --parallel $(($(nproc) + 1))
        '''
}

def memtest() {
    // cmakeBuild(
    //     buildType: "Release",
    //     buildDir: 'build-release-tests',
    //     sourceDir: '.',
    //     cleanBuild: true,
    //     cmakeArgs: '-DBUILD_TESTING=ON',
    //     installation: 'InSearchPath',
    //     steps: [
    //         [args: 'test memcheck', withCmake: true]
    //         [args: '--parallel $(($(nproc) + 1))', withCmake: true]
    //     ]
    // )
    sh '''
        rm -rf build-release-tests
        mkdir -p build-release-tests
        cmake -DCMAKE_BUILD_TYPE=Release \
            -DBUILD_TESTING=ON \
            -B build-release-tests
        cmake --build build-release-tests --parallel $(($(nproc) + 1))
        '''
}
