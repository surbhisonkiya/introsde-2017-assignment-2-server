# introsde-2017-assignment-2-server

Surbhi Sonkiya

Email-id: surbhi.sonkiya@studenti.unitn.it

Worked Alone.

Server URL: https://activityperson.herokuapp.com/

Server github repository: https://github.com/surbhisonkiya/introsde-2017-assignment-2-server.git

Client github repository: https://github.com/surbhisonkiya/introsde-2017-assignment-2-client.git

*********************************************************************************************************************************

Project: 

The University wants to understand better the preferences of their students, so they need a system that help them register Users and their preferred activities. Ideally they need to register firstname, lastname, birthdate of the students. About the activities they need to register name, description, type of activity, place, start date/time. The University needs to add, read, modify and delete the information via Web (REST Services). Additionally the results should be save in a database.


description of the project (2 paragraphs). 1 paragraph about the code, 1 paragraph about the tasks the code does.

*********************************************************************************************************************************

Execution: 

1) Clone the server github repository to your local machine -

        Server github repository: https://github.com/surbhisonkiya/introsde-2017-assignment-2-server.git

2) In the terminal, navigate to the path where you have cloned the repository on your local machine. 
3) Build the war file from build.xml. Execute below command in the terminal. It creates war with name "sde-assignment-02-server.war".

         ant create.war
         
5) Create an app in the heroku. I have created an app named "activityperson".
6) Login to your heroku account from the terminal.
7) Deploy the newly created war on heroku. Use below command to do so -

        heroku war:deploy sde-assignment-02-server.war --app activityperson --include activitystudentDB.sqlite

8) In the browser, execute the below mentioned database_init to initialize the database on the server -
     
         https://activityperson.herokuapp.com/database_init


9) Server is ready and other server operations could be performed.
*********************************************************************************************************************************

Additional Notes: 

Please do step 8 mentioned under Execution part. If not done, database will not be initialised and server and it's operations will throw an error.
