FROM openjdk:19

EXPOSE 8080

ADD backend/target/app.jar app.jar

CMD ["sh", "-c", "java -jar app.jar --spring.data.mongodb.uri=$MONGO_DB_URI"]
