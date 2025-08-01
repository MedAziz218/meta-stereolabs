
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

SRC_URI = "git://github.com/stereolabs/zed-ros2-wrapper.git;branch=master;protocol=http"
SRCREV = "b5844a81b0baa4e6b847a16030bcf4075f2b3d1d"

S = "${WORKDIR}/git"
OECMAKE_SOURCEPATH = "${S}/${ROS_BPN}"


#--------{ros_setup}---------------------------------------------

inherit ros_distro_humble
inherit ros_component

ROS_CN = "zed_components"
ROS_BPN = "zed_components"

ROS_BUILD_DEPENDS = " \
    zed-msgs \
    rclcpp \
    rclcpp-components \
    rcutils \
    builtin-interfaces \
    std-msgs \
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
    cob-srvs \
    diagnostic-msgs \
    diagnostic-updater \
    visualization-msgs \
    robot-localization \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    rosidl-default-generators-native \
    rosidl-default-runtime-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""
ROS_EXEC_DEPENDS = ""
# ROS_EXEC_DEPENDS = "\
#     launch-ros \
#     zed-msgs \
#     rclcpp \
#     rclcpp-components \
#     rcutils \
#     builtin-interfaces \
#     std-msgs \
#     stereo-msgs \
#     sensor-msgs \
#     geometry-msgs \
#     nav-msgs \
#     nmea-msgs \
#     geographic-msgs \
#     tf2 \
#     tf2-ros \
#     tf2-geometry-msgs \
#     image-transport \
#     diagnostic-msgs \
#     diagnostic-updater \
#     visualization-msgs \
#     robot-localization \
#     image-transport-plugins \
#     compressed-image-transport \
#     compressed-depth-image-transport \
#     theora-image-transport \
#     point-cloud-transport-plugins \
#     draco-point-cloud-transport \
#     zlib-point-cloud-transport \
#     zstd-point-cloud-transport \
# "

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = "\
    ament-lint-auto \
    ament-cmake-copyright \
    ament-cmake-cpplint \
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
EXTRA_OECMAKE += "\
    -DSTAGING_BINDIR_NATIVE=${STAGING_BINDIR_NATIVE} \
"
