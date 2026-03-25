FROM maven:3.9.12
LABEL authors = "RForSwan"

WORKDIR /app

COPY pom.xml .

COPY . /app

RUN mvn -DskipTests package

COPY target/opt2.jar /app/opt2.jar

CMD ["java", "-jar", "target/opt2.jar"]