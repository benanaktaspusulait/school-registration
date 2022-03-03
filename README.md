for new macbook you can run
docker pull --platform linux/amd64 mysql:5.7.35
docker run --name school-registration -e  MYSQL_ROOT_PASSWORD=1234  bind-address=127.0.0.1 mysql:5.7.35 

# school registration metadata 
Metadata school registration assignment (code pairing or machine coding round) assignment

# Version Number
5b8d0fd276b6d288905ed2f63a934e057e8feca2

# Getting started (Run the application)
This assignment is getting driven by unit-tests 


# Things I tried to follow :
1. I have created all domain models/enums, views, entities.
2. I used lombok for getter,setters, constructors and immutability
3. I have created methods & variables names
4. I created BaseModels, BaseRepository, BaseService, BaseMapper
5. I created repositories, services, controllers, test codes
6. I used java faker to generate some dummy datas. After than I commented these lines in ApplicationStartup.java
7. I tried to avoid code duplication by refactoring/reusing duplicate code


# Things I could have done/improved if given more time :
1. More comments in the test code
2. More test code
3. Add more test code to check registration controls

#To run 
2 ways
- You can directly run schema.sql and data.sql or 
- You can change 'initialization-mode: always' then change to 'none'  
