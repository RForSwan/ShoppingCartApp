FROM maven:3.9.12
LABEL authors = "RForSwan"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn -DskipTests package

CMD ["java", "-jar", "target/opt2.jar"]