start Docker Desktop
switch to Linux mode (click up arrow on task bar, right click cup, "switch to Linux containers")

DIDN'T WORK from https://quarkus.io/guides/security-keycloak-authorization
docker run --name keycloak -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -p 8543:8443 -v E:/Projects/BAE/Quarkus/Keycloak/Container/config/keycloak-keystore.jks:/etc/keycloak-keystore.jks quay.io/keycloak/keycloak:25.0.6 start  --hostname-strict=false --https-key-store-file=/etc/keycloak-keystore.jks

THIS WORKS FROM https://www.keycloak.org/getting-started/getting-started-docker
docker run -p 8080:8080 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.6 start-dev
follow instructions until section "Secure the first application"


<ctrl-c>

run it again mapping to different ports
docker run -p 8543:8080 -p 8544:9000 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.6 start-dev


TO RUN IN INTERACTIVE SHELL
docker run -p 8543:8080 -p 8544:9000 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin -it -entrypoint /bin/bash quay.io/keycloak/keycloak:25.0.6


---- START TEST
THIS WORKS - RUN CONTAINER THEN CHANGE USING BROWSER THEN COMMIT TO SAVE CHANGES...

RUN NORMALLY THEN DO CHANGES IN BROWSER THEN SAVE IMAGE USING ANOTHER CMD WINDOW
docker run -p 8543:8080 -p 8544:9000 -e KEYCLOAK_ADMIN=admin -e KEYCLOAK_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:25.0.6 start-dev

make changes i.e. create REALM then USER then set password for user 

IN ANOTHER CMD WINDOW
docker ps
docker commit 889e5ac4dff9 myimages/keycloaksetup:1.0.0
docker images

<ctrl-c> original image
docker run -p 8543:8080 -p 8544:9000  myimages/keycloaksetup:1.0.0 start-dev

---- FINISHED TEST

The above REALM and USER can be used in Quarkus project
TBD
https://quarkus.io/guides/security-keycloak-authorization



ADMIN CONSOLE
http://localhost:8543/
ACCOUNT CONSOLE - TO LOG myuser IN
http://localhost:8543/realms/myrealm/account