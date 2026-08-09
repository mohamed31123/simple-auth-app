# Simple Authentication App : it is a simple App to get familiar with authentication basics .
---
## What is authentication : Authentication is a process of defining who you are ?
---
### What steps should I follow to achieve this simple app :

#### 1 : create a simple user entity :
#### 2 : add a RegisterRequest dto so the user can create he can authenticate .
#### 3 : add a LoginRequest dto so the user can Login  ,
#### 4 : add a repository and a AuthSevice with two methods Login and Register they return both an AuthResponse which you must add as Response dto .
#### 5 : add an AuthServiceImpl that implement AuthService and define its methods.
#### 6 : add JwtService to genereateToken and valid if is it expired or not and so on .
#### 7 : make sur to add A SecurityConfig ,
#### 8 : Add an AuthController to your app to treat the http request 

### More detailed : 
---
## What is the role of the user entity ?
user entity represent the user table in your database .
### why using dto :
DTO stand for data transfer object its main role is to transfer the data instead of interacting directely with the entity to protect the senesitive data like possword for exemple .


