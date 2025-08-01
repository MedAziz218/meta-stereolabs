SUMMARY = "installs the sdk for sterolabs camera"
DESCRIPTION = "recipe that install stereolabs zed-sdk needed to use stereolabs zed cameras like the ZED-M"
LICENSE = "CLOSED"

COMPATIBLE_MACHINE = "(tegra)"

DOWNLOAD_FILENAME = "ZED_SDK_Linux.run"

# TODO: retrieve these from PV
ZED_SDK_MAJOR = "5"
ZED_SDK_MINOR = "0"
# TODO: inherit l4t versions from meta-tegra layer
L4T_MAJOR_VERSION = "36"
L4T_MINOR_VERSION = "4"

SRC_URI = "https://download.stereolabs.com/zedsdk/${ZED_SDK_MAJOR}.${ZED_SDK_MINOR}/l4t${L4T_MAJOR_VERSION}.${L4T_MINOR_VERSION}/jetsons;downloadfilename=${DOWNLOAD_FILENAME}"
SRC_URI[sha256sum] = "f6027b1db0e11348b0892efaa0259235ade4f29fd22da874f42ec795b0589fcb"

SOLIBS = ".so"
FILES_SOLIBSDEV = ""

do_configure[noexec] = "1"
do_compile[noexec] = "1"

INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:${PN} += "libdir"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT = "1"

zedsdk_dir = "/usr/local/zed"

inherit cuda useradd

USERADD_PACKAGES = "${PN}"
GROUPADD_PARAM:${PN} = "-f -r zed"

DEPENDS += "\
    libjpeg-turbo \
    libusb1 \
    lapack \
    libpng \
    mesa \
    zlib \
    libarchive \
    tegra-nvpmodel \
    tegra-libraries-camera \ 
    tensorrt-plugins-prebuilt \
    v4l-utils \
"

RDEPENDS:${PN} += "\
    hidapi \
"

do_unpack() {
    [ -d ${S} ] || mkdir -p ${S}
    cd ${S}
    chmod +x ${DL_DIR}/${DOWNLOAD_FILENAME} 
    ${DL_DIR}/${DOWNLOAD_FILENAME} --tar xf
}

do_patch(){
    # edit the installation path
    sed -i "/^set(ZED_PATH /c\set(ZED_PATH \"${zedsdk_dir}\")" ${S}/zed-config.cmake
    # set LIB_PATH_64 to /usr/lib/
    sed -i 's|set *(LIB_PATH_64 *"/usr/lib/[^"]*")|set (LIB_PATH_64 "${libdir}/")|' ${S}/zed-config.cmake
}

do_install () {
        # install libs
        install -d ${D}${zedsdk_dir}/lib
        install ${S}/lib/libsl_ai.so ${D}${zedsdk_dir}/lib
        install ${S}/lib/libsl_zed.so ${D}${zedsdk_dir}/lib

        # install include folder
        install -d ${D}${zedsdk_dir}/include
        cp -r ${S}/include ${D}${zedsdk_dir}

        # install .cmake files
        install ${S}/zed-config.cmake ${D}${zedsdk_dir}/zed-config.cmake
        install ${S}/zed-config-version.cmake ${D}${zedsdk_dir}/zed-config-version.cmake

        chmod 770 -R "${D}${zedsdk_dir}" 
        chgrp -R zed "${D}${zedsdk_dir}" 
        
        #install udev rules
        install -d ${D}${sysconfdir}/udev/rules.d
        install -m 0644 ${S}/99-slabs.rules ${D}${sysconfdir}/udev/rules.d/    
}

# TODO: probably changing these to be seperate packages makes more sense
PACKAGECONFIG ??= "samples resources firmware drivers doc"
PACKAGECONFIG[samples] = ",,,"
PACKAGECONFIG[tools] = ",,,"
PACKAGECONFIG[resources] = ",,,"
PACKAGECONFIG[firmware] = ",,,"
PACKAGECONFIG[drivers] = ",,,"
PACKAGECONFIG[doc] = ",,,"

ZED_SDK_EXTRAS = "samples tools resources firmware drivers doc"

do_install:append() {
    for extra in ${ZED_SDK_EXTRAS}; do
        if [[ "${PACKAGECONFIG}" == *"${extra}"* ]]; then
            cp -r "${S}/${extra}" "${D}${zedsdk_dir}/"
            chmod 770 -R "${D}${zedsdk_dir}/${extra}" 
            chgrp -R zed "${D}${zedsdk_dir}/${extra}" 
        fi
    done
}

FILES:${PN} += "${zedsdk_dir}"