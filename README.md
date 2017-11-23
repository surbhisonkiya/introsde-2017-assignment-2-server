# introsde-2017-assignment-2-server

Surbhi Sonkiya

Email-id: surbhi.sonkiya@studenti.unitn.it

Worked Alone.

Server URL: https://activityperson.herokuapp.com/

Server github repository: https://github.com/surbhisonkiya/introsde-2017-assignment-2-server.git

Client github repository: https://github.com/surbhisonkiya/introsde-2017-assignment-2-client.git

*********************************************************************************************************************************

# Project Description: 

This project provides a system to the University to understand the preferences of their students, that can help them register students and their preferred activities. Firstname, lastname, birthdate of the students needs to be defined and stored. About the activities they need to register name, description, type of activity, place, start date/time. The University needs to add, read, modify and delete the information via Web (REST Services). Additionally, the results needs to be saved in a database.

About the code:

I have used SQLite database (name of the database "activitystudentDB"). Person and Activity tables are implemented through Java Persistence API. In src folder, Person.java and Activity.java implements these tables respectively. Activity types is implemented in an enum class. Hence, it is not stored as a table in the database. If the code is run locally, using App.java class, it implements the server on localhost port:5903.

Below are the list of tasks that are implemented:

Request#0: DATABASE INIT an initialization process at deployment should create 5 new persons to initialise the database and return the newly created persons including at least one activity preference per person.

Request#1: GET /person should list all the people (see above Person model to know what data to return here) in your database (wrapped under the root element "people").

Request#2: GET /person/{id} should give all the personal information plus related information (e.g., activity preferences and tags).

Request#3: PUT /person/{id} should update the personal information of the person identified by {id} (e.g., only the person's information, not activity preferences).

Request#4: POST /person should create a new person and return the newly created person with its assigned id (if a activity profile is included, create them for the new person).

Request#5: DELETE /person/{id} should delete the person identified by {id} from the system.

Request#6: GET /activity_types should return the list of activity_types your model supports in XML and JSON formats.

Request#7: GET /person/{id}/{activity_type} should return the list of activities of {activity_type} (e.g. Social) for person identified by {id}.

Request#8: GET /person/{id}/{activity_type}/{activity_id} should return the activities of {activity_type} (e.g. Social) identified by {activity_id} for person identified by {id}.

Request#9: POST /person/{id}/{activity_type} should save a new value for the {activity_type} (e.g. Social) of person identified by {id} and archive the old value in the history.
        
Request#10: PUT /person/{id}/{activity_type}/{activity_id} should update the value for the {activity_type} (e.g., Social) identified by {activity_id}, related to the person identified by {id}.

Request#11: GET /person/{id}/{activity_type}?before={beforeDate}&after={afterDate} should return the activities of {activity_type} (e.g., Social) for person {id} which {start_date} is in the specified range of date. (Date format should be: "yyyy-MM-dd'T'HH:mm:ss").

*********************************************************************************************************************************

# Execution: 

1) Create an account on Heroku (URL: https://www.heroku.com/) and create an app in the Heroku (can create after logging into your Heroku account from the browser). I have created an app named "activityperson".

2) Clone the server github repository to your local machine -

        Server github repository: https://github.com/surbhisonkiya/introsde-2017-assignment-2-server.git

3) In the terminal, navigate to the path where you have cloned the repository on your local machine. 

4) Build the war file from build.xml. Execute below command in the terminal. It creates war with name "sde-assignment-02-server.war".

         ant create.war
         
5) Login to your heroku account from the terminal.

6) Deploy the newly created war on heroku. Use below command to do so -

        heroku war:deploy sde-assignment-02-server.war --app activityperson --include activitystudentDB.sqlite

7) In the browser, execute the below mentioned database_init to initialize the database on the server -
     
         https://activityperson.herokuapp.com/database_init


8) Server is ready and database is intialized. Server operations (HTTP requests) could be performed to verify the output.

*********************************************************************************************************************************

# Additional Notes: 

Please do step 8 mentioned under Execution part. If not done, database will not be initialised and server and it's operations will throw an error.


