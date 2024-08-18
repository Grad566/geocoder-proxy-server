FROM gradle:8.7.0-jdk21

WORKDIR /geocoding

COPY /geocoding . 

RUN gradle installBootDist

CMD ./build/install/geocoding-boot/bin/geocoding --spring.profiles.active=docker
