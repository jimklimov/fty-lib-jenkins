def cmake_release() {
    // cmakeBuild(
    //     buildType: "Release",
    //     buildDir: 'build-release',
    //     sourceDir: '.',
    //     cleanBuild: true,
    //     cmakeArgs: '-DBUILD_TESTING=OFF',
    //     installation: 'InSearchPath',
    //     steps: [
    //         [args: '--parallel $(($(nproc) + 1))', withCmake: true]
    //     ]
    // )
    sh '''
        rm -rf build-release
        mkdir -p build-release
        cmake -DCMAKE_BUILD_TYPE=Release \
            -DBUILD_TESTING=OFF \
            -B build-release
        cmake --build build-release --parallel $(($(nproc) + 1))
        '''
}

def cmake_debug() {
    // cmakeBuild(
    //     buildType: "Debug",
    //     buildDir: 'build-debug',
    //     sourceDir: '.',
    //     cleanBuild: true,
    //     cmakeArgs: '-DBUILD_TESTING=ON',
    //     installation: 'InSearchPath',
    //     steps: [
    //         [args: '--parallel $(($(nproc) + 1))', withCmake: true]
    //     ]
    // )
    sh '''
        rm -rf build-debug
        mkdir -p build-debug
        cmake -DCMAKE_BUILD_TYPE=Debug \
            -DBUILD_TESTING=ON \
            -B build-debug
        cmake --build build-debug --parallel $(($(nproc) + 1))
        '''
}
