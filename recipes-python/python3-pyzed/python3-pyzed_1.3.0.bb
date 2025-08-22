SUMMARY = "Python API for the ZED SDK."
LICENSE = "CLOSED"
LIC_FILES_CHKSUM = ""

DEPENDS = "python3-cython-native"
RDEPENDS:${PN} += "\
    python3-numpy \
"

inherit pypi setuptools3
PYPI_PACKAGE = "pyzed"
SRC_URI[md5sum] = "9c0342b18cdb4c32dee78bc12cfec2bc"
SRC_URI[sha256sum] = "63d680ecd6bc993f8c72abba0369b28478a3c1d35cf671992f862bcee140d604"
