# my-curriculum
A cloud-native application for managing the curriculum and syllabi handbooks of degree programs with Spring Boot.  
This fork now runs independently and will no longer update from turncodr/my-curriculum.

## Getting Started

1. Install JDK 8 (or later).

1. Install git.

1. If you don't have a GitHub-Account yet, create one [here](https://github.com/) and fork this repository.

1. Clone your forked repository via `git clone https://github.com/yourgithubname/my-curriculum.git`

1. Add the remote repository *upstream* via `git remote add upstream https://github.com/bestyan/my-curriculum.git`

1. Start the application by navigating to the project folder and executing `./mvnw spring-boot:run`  
If you have Maven installed you can also use `mvn spring-boot:run`.

## Persisting data into db

If you want to persist data into a db, you should start the application with the spring profile 'with_db'.
This profile enables the DatabaseConfiguration class which configures the jdbc driver and connects to a mysql db. 
When you start the application with the db profile make sure that mysql is running, otherwise will encounter some exceptions and the application won't start. 