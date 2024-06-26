INITSCRIPT_PARAMS = "defaults 14 86"

require samhain.inc

DEPENDS = "gmp"

SRC_URI += "file://samhain-server-volatiles \
            file://samhain-server-volatiles.conf \
           "

MODE_NAME = "server"
SAMHAIN_MODE = "server"

TARGET_CC_ARCH += "${LDFLAGS}"

do_install:append() {
    if ${@bb.utils.contains('DISTRO_FEATURES', 'systemd', 'true', 'false', d)}; then
        install -d ${D}${sysconfdir}/tmpfiles.d
        install -m 0644 ${UNPACKDIR}/samhain-server-volatiles.conf \
            ${D}${sysconfdir}/tmpfiles.d/samhain-server.conf
    else
        install -d ${D}${sysconfdir}/default/volatiles
        install -m 0644 ${UNPACKDIR}/samhain-server-volatiles \
            ${D}${sysconfdir}/default/volatiles/samhain-server
    fi

    install -m 700 samhain-install.sh init/samhain.startLinux \
        init/samhain.startLSB ${D}/var/lib/samhain
}

RDEPENDS:${PN} += "gmp bash perl"
RCONFLICTS:${PN} = "samhain-standalone"
