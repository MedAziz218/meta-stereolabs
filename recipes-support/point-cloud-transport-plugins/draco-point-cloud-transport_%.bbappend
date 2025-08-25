
FILESEXTRAPATHS:prepend := "${THISDIR}/${BPN}:"
SRC_URI:append = " file://fix-draco-not-linking.patch"

# ROS_BUILDTOOL_DEPENDS += " \
#     rosidl-default-generators-native \
# "
# ROS_BUILD_DEPENDS:remove = "rosidl-default-generators"
