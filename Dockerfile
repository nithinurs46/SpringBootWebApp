FROM openjdk:8
EXPOSE 8081
ADD target/springwebapp.war springwebapp.war
ENTRYPOINT ["java","-jar","springwebapp.war"]