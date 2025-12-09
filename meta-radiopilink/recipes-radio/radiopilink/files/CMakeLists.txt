cmake_minimum_required(VERSION 3.10)
project(myapp LANGUAGES C CXX)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_STANDARD_REQUIRED ON)

# Ajouter les dossiers include/
include_directories(${CMAKE_SOURCE_DIR}/include)

# Lister tous les fichiers sources automatiquement
file(GLOB_RECURSE SRC_FILES
    "${CMAKE_SOURCE_DIR}/src/*.cpp"
    "${CMAKE_SOURCE_DIR}/main.cpp"
)

# Créer l’exécutable
add_executable(myapp ${SRC_FILES})

# Libs éventuelles (pour Raspberry Pi SPI)
# Sur Yocto, le driver SPI est dans le kernel, mais si tu utilises pigpio/spidev/etc :
# target_link_libraries(myapp pthread)
# target_link_libraries(myapp pigpio)   # si nécessaire

# La partie IMPORTANTE pour Yocto !!!
# → Install dans /usr/bin via CMake
install(TARGETS myapp DESTINATION bin)
