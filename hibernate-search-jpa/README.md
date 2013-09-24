hibernate-search-jpa: Hibernate-Search Example
========================
Author: Dimitrios Zakas 
Level: Intermediate
Technologies: Hibernate-Search, CDI, JSF, EJB , JPA
Summary: Demonstrates a full text-search on JPA entities 
Target Product: EAP  
Source: <https://github.com/jboss-jdf/jboss-as-quickstart/>

What is it?
-----------

This example demonstrates a full text based search on JPA entities.
The web application consists of a search field and the result list.
When nothing is entered, the full list will be displayed.
The entities are 'Quote' and 'Topic' with a ManyToMany relationship.
This example also contains an Arquillian test.


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
        If the field is not empty, a text-search will be made in the entities and the results will be displayed in the following list

Undeploy the Archive
--------------------

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. When you are finished testing, type this command to undeploy the archive:

        mvn jboss-as:undeploy

Run the Quickstart in JBoss Developer Studio or Eclipse
-------------------------------------------------------

You can also start the server and deploy the quickstarts from Eclipse using JBoss tools. For more information, see Use JBoss Developer Studio or Eclipse to Run the Quickstarts


Run the Arquillian Tests
-------------------------

This quickstart provides Arquillian tests. By default, these tests are configured to be skipped as Arquillian tests require the use of a container. 

_NOTE: The following commands assume you have configured your Maven user settings. If you have not, you must include Maven setting arguments on the command line. See [Run the Arquillian Tests](../README.md#run-the-arquillian-tests) for complete instructions and additional options._

1. Make sure you have started the JBoss Server as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type the following command to run the test goal with the following profile activated:

        mvn clean test -Parq-jbossas-remote 

Investigate the Console Output
------------------------------

JUnit will present you test report summary:

    Tests run: 3, Failures: 0, Errors: 0, Skipped: 0

If you are interested in more details, check ``target/surefire-reports`` directory. 
You can check console output to verify that Arquillian has really used the real application server. 
Search for lines similar to the following ones in the server output log:

    [timestamp] INFO  [org.jboss.as.server.deployment] (MSC service thread 1-2) JBAS015876: Starting deployment of "QuoteSearchTest.war"
    ...
    [timestamp] INFO  [org.jboss.as.server] (management-handler-thread - 4) JBAS018559: Deployed "QuoteSearchTest.war"
    ...
    [timestamp] INFO  [org.jboss.as.server.deployment] (MSC service thread 1-4) JBAS015877: Stopped deployment QuoteSearchTest.war in 76ms
    ...
    [timestamp] INFO  [org.jboss.as.server] (management-handler-thread - 5) JBAS018558: Undeployed "QuoteSearchTest.war"

Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following commands to pull them into your local repository. The IDE should then detect them.

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
