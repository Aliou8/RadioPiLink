#include <iostream>
#include <cstring>
#include <fcntl.h>
#include <sys/ioctl.h>
#include <linux/spi/spidev.h>
#include <unistd.h>
#include <stdint.h>

int main() {
    const char* device = "/dev/spidev0.0";

    // --- 1. Ouvrir le device SPI ---
    int fd = open(device, O_RDWR);
    if (fd < 0) {
        perror("Erreur open spidev");
        return 1;
    }

    // --- 2. Paramètres SPI ---
    uint8_t mode = SPI_MODE_0;
    uint8_t bits = 8;
    uint32_t speed = 1'000'000; // 1 MHz

    ioctl(fd, SPI_IOC_WR_MODE, &mode);
    ioctl(fd, SPI_IOC_WR_BITS_PER_WORD, &bits);
    ioctl(fd, SPI_IOC_WR_MAX_SPEED_HZ, &speed);

    // --- 3. Préparer buffers ---
    uint8_t tx[] = { 0x9F };  // Exemple : commande JEDEC ID
    uint8_t rx[3] = { 0, 0, 0 };

    // Structure spi_ioc_transfer
    struct spi_ioc_transfer tr = {};
    tr.tx_buf = (unsigned long)tx;
    tr.rx_buf = (unsigned long)rx;
    tr.len = sizeof(tx) + sizeof(rx);     // nombre total d’octets
    tr.speed_hz = speed;
    tr.bits_per_word = bits;

    // --- 4. Appeler ioctl SPI ---
    int ret = ioctl(fd, SPI_IOC_MESSAGE(1), &tr);
    if (ret < 1) {
        perror("Erreur transfert SPI");
        return 1;
    }

    // --- 5. Afficher ce qu’on a reçu ---
    std::cout << "Reçu : ";
    for (int i = 0; i < sizeof(rx); i++)
        std::cout << std::hex << (int)rx[i] << " ";
    std::cout << std::dec << std::endl;

    close(fd) ; 
    return 0;
}
