SUMMARY = "Exports a gssapi interface which calls other gssapi libraries"
DESCRIPTION = "\
This library exports a gssapi interface, but does not implement any gssapi \
mechanisms itself; instead it calls gssapi routines in other libraries, \
depending on the mechanism. \
"

HOMEPAGE = "http://www.citi.umich.edu/projects/nfsv4/linux/"
SECTION = "libs"

LICENSE = "BSD-3-Clause | HPND"

#Copyright (c) 1996, by Sun Microsystems, Inc.                   HPND
#Copyright (c) 2007 The Regents of the University of Michigan. BSD-3-Clause
#Copyright 1995 by the Massachusetts Institute of Technology.  HPND without Disclaimer
#Copyright 1993 by OpenVision Technologies, Inc.               HPND
LIC_FILES_CHKSUM = "file://COPYING;md5=56871e72a5c475289c0d5e4ba3f2ee3a \
                    file://src/g_accept_sec_context.c;beginline=3;endline=23;md5=da8ca7a37bd26e576c23874d453751d2\
                    file://src/g_ccache_name.c;beginline=1;endline=32;md5=208d4de05d5c8273963a8332f084faa7 \
                    file://src/oid_ops.c;beginline=1;endline=26;md5=1f194d148b396972da26759a8ec399f0\
                    file://src/oid_ops.c;beginline=378;endline=398;md5=72457a5cdc0354cb5c25c8b150326364\
"

SRC_URI = "git://gitlab.com/gsasl/libgssglue.git;protocol=https;branch=master \
           file://run-ptest \
          "
SRCREV = "ada76bdaec665f70505f0b3aefe871b873e7c4b6"

inherit autotools-brokensep ptest

do_configure:prepend() {
    cd ${S}
    ./bootstrap
}

do_install:append() {
    # install some docs
    install -d -m 0755 ${D}${docdir}/${BPN}
    install -m 0644 ${S}/AUTHORS ${S}/ChangeLog ${S}/NEWS ${S}/README ${D}${docdir}/${BPN}

    # install the gssapi_mech.conf
    install -d -m 0755 ${D}${sysconfdir}
    install -m 0644 ${S}/doc/gssapi_mech.conf ${D}${sysconfdir}
    
    # change the libgssapi_krb5.so path and name(it is .so.2)
    sed -i -e "s:/usr/lib/libgssapi_krb5.so:libgssapi_krb5.so.2:" ${D}${sysconfdir}/gssapi_mech.conf
}

do_compile_ptest() {
    echo 'buildtest-TESTS: $(check_PROGRAMS)' >> ${S}/tests/Makefile
    oe_runmake -C ${S}/tests buildtest-TESTS
}

do_install_ptest() {
    install -m 755 ${S}/tests/generic ${D}/${PTEST_PATH}
}

# gssglue can use krb5, spkm3... as gssapi library, configurable
RRECOMMENDS:${PN} += "krb5"
