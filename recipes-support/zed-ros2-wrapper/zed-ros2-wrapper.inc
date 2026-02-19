LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://LICENSE;md5=c6c5b60071652a02bcffc48cda20a27c"

SRC_URI = "git://github.com/stereolabs/zed-ros2-wrapper.git;branch=master;protocol=http"
SRCREV = "b5844a81b0baa4e6b847a16030bcf4075f2b3d1d"

#--------{ros_setup}---------------------------------------------

S = "${WORKDIR}/git"
OECMAKE_SOURCEPATH = "${S}/${ROS_BPN}"

inherit ros_distro_${ROS_DISTRO}
inherit ros_component

ROS_BUILD_TYPE = "ament_cmake"
inherit_defer ros_${ROS_BUILD_TYPE}