SUMMARY = "installs the sdk for sterolabs camera"
DESCRIPTION = "recipe that install stereolabs zed-sdk needed to use stereolabs zed cameras like the ZED-M"
LICENSE = "CLOSED"
COMPATIBLE_MACHINE = "(tegra)"

DOWNLOAD_FILENAME = "ZED_SDK_Linux.run"

ZED_SDK_MAJOR = "5"
ZED_SDK_MINOR = "0"
L4T_MAJOR_VERSION = "36"
L4T_MINOR_VERSION = "4"

SRC_URI = "https://download.stereolabs.com/zedsdk/${ZED_SDK_MAJOR}.${ZED_SDK_MINOR}/l4t${L4T_MAJOR_VERSION}.${L4T_MINOR_VERSION}/jetsons;downloadfilename=${DOWNLOAD_FILENAME}"
SRC_URI[sha256sum] = "f6027b1db0e11348b0892efaa0259235ade4f29fd22da874f42ec795b0589fcb"

do_unpack() {
    [ -d ${S} ] || mkdir -p ${S}
    cd ${S}
    chmod +x ${DL_DIR}/${DOWNLOAD_FILENAME} 
    ${DL_DIR}/${DOWNLOAD_FILENAME} --tar xf
}