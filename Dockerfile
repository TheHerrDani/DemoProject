FROM maven:3-openjdk-18-slim as build

WORKDIR /build

COPY pom.xml pom.xml
COPY security/pom.xml security/pom.xml
COPY repository-layer/pom.xml repository-layer/pom.xml
COPY business/pom.xml business/pom.xml
COPY rest-service/pom.xml rest-service/pom.xml
COPY checkstyle_config.xml checkstyle_config.xml

RUN mvn -q -ntp -B -pl security -am dependency:go-offline
ADD security security
RUN mvn -q -B -pl security install -DskipTests

RUN mvn -q -ntp -B -pl repository-layer -am dependency:go-offline
ADD repository-layer repository-layer
RUN mvn -q -B -pl repository-layer install -DskipTests

RUN mvn -q -ntp -B -pl business -am dependency:go-offline
ADD business business
RUN mvn -q -B -pl business install -DskipTests

RUN mvn -q -ntp -B -pl rest-service -am dependency:go-offline
ADD rest-service rest-service
RUN mvn -pl rest-service install -DskipTests -P production

RUN mvn -q -ntp -B -pl security,repository-layer,business,rest-service package -DskipTests

RUN mkdir -p /jar-layers
WORKDIR /jar-layers
# Extract JAR layers
RUN java -Djarmode=layertools -jar /build/rest-service/target/*.jar extract


FROM maven:3.8.5-openjdk-18
RUN mkdir -p /app
WORKDIR /app
COPY --from=build /jar-layers/dependencies/ ./
COPY --from=build /jar-layers/spring-boot-loader/ ./
COPY --from=build /jar-layers/snapshot-dependencies/ ./
COPY --from=build /jar-layers/application/ ./
ENTRYPOINT ["java","-Dspring.profiles.active=production", "org.springframework.boot.loader.JarLauncher"]
