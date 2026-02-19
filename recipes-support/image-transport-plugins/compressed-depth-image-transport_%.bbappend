# fix warning treated as error : 
# build/tmp/work/armv8a-yoe-linux/compressed-depth-image-transport/2.5.3-1/recipe-sysroot/opt/ros/humble/include/rcutils/rcutils/logging_macros.h:79:18: error: format not a string literal and no format arguments [-Werror=format-security]
# |    79 |       rcutils_log(&__rcutils_logging_location, severity, name, __VA_ARGS__); \
# |       |       ~~~~~~~~~~~^~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

EXTRA_OECMAKE:append = " -DCMAKE_CXX_FLAGS='-Wno-error=format-security'"
