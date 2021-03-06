DESCRIPTION = "Security packagegroup for Poky"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302 \
                    file://${COREBASE}/meta/COPYING.MIT;md5=3da9cfbcb788c80a0384361b4de20420"

inherit packagegroup

PACKAGES = "packagegroup-security-tpm"

SUMMARY:packagegroup-security-tpm = "Security TPM support"
RDEPENDS:packagegroup-security-tpm = " \
    tpm-tools \
    trousers \
    pcr-extend \
    tpm-quote-tools \
    swtpm \
    libhoth \
    openssl-tpm-engine \
    ${X86_TPM_MODULES} \
    "

X86_TPM_MODULES ?= ""

X86_TPM_MODULES:x86 = " \
    kernel-module-tpm-atmel \
    kernel-module-tpm-infineon \
    kernel-module-tpm-nsc \
    "

X86_TPM_MODULES:x86-64 = " \
    kernel-module-tpm-atmel \
    kernel-module-tpm-infineon \
    kernel-module-tpm-nsc \
    "
