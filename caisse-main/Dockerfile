FROM maven:3.8.2-jdk-11 as lets-dev

WORKDIR /src

CMD bash -c "mvn dependency:go-offline -B && mvn clean spring-boot:run -Dspring.profiles.active=dev"

