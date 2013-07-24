hibernate-search-jpa: Hibernate-Search Example
========================
Author: Dimitrios Zakas 
Level: Intermediate
Technologies: Hibernate-Search, CDI, JSF, EJB , JPA 
Summary: Demonstrates full text-search on JPA entities 
Target Product: EAP  
Source: <https://github.com/jboss-jdf/jboss-as-quickstart/>

What is it?
-----------

This example demonstrates a text based search on JPA entities.
It consists of a search field and the result list.
When nothing is entered, the full list will be displayed.
The entities are 'Quote' and 'Topic' with a ManyToMany relationship


System requirements
-------------------

All you need to build this project is Java 6.0 (Java SDK 1.6) or better, Maven 3.0 or better.

The application this project produces is designed to be run on JBoss Enterprise Application Platform 6 or JBoss AS 7.

Configure Maven
---------------

If you have not yet done so, you must Configure Maven before testing the quickstarts.


Build and Deploy the Quickstart
-------------------------------

NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See Build and Deploy the Quickstarts for complete instructions and additional options.

Make sure you have started the JBoss Server as described above.
Open a command line and navigate to the root directory of this quickstart.
Type this command to build and deploy the archive:

mvn clean package jboss-as:deploy
This will deploy target/jboss-as-hibernate-search-jpa.war (or target/jboss-as-hibernate-search=jpa.ear) to the running instance of the server.

Access the application
----------------------
Contributor: Provide the URL to access the running application. Be sure to make the URL a hyperlink as below, substituting the your quickstart name for the QUICKSTART_NAME.

    Access the running application in a browser at the following URL:  <http://localhost:8080/jboss-as-hibernate-search-jpa>

    You will be presented with a simple form for searching quotes and  the result list

        If the field is empty all quotes will be returned  
        If the field is not empty, a text-search will be made in the entities and will be displayed in the result list

Undeploy the Archive
--------------------

Make sure you have started the JBoss Server as described above.
Open a command line and navigate to the root directory of this quickstart.
When you are finished testing, type this command to undeploy the archive:

mvn jboss-as:undeploy

Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------------------------

You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see Use JBoss Developer Studio or Eclipse to Run the Quickstarts

Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

mvn dependency:sources
mvn dependency:resolve -Dclassifier=javadoc

