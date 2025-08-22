SUMMARY = "Python API for the ZED SDK"
LICENSE = "MIT"
HOMEPAGE = "https://github.com/stereolabs/zed-python-api"

LIC_FILES_CHKSUM = "file://LICENSE;md5=a93f6f8dababde7e5265b0170acfe81b"

SRC_URI = "\
    git://github.com/stereolabs/zed-python-api.git;protocol=https;branch=master \
    file://setup.patch \
"
SRCREV = "997e0d14c0101b2029252d8307fc33e514cce949"

S = "${WORKDIR}/git"
SETUPTOOLS_SETUP_PATH = "${S}/src"

DEPENDS += "\
    python3-cython-native \
    python3-numpy-native \
    python3-pip-native \
    zed-sdk \
"
inherit cuda python3-dir
do_compile:prepend() {
    export ZED_SDK_ROOT_DIR="${STAGING_DIR_HOST}/usr/local/zed"
    export CUDA_PATH="${CUDA_PATH}"
}

do_fix_rpath() {
    set -x
    for f in ${D}${PYTHON_SITEPACKAGES_DIR}/pyzed/*.so*; do
        if [ -f "$f" ]; then
            oldrpath=$(patchelf --print-rpath "$f")
            newrpath=$(echo "$oldrpath" | sed "s#${STAGING_DIR_HOST}##g" | sed 's/::/:/g; s/:$//; s/^://')
            patchelf --set-rpath "$newrpath" "$f"
            echo "oldrpath=$oldrpath\n"
            echo "newrpath=$newrpath\n"

        fi
    done
}

do_fix_rpath[depends] += "patchelf-native:do_populate_sysroot"
addtask fix_rpath after do_install before do_package

inherit  setuptools3 
