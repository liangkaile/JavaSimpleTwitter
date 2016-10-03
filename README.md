User Story:
As part of enhancing our internal services which are available to our employees, we would like to build a Twitter like solution for our employees, where employees can tweet and have followers.

High Level Requirements :
* We have 10K employees
* Employees can follow their colleagues, post (or tweet) messages to their followers
* Use corporate LDAP for user Management
* On an average, every employee will send approximately 10 messages a day to their followers
* On the home page we need to show 100 most recent tweets.
* Optionally you can support pagination.
* You are welcome to assume unspecified requirements to make it better for the customers
* Come prepared with High level Architecture and Design.
* You are expected to explain the rationale for your choice of technologies and architectural patterns


Programming Problem:
In the above exercise, please build a RESTful service
* /feed : To list 100 recent tweets for the logged in user
* In memory database is sufficient. Optionally, you are welcome to use a persistent data store of your choice.
* You are encouraged but not required to take advantage of a service code-generation framework of your choice when performing this exercise

Framework:
1 Jersey: https://jersey.java.net/

Installation:
1) mvn install

Run test:
1) mvn test

Running the project:
1) deploy war file to tomcat

Sample commands:
1) create user
curl -X POST -H "Content-Type: application/json" -d '{
  "name": "intuit",
  "email": "intuit@gmail.com",
  "activated": true,
  "followNames": []
}' "http://localhost:8080/rest/users"

curl -X POST -H "Content-Type: application/json" -d '{
  "name": "kaile",
  "email": "kaile@gmail.com",
  "activated": true,
  "followNames": ["intuit"]
}' "http://localhost:8080/rest/users"


2) list user -- user id is available in response
curl http://localhost:8080/rest/users

3) add new feed
curl -X POST -H "Content-Type: application/json" -d '{
  "body": "hello world",
  "user": "intuit"
}' "http://localhost:8080/rest/feed"

curl -X POST -H "Content-Type: application/json" -d '{
  "body": "hello intuit",
  "user": "intuit"
}' "http://localhost:8080/rest/feed"

4) list feed
curl -X GET -H "Content-Type: application/json" -H "user: kaile" "http://localhost:8080/rest/feed"