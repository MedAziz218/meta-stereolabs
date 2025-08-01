
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""
SRC_URI = "git://10.5.3.3/mlahdheri/fitbot-yocto-version.git;branch=main;protocol=http;lfs=${CLONE_LFS}"

SRC_URI = "git://github.com/stereolabs/zed-ros2-wrapper.git;branch=main;protocol=http"
SRCREV = "b5844a81b0baa4e6b847a16030bcf4075f2b3d1d"

S = "${WORKDIR}/git"
OECMAKE_SOURCEPATH = "${S}/${ROS_BPN}"


#--------{ros_setup}---------------------------------------------

inherit ros_distro_humble
inherit ros_component

ROS_CN = "orchestrator"
ROS_BPN = "orchestrator"

ROS_BUILD_DEPENDS = " \
    fitbot-robot-comm \
    \
    nlohmann-json \
    rclcpp \
    rclcpp-action \
    std-msgs \
    moveit-ros-planning-interface \
    grpc \
"

ROS_BUILDTOOL_DEPENDS = " \
    ament-cmake-native \
    grpc-native \
    protobuf-native \
"

ROS_EXPORT_DEPENDS = ""

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = "\
    grpc \
"

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
