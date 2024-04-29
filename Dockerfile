FROM maven:3.9.6-eclipse-temurin-22-alpine AS build
COPY src /app/src
COPY pom.xml /app
RUN --mount=type=cache,id=m2-cache,sharing=shared,target=/root/.m2  \
  mvn --file /app/pom.xml package -DskipTests
RUN mkdir -p /app/target/dependency && (cd /app/target/dependency; jar -xf ../*.jar)

FROM eclipse-temurin:22-jre-alpine
ARG DEPENDENCY=/app/target/dependency
COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","org.fungover.springboot3k8s.Springboot3k8sApplication"]

#docker buildx build --platform linux/amd64 -t kappsegla/kube:1 --push .
#docker buildx build --platform linux/arm64 -t kappsegla/kube:1 --push .
