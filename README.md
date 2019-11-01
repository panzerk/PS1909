# PS1909
cTAKES API and web interface
1. Check Java version, my version is 11.01, JDK 11
2. Make sure you have configure the Maven environment. If not, go to https://maven.apache.org/download.cgi
download a Maven file, extract it, then go to advanced system seeting, find “Environment variables” dialog, clicks on the New... button and add a MAVEN_HOME variable and point it to Maven file.
In system variables, find PATH, clicks on the Edit... button. In “Edit environment variable” dialog, clicks on the New button and add this %MAVEN_HOME%\bin
Now start a new command prompt and run mvn -v, you will see the version of Maven
3. Create a local Mysql database woth host:127.0.0.1 port:3306 username:ps1909 password:pass, then load sql script, here is the UMLS database:https://github.com/GoTeamEpsilon/ctakes-rest-service/tree/master/sno_rx_16ab_db, or use sql fires in database folder
but that's not enough, you still need to create another table in UMLS database to store history, here is the script:
create table `analyze_result` (
    `id` int(10) not null auto_increment,
	`create_time` datetime not null,
	`analyze_type` varchar(30) not null,
	`analyze_text` text not null,
	`analyze_result` text not null,
	primary key (`id`)
) engine = innodb default charset = utf8;
4.Import ctakes-webfront and rest-ctakes in Eclipse, ctakes-webfront is frontened, rest-ctakes is backend. 
5. In Eclipse, run ctakes-webfront/src/main/java/com/job/AppBootstrap.java to power web interface on, then create a new console for backend,
run rest-ctakes/src/main/java/org/apache/ctakes/rest/CtakesApplication.java to power backend on, first time you run this java program need time to download A.pache repository.
6. Make sure the Mysql, ctakes-webfront, rest-ctakes are on. Type http://127.0.0.1:9966/ in a browser, it works for Firefox and Chrome, i did not try IE. Then you access the system.
If your browser has API tester like talend, you can use method "Post" url http://127.0.0.1:8080/analyze or http://127.0.0.1:8080/pos,
 type 
{
  "analysisText":" heart disease"
}
in body to test the API.

Here is the page design
![image](https://github.com/panzerk/PS1909/blob/master/20.PNG)
