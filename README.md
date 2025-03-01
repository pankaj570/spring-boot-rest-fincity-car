# spring-boot-rest-fincity-car

# This project has built on below technology.
1. Spring security
2. Spring Session with Redis server
3. Spring HETOS for hypermedia response
4. Spring JDBC with H2Database if required any RDBMS then change only configuration in application.properties.
5. project build based on maven and gradle both.

# How to Run the project in Local System follow the below steps.

1. clone or download the zip project.
2. build the project without fail if
   a. maven build then use below command
     1. for build :\> mvn clean install
     2. Run :\> java -jar target\spring-boot-rest-fincity-car-1.0-SNAPSHOT.jar
   b. gradle build use below command"
      1. :\> gradle tasks
      2. :\> gradle run
      
3. After run the project import the collection in POSTMAN which is available in folder "postman-collection"
  1. First generate the session using basic auth of using login service.(Application.java have some already inserted users details in Database at time starting the application).
  2. After generating session user get x-auth-token in response based on base-64 encode.
  3. generated x-auth-token pass in header of each request of remaining api to get the response otherwise you get not fully authorize.
      
