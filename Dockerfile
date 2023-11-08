FROM maven:3.9.5-eclipse-temurin-21 AS BUILD
COPY . /build/
WORKDIR /build/
RUN mvn -C clean package -Pproduction

FROM eclipse-temurin:21
COPY --from=BUILD /build/target/*.war /app/
WORKDIR /app
RUN jar xvf *.war
WORKDIR /app/WEB-INF/
EXPOSE 8080
ENTRYPOINT java -classpath "lib/*:classes/." org.vaadin.samuli.InfiniteGridDemo