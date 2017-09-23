FROM openjdk:8-jre-alpine
EXPOSE 8080 8081

COPY build/install/scim-service /scim-service/
COPY config.yml  /scim-service/config.yml
    
# Debugging: set following params
# -Xdebug -agentlib:jdwp=transport=dt_socket,address=<DebugPort:8787>,server=y,suspend=n
CMD /scim-service/bin/scim-service server /scim-service/config.yml
