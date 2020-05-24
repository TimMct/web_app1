# web app

## ProBook : where professionals meet

This repo contains a web app named Probook, because it's inspired from FaceBook, but it's not the same thing (it doesn't have all the responsibilities).

There are 3 kinds of user categorised by their job: engineers, medics and teachers. Each user can add another user as friend. Also, they can add pictures and like other pictures. If a user has a picture liked by someone else then that user will be notified.

Design patterns:  
* factory is used to make 3 kinds of user for the app.
* observer is used when a user likes another's user photo.
* facade is used for tests.


### implementation

This app was made with the SpringBoot API and it basically persists data with Hibernate API. These data will be used for login of users.
The User Interface is not yet finished. Till now I implemented the logic of adding a user or a picture in the database alongside with adding a friend to a user and letting a user like another's user pictures. There are classes of controller, of service and of repository for both User and Picture. 

### diagram  

This diagram describes the relations between the main classes of this application. A user can have zero or any number of pictures, hence ther's a one-to-many relationship and also a picture has a owner so the relation is bilateral. A user can have zero or any number of friends and some friends can have the same user as their friend. Since a friend is basically another user, we have here a many-to-many relation for the same class (recusrion). A user must have a LikeObserver which sees if the user has been liked by another user. Then the LikeObserver creates a notification for the user (this will hang somewhere on the web page). Also, a picture must keep track of the users that liked it so this way, a user cannot like a picture multiple times. Hence, a picture has a list of users who liked that picture.   

<img src="new_diagram.png"
     alt="diag"
     style="float: left; margin-right: 10px;" />

### observer pattern

When a user likes another's user picture, the second user will have a new notification from the LikeObserver. This notification is made in the moment when the picture gets liked for the first time (and the only one). Basically, each user has a string for notifications. In the beggining this string is empty and when the moment comes the string takes a form of notification says to the current user that he/she has a new like from another user. Each user has it's own LikeObserver so the surveillance doesn't get messed up, because a LikeObserver deals with only one user.  

### factory pattern

The factory pattern is used to created a user in a specific manner, hence by a type. I thought that grouping users by their job is a good ideea so I thought that the most important jobs (especially nowadays) are these: engineers, medics and teachers. The way the factory choses to create an user or another is by a string. This string specifies which type of user we want to have. For the moment the 3 subclasses of user don't have a special attribute.  

### sequence diagram  
This sequence diagram describes two flows: the flow of creating a user and the flow of adding a friend (user) to an existing user.  
<img src="sequence_diag.png"
     alt="diag"
     style="float: left; margin-right: 10px;" />

