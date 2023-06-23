## Lead Engineer Programming Task
In order to be considered for the position, you must complete the following steps. We'll be paying special attention to your use of best practices and common coding conventions / patterns.         

*Note: This task should take no longer than a few hours for an experienced engineer.*
### Prerequisites
- Please note that this will require knowledge of:
  	- [JavaScript](http://www.codecademy.com/tracks/javascript)
	- [Angular](https://angular.io/)
	- HTML and CSS
  	- [Java](https://java.com)
  	- [Spring Boot](https://projects.spring.io/spring-boot/)
## Task
1. Create a new branch for your changes
2. Create a *be-source* directory to contain your backend project and a *fe-source* directory to contain your frontend project. 
3. Create a runnable Java 11 / Spring Boot 2.x project in the *be-source* directory with the following functionality:
	- it should run on port 8080
	- it should expose a REST JSON endpoint which when called
		- Connects to the [Github API](http://developer.github.com/)
		- Finds the [angular/angular](https://github.com/angular/angular) repository
		- Retrieves the most recent 25 commits
		- Returns the result
	- Include a README file in the root of the *be-source* directory with information on how to run the project
4. Create a runnable Angular project in the *fe-source* directory that accomplishes the following:
	- Connects to the above Spring Boot project API and hits the created endpoint
	- With the JSON result it should create a view that groups the retrieved commits by author in a list layout, one commit per line. This repository includes an [example screenshot (click on me)](example-list-cell.png) of what each cell should look like
	- Add a Refresh button to refresh the page. This refresh button should be written using CSS properties alone and it should be compatible with all modern browsers + IE11. [Click here](example-button.png) to see an example of what the button should look like.
	- Include a README file in the root of the *fe-source* directory with information on how to run the project
5. EXTRA CREDIT / BONUS: Run both the frontend and backend applications inside one or more Docker containers (if more than one, extra extra bonus for using docker-compose). Expose their ports so that the applications can be accessed from the host machine. Provide the docker command(s) in order to easily run both projects. Just to re-iterate, dockerizing the applications is not a requirement to be considered for the position.
6. Commit your code and push to the remote repository
7. Send us a pull request; we will review your code and get back to you

# GitHub Repo Commit Listing App

## Prerequisites

- [Docker Engine](https://docs.docker.com/engine/install/)
- [Docker Compose v2](https://docs.docker.com/compose/install/)
- Having a github account and create the access token and provide it to application properties file.

> ## Build steps

Thanks to the Docker Engine and Docker Compose, the build steps are so simple. Run the command:

```bash
docker compose pull
docker compose build
```

If the machine can't handle the concurrent image building, the images can be built one by one:

```bash
docker compose build api
docker compose build ui
```
## Run steps

Running the app is also easy. Just run the command:

```bash
docker compose up -d
```

This command will wait for the database to be ready, then will run other images. After all the images started, go to the link http://localhost/

## Stopping containers

Run this command to stop the containers:

```bash
docker compose down
```
