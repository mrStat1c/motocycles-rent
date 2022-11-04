##Spring MVC приложение

###Deploy:
1) Запустить файл src/main/resources/utilityFiles/startTomcat.bat (запустится Tomcat)
<br/><br/>
Tomcat должен быть установлен в C:\tomcat
<br/><br/>
Логи будут писаться в C:\tomcat\logs\app-info-%d{yyyy-MM-dd}.log (настраивается в src/main/resources/log4j2.xml)
<br/><br/>
2) Выполнить скрипт src/main/resources/utilityFiles/createDb.sql (создастся бд motorent)
<br/><br/>
Предварительно должна быть установлена MySQL
<br/><br/>
3) Выполнить скрипт src/main/resources/utilityFiles/fillDb.sql (накатятся тестовые данные в бд motorent)
<br/><br/>
4) Выполнить build task "war"
<br/><br/>
motorent.war задеплоится в C:\tomcat\war (настраивается в build.gradle)

###URL:
http://localhost:8080/motorent/customers
http://localhost:8080/motorent/moto

###Branches:
**master**
<br/>
взаимодействие с бд с помощью Spring Data HPA

**hibernate**
<br/>
взаимодействие с бд с помощью Hibernate

**jdbcTemplate**
<br/>
взаимодействие с бд с помощью JdbcTemplate
