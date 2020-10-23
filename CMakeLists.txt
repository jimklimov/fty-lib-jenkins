cmake_minimum_required(VERSION 3.13)
cmake_policy(VERSION 3.13)

project (test)

set(test_VERSION_MAJOR 0)
set(test_VERSION_MINOR 5)

set(CMAKE_C_FLAGS "-Wall -Wextra -Wpedantic")

add_executable(test src/main.cpp)
# install(TARGETS test DESTINATION bin)
