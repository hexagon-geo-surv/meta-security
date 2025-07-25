# This is an example for Security hardening an OE or Poky image


Meta-hardening
=============

This layer provides examples for hardening OE/Yocto images.
This layer does not provide 100% security protection.  This is only 
a framework from which a user can build from and can possible contribute to.
The goal here is to capture use cases and examples the community decided shares for
everyones benefit.

Building the meta-hardening layer
-------------------------------
In order to add hardening support to the poky/OE build this layer should be added
to your projects bblayers.conf file.

By default the hardening components are disabled.  This conforms to the
Yocto Project compatible guideline that indicate that simply including a
layer should not change the system behavior.

In order to use the components in this layer to take affect the  'harden' keyword must
set the DISTRO as in "DISTRO = harden".   This enables the "NO ROOT access" idea or framework.

If one wants the a more complete example of a hardened image, one must also build the image:
harden-image-minimal

There are default example userid and passwards:
These can be over written in your local.conf via:
ROOT_DEFAULT_PASSWORD ?= "1SimplePw!"
DEFAULT_ADMIN_ACCOUNT ?= "myadmin"

example:
local.conf
DISTRO = "harden"

The default user and password are: 
User: "myadmin"
Password: "1SimplePw!"

bitbake {qemu machine} harden-image-minimal

Dependencies
============

Branch: master

This layer depends on:

URI: git://git.yoctoproject.org/poky

or this normal combo: 

URI: git://git.openembedded.org/meta-openembedded/meta-oe

URI: git://git.openembedded.org/bitbake

plus:

URI: git://git.openembedded.org/meta-openembedded
layers: meta-oe


Maintenance
-----------

Send pull requests, patches, comments or questions to yocto-patches@lists.yoctoproject.org

When sending single patches, please using something like:
'git send-email -1 --to yocto-patches@lists.yoctoproject.org --subject-prefix=meta-hardening][PATCH'

These values can be set as defaults for this repository:

$ git config sendemail.to yocto-patches@lists.yoctoproject.org
$ git config format.subjectPrefix meta-hardening][PATCH

Now you can just do 'git send-email origin/master' to send all local patches.

Maintainers:
Scott Murray <scott.murray@konsulko.com>
Marta Rybczynska <rybczynska@gmail.com>

License
=======

All metadata is MIT licensed unless otherwise stated. Source code included
in tree for individual recipes is under the LICENSE stated in each recipe
(.bb file) unless otherwise stated.
