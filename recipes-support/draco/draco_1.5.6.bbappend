
# fix wrong branch in SRC_URI
SRC_URI = "gitsm://github.com/google/draco.git;protocol=https;branch=gltf_2.0_draco_extension \
           file://0001-instrumentation.patch"

# enable SHARED_LIBS to genereate libdraco.so (required for zed-wrapper > draco-point-cloud-transport at runtime )
EXTRA_OECMAKE += "\
    -DBUILD_SHARED_LIBS=ON \
    -DCMAKE_BUILD_TYPE=Release \
    -DDRACO_TESTS=OFF \
    -DDRACO_BUILD_STATIC=OFF \
"