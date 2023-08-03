# Herda as configurações do emulador (produto sdk_phone_x86_64)
$(call inherit-product, $(SRC_TARGET_DIR)/product/sdk_phone_x86_64.mk)
# Sobrescreve algumas variáveis com os dados do novo produto
PRODUCT_NAME := devtitans_kraken
PRODUCT_DEVICE := kraken
PRODUCT_BRAND := KrakenBrand
PRODUCT_MODEL := KrakenModel
PRODUCT_COPY_FILES += \
 device/devtitans/kraken/kraken.txt:system/etc/kraken.txt \
 device/devtitans/kraken/kraken.rc:vendor/etc/init/kraken.rc\
 device/devtitans/kraken/tambaqui.sh:system/bin/tambaqui.sh
PRODUCT_SYSTEM_PROPERTIES += \
 ro.devtitans.name=kraken
LOCAL_PACKAGE_NAME := listApps2
PRODUCT_PACKAGES += \
 UniversalMediaPlayer \
 hello_world \
 listApps2