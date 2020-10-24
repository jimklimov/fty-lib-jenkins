def cmake_release() {
    cmakeBuild(
        buildType: "Release",
        buildDir: 'build-release',
        sourceDir: '.',
        cleanBuild: true,
        cmakeArgs: '-DBUILD_TESTING=OFF',
        installation: 'InSearchPath',
        steps: [
            [args: '--parallel $(($(nproc) + 1))', withCmake: true]
        ]
    )
}

def cmake_debug() {
    cmakeBuild(
        buildType: "Debug",
        buildDir: 'build-debug',
        sourceDir: '.',
        cleanBuild: true,
        cmakeArgs: '-DBUILD_TESTING=ON',
        installation: 'InSearchPath',
        steps: [
            [args: '--parallel $(($(nproc) + 1))', withCmake: true]
        ]
    )
}
