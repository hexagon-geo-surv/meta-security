SUMMARY = "LibHTP is a security-aware parser for the HTTP protocol and the related bits and pieces."

require suricata.inc

LIC_FILES_CHKSUM = "file://LICENSE;beginline=1;endline=2;md5=596ab7963a1a0e5198e5a1c4aa621843"

SRC_URI = "git://github.com/OISF/libhtp.git;protocol=https;branch=0.5.x"
SRCREV = "ebe480be4a5f3bb1d44be6a9a6c2755bd8ef7e71"

DEPENDS = "zlib"

inherit autotools-brokensep pkgconfig

CFLAGS += "-D_DEFAULT_SOURCE"

do_configure () {
    cd ${S}
    ./autogen.sh
    oe_runconf
}

RDEPENDS:${PN} += "zlib"

