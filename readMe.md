# web app

FakeBook

This repo contains a web app named FakeBook, because it's inspired from FaceBook, but it's not the same thing (it doesn't have all the responsibilities).

There are users who can post a picture of their own. These users can send friend request between each others and only if two users are friends then they can see all of their pictures (unless they can see only the main picture). Also, those who are friends can like other user's pictures.

There is also an admin who monitorises the activity of all the users (like when a user enters/leaves), and the admin can see the most liked pictures and the most popular user (the one with that has many friends).

Design patterns:  + factory is used to make 2 kinds of entities which work with the app (users and admin). 
                  + observer is used when a user sends a friend request to another user.
                  + facade ...


# implementation

Till now there are 2 main classes: User and Picture. Between these classes there is a one-to-many relationship. A user can have many pictures, but a picture can have only one owner (user).
