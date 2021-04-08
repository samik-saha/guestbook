
## Features
* Any visitor can post their name and a comment to the Guestbook.
* All visitors can see a list of every entry in the Guestbook.

## API Specifications
Endpoint    | Method| Request Body| Response
------------|-------|-------------|----------
/guestbook  |  GET  |             |[{"name":"abc", "comment":"abcd"}, ...]
/guestbook  |  POST | {"name":"abc", "comment":""}|


## Instructions to run in Docker
Create custom network

    docker network create --driver bridge guestbook-net

Run postgres instance

    docker run --network guestbook-net -p 5432:5432 --name my-postgres -e POSTGRES_DB=guestbookdb -e POSTGRES_PASSWORD=open -d postgres

Build Docker Image

    docker build -t guestbook:dev .

Run application in Docker container

    docker run --name guestbook --network guestbook-net -e PORT=8080 -e SPRING_PROFILES_ACTIVE=docker -p 9000:8080 -d guestbook:dev


## Instructions for Deploying to Heroku

* Create a heroku app
* Add a postgres database addon
* Set environment variable `SPRING_PROFILES_ACTIVE = heroku`
* Login to heroku from cli
      
      heroku container:login
  
* Push Docker based app to heroku  

      heroku container:push -a <app_name> web

* Release heroku app

      heroku container:release -a <app_name> web


## Demo API URL
https://ss-guestbook.herokuapp.com/guestbook