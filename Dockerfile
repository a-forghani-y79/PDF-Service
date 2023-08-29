FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /opt/app
COPY . .
RUN chmod +x mvnw
RUN ./mvnw clean install

FROM eclipse-temurin:17-jre-jammy
WORKDIR /opt/app
EXPOSE 8080
COPY --from=builder /opt/app/bimany-api/target/*.jar /opt/app/*.jar

ENTRYPOINT ["java","-jar","/opt/app/*.jar" ]
