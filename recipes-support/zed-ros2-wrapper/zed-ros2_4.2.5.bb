require zed-ros2-wrapper.inc

ROS_CN = "zed_wrapper"
ROS_BPN = "zed_wrapper"

ROS_BUILD_DEPENDS = "\
    ament-cmake-auto \
"
ROS_BUILDTOOL_DEPENDS = "\
    ament-cmake-native \
"
ROS_EXPORT_DEPENDS = "\
"
ROS_BUILDTOOL_EXPORT_DEPENDS = "\
"
ROS_EXEC_DEPENDS = "\
    zed-msgs \
    zed-components \
    zed-wrapper \
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

#-------------{special_configs}---------------------------------------------
RDEPENDS:${PN} += "ros-base"
# EXTRA_OECMAKE += "\
#     -DSTAGING_BINDIR_NATIVE=${STAGING_BINDIR_NATIVE} \
# "


# TODO: fix this package overwrites the files of zed-wrapper
# inspect both packages and split them correctly  
