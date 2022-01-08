# Legbook API
Spring API for Legbook(Clone of facebook) Project 

# Intro #
A Social media site where user can
- [x] Register/Login(Used JWT)
- [x] Send Confirmation mail to register user(Used spring mail)
- [x] Create Posts
- [x] Create Comments
- [x] Like/Dislike Posts,Comment
- [x] Upload image in posts(Used S3)
- [x] Add Projection in API
- [x] Chat module(Used websockets)
- [x] Save previous chat to database
- [ ] Friends module(User can send friend request and accept/block)



# Technologies used #

* Java 11 
* MySQL 8
* Angular 11
* AWS S3-For storing images,videos

# Steps to install #
* 1-Download project,open your IDE and install necessary packages
* 2-Install MySQL and create database named **legbook**
* 3-Create AWS account and create bucket in S3.Copy bucket id and secret and place them in your environment variable named **s3keyid** and **s3keysecret**
* 4-Write necessary information regarding port,id and smtp password in application.properties file
* 5-Run Spring and angular project
