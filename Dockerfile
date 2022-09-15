FROM openjdk:11
ARG USER=openjdk
ARG GROUP=openjdk

COPY --chown=${USER}:${GROUP} build/libs/pocket-url.jar /deployment/app.jar
COPY --chown=${USER}:${GROUP} docker/* /deployment/
RUN chmod +x /deployment/*
ENTRYPOINT '/deployment/run-java.sh'
