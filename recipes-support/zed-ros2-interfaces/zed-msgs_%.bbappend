
# | CMake Error at /home/yocto/yoe-distro/build/tmp/work/armv8a-yoe-linux/zed-msgs/5.0.1-1/recipe-sysroot/opt/ros/humble/share/rosidl_cmake/cmake/rosidl_write_generator_arguments.cmake:61 (message):
# |   rosidl_write_generator_arguments() must be invoked with at least one of the
# |   IDL_TUPLES;NON_IDL_TUPLES;ROS_INTERFACE_FILES arguments

# "rosidl-default-generators" belongs in ROS_BUILDTOOL_DEPENDS (as "rosidl-default-generators-native"); it should not be in
# ROS_BUILD_DEPENDS.

ROS_BUILDTOOL_DEPENDS += " \
    rosidl-default-generators-native \
"
ROS_BUILD_DEPENDS:remove = "rosidl-default-generators"

