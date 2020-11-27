# IMS Systems

This repo contains a system to allow the end user to access the database and edit files in a friendly and simple manner. They can add, update, delete and read from customers, items and orders in the database and calculate orders if need be.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

To run the file you will need to install the latest version of Java at the link bellow.

```
https://www.java.com/en/download/
```

Have a functioning MySQL Database set up on your localhost on the port: `3306`

### Installing

Navigate to where you have downloaded the jar file then right click and open bash

![open bash screenshot](https://github.com/FayazPanda/JavaProject/blob/main/images/tut1.png)

Then type **java -jar fat.jar** replacing the fat with whatever you might have renamed it to

![run command screenshot](https://github.com/FayazPanda/JavaProject/blob/main/images/tut2.png)

To test that the system is running, log in and you should be greeted with the following text:

![Entity usage Screenshot](https://github.com/FayazPanda/JavaProject/blob/main/images/tut3.png)

## Running the tests

The following section will explain the tests, what they are and how you can run them.

### Unit Tests 

Units tests are to check the functionality of code in a vaccum to see if they work on their own. These tests are set up in `...\src\test\java\com\qa\ims\persistence\dao` and can be run using JUnit with Maven.

### Integration Tests 

Intergration tests are to check the functionality of code when used with other aspects. These tests are setup in `...\src\test\java\com\qa\ims\controller` directory. These use JUnity and Mockito with Maven to run.

## Deployment

Deployment to live system requires constant update for the fat.jar system with every update of the system. Otherwise deployment is the same as installation.

## Future Development

For future development you will need to install maven, java and and IDE for Java(eclipse or Intellij recommended).

```
http://maven.apache.org/download.cgi
https://www.java.com/en/download/
https://www.eclipse.org/
```

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Project Management Board

The project was done in SCRUM methodology using jira to keep track of the tasks and bugs as it was coded.

![Jira Screenshot](https://github.com/FayazPanda/JavaProject/blob/main/images/jira.png)

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)
* **Fayaz Sheikh** - *Project development* - [FayazPanda](https://github.com/FayazPanda)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

## Acknowledgments

* **JHarry444** - Creating the base framework that my project is based off of.
* **Trainers(Piers B., Savannah V. & Edward R.)** - All the trainers who have given me help when I was stuck at one point or another.
