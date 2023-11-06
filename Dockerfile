FROM eclipse-temurin:21 AS BUILD
RUN apt update && apt install unzip maven -y
COPY . /app/
WORKDIR /app/
RUN mvn -C clean test package -Pproduction
WORKDIR /app/target/
RUN ls -la
RUN unzip *.zip -d app/

FROM eclipse-temurin:21
COPY --from=BUILD /app/target/app /app/
WORKDIR /app/
EXPOSE 8080
ENTRYPOINT ./run