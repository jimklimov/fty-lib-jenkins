def dummy_test() {
    echo "Dummy test..."
}

def unit_tests() {
    cmakeBuild(
        buildType: "Release",
        buildDir: 'build-release-tests',
        sourceDir: '.',
        cleanBuild: true,
        cmakeArgs: '-DBUILD_TESTING=ON',
        installation: 'InSearchPath',
        steps: [
            [args: 'test', withCmake: true]
            [args: '--parallel $(($(nproc) + 1))', withCmake: true]
        ]
    )
}

def cmake_debug() {
    cmakeBuild(
        buildType: "Release",
        buildDir: 'build-release-tests',
        sourceDir: '.',
        cleanBuild: true,
        cmakeArgs: '-DBUILD_TESTING=ON',
        installation: 'InSearchPath',
        steps: [
            [args: 'test memcheck', withCmake: true]
            [args: '--parallel $(($(nproc) + 1))', withCmake: true]
        ]
    )
}
