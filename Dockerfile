FROM hub.c.163.com/library/java:latest

ADD target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
