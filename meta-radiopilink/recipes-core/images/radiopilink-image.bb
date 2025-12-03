require conf/image/core-image-minimal.bb

#-----------------------------------------------------------------------
#   IMAGE FEATURES
#-----------------------------------------------------------------------

IMAGE_FEATURES += "splash package-management ssh-server-dropbear hwcodecs weston allow-empty-password allow-root-login"
inherit core-image features_check
CORE_IMAGE_BASE_INSTALL += "${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'weston-xwayland matchbox-terminal', '', d)}"

QB_MEM = "-m 512"

REQUIRED_DISTRO_FEATURES = "wayland"


#-----------------------------------------------------------------------
#  IMAGE SETTINGS
#-----------------------------------------------------------------------
inherit extrausers
EXTRA_USERS_PARAMS = "\
    useradd -p '' -G audio,video,dialout -d /home/radiopilink -m radiopilink; \
    usermod -a -G sudo radiopilink; \
    "

#-----------------------------------------------------------------------
#   IMAGE INSTALL PACKAGES
#-----------------------------------------------------------------------
IMAGE_INSTALL += " \
    qt6-qtbase \
    qt6-qtbase-plugins \
    qt6-qtdeclarative \
    qt6-qtquickcontrols2 \
"
PACKAGECONFIG:qt6-qtbase = "eglfs,linuxfb"

#-----------------------------------------------------------------------
#   ADDITIONAL PACKAGES FOR PYTHON3 SUPPORT
#-----------------------------------------------------------------------

IMAGE_INSTALL:append = " \
    python3-pip \
    python3-setuptools \
    python3-wheel \
    python3-numpy \
    python3-scipy \
    python3-matplotlib \
    python3-pandas \
    python3-smbus2 \
    python3-rpi.gpio \
    python3-picamera2 \
    python3-opencv \
    python3-serial \
    python3-flask \
    python3-flask-cors \
    python3-requests \
    python3-cryptography \
    python3-paramiko \
    python3-netifaces \
    python3-jinja2 \
    python3-psutil \
    
"
#-----------------------------------------------------------------------
#   ADDITIONAL PACKAGES FOR WIRELESS SUPPORT
#-----------------------------------------------------------------------
IMAGE_INSTALL:append = " \
    wireless-tools \
    wpa-supplicant \
    iw \
    firmware-brcm80211 \
    "
#-----------------------------------------------------------------------
#   ADDITIONAL PACKAGES FOR BLUETOOTH SUPPORT
#-----------------------------------------------------------------------
IMAGE_INSTALL:append = " \
    bluez \
    bluez-tools \
    pi-bluetooth \
    "
#-----------------------------------------------------------------------
#   ADDITIONAL PACKAGES FOR AUDIO SUPPORT
#-----------------------------------------------------------------------
IMAGE_INSTALL:append = " \
    alsa-utils \
    "
#-----------------------------------------------------------------------
#   ADDITIONAL PACKAGES FOR DEV SUPPORT
#-----------------------------------------------------------------------
IMAGE_INSTALL:append = " \
    git \
    vim \
    htop \
    sudo \
    net-tools \
    curl \
    wget \
    unzip \
    pciutils \
    bash-completion \
    htop \
    nano \
    bash \
    tzdata \
    procps \
    media-files \
    util-linux \
    coreutils \
    bash \
    "

#-----------------------------------------------------------------------
#   RPI SPECIFIC PACKAGES SETTINGS
#-----------------------------------------------------------------------

ENABLE_UART = "1"
ENABLE_SPI_BUS = "1"
ENABLE_I2C_BUS = "1"
KERNEL_MODULE_AUTOLOAD:rpi += "i2c-dev i2c-bcm2708"
RPI_USE_U_BOOT = "1"
VIDEO_CAMERA = "1"
ENABLE_DWC2_PERIPHERAL = "1"
ENABLE_DWC2_HOST = "1"
ENABLE_W1 = "1"

