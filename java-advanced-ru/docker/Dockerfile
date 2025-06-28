FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.2

RUN apt-get update && apt-get install -yq make unzip wget \
 && wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
 && unzip gradle-${GRADLE_VERSION}-bin.zip \
 && rm gradle-${GRADLE_VERSION}-bin.zip \
 && mv gradle-${GRADLE_VERSION} /opt/gradle

ENV GRADLE_HOME=/opt/gradle
ENV PATH=$PATH:$GRADLE_HOME/bin

WORKDIR /app
COPY app/ ./

RUN gradle build --quiet -x test

EXPOSE 8080

CMD ["gradle", "bootRun", "--quiet", "-x", "test"]
