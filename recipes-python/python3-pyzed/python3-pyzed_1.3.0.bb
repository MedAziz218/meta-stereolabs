SUMMARY = "Pythonic bindings for FFmpeg's libraries."
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://README.md;md5=eace314daa4ef4785001e809f6027e10"

DEPENDS = "python3-cython-native "
RDEPENDS:${PN} += "\
    python3-opengl \
"

inherit pypi python_setuptools_build_meta
PYPI_PACKAGE = "pyzed"
SRC_URI[md5sum] = "9c0342b18cdb4c32dee78bc12cfec2bc"
SRC_URI[sha256sum] = "63d680ecd6bc993f8c72abba0369b28478a3c1d35cf671992f862bcee140d604"
