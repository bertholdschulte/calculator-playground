FROM java:8-jdk
EXPOSE 8080

ADD target/calculator-0.0.1-SNAPSHOT.jar /app/calculator.jar

CMD ["java", "-jar", "/app/calculator.jar"] 
