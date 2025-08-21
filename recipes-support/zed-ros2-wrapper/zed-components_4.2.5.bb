
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6c5b60071652a02bcffc48cda20a27c"

SRC_URI = "git://github.com/stereolabs/zed-ros2-wrapper.git;branch=master;protocol=http"
SRCREV = "b5844a81b0baa4e6b847a16030bcf4075f2b3d1d"

S = "${WORKDIR}/git"
OECMAKE_SOURCEPATH = "${S}/${ROS_BPN}"
inherit cuda

#--------{ros_setup}---------------------------------------------

inherit ros_distro_humble
inherit ros_component

ROS_CN = "zed_components"
ROS_BPN = "zed_components"

ROS_BUILD_DEPENDS = "\
    ament-cmake-auto \
    zed-msgs \
    rclcpp \
    rclcpp-components \
    rcutils \
    builtin-interfaces \
    std-msgs \
    rosgraph-msgs \
    stereo-msgs \
    sensor-msgs \
    geometry-msgs \
    nav-msgs \
    nmea-msgs \
    geographic-msgs \
    tf2 \
    tf2-ros \
    tf2-geometry-msgs \
    image-transport \
    point-cloud-transport \
    std-srvs \
    diagnostic-msgs \
    diagnostic-updater \
    visualization-msgs \
    robot-localization \
    backward-ros \
    \
    cob-srvs \
    zed-sdk \
"
ROS_BUILDTOOL_DEPENDS = "\
    ament-cmake-native \
    rosidl-default-generators-native \
"
ROS_EXPORT_DEPENDS = "\
"
ROS_BUILDTOOL_EXPORT_DEPENDS = "\
"
ROS_EXEC_DEPENDS = "\
    launch-ros \
    zed-msgs \
    rclcpp \
    rclcpp-components \
    rcutils \
    builtin-interfaces \
    std-msgs \
    rosgraph-msgs \
    stereo-msgs \
    sensor-msgs \
    geometry-msgs \
    nav-msgs \
    nmea-msgs \
    geographic-msgs \
    tf2 \
    tf2-ros \
    tf2-geometry-msgs \
    image-transport \
    diagnostic-msgs \
    diagnostic-updater \
    visualization-msgs \
    robot-localization \
    image-transport-plugins \
    compressed-image-transport \
    compressed-depth-image-transport \
    theora-image-transport \
    point-cloud-transport-plugins \
    draco-point-cloud-transport \
    zlib-point-cloud-transport \
    zstd-point-cloud-transport \
    backward-ros \
"
ROS_TEST_DEPENDS = "\
    ament-lint-auto \
    ament-cmake-copyright \
    ament-cmake-cppcheck \
    ament-cmake-lint-cmake \
    ament-cmake-pep257 \
    ament-cmake-uncrustify \
    ament-cmake-xmllint \
"


DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS:${PN} += "${ROS_EXEC_DEPENDS}"

ROS_BUILD_TYPE = "ament_cmake"

inherit ros_${ROS_BUILD_TYPE}

#-------------{special_configs}---------------------------------------------
RDEPENDS:${PN} += "ros-base"

# WARNING: zed-components-4.2.5-r0 do_package_qa: QA Issue: File /opt/ros/humble/lib/libzed_camera_component.so in package zed-components contains reference to TMPDIR
# File /opt/ros/humble/lib/libzed_camera_one_component.so in package zed-components contains reference to TMPDIR [buildpaths]
#
# | /mnt/yoctoSSD/yocto/yoe-distro/build/tmp/work/armv8a_tegra234-yoe-linux/zed-components/4.2.5/recipe-sysroot/opt/ros/humble/include/rcutils/rcutils/logging_macros.h:79:18: error: format not a string literal and no format arguments [-Werror=format-security]
# |    79 |       rcutils_log(&__rcutils_logging_location, severity, name, __VA_ARGS__); 
#
EXTRA_OECMAKE:append = " \
    -DCMAKE_C_FLAGS='${CFLAGS} -fmacro-prefix-map=${WORKDIR}=. -fmacro-prefix-map=${S}=.' \
    -DCMAKE_CXX_FLAGS='${CXXFLAGS} -fmacro-prefix-map=${WORKDIR}=. -fmacro-prefix-map=${S}=.' \
"

