# README #

### What is this repository for? ###

This project is a task for the company N26 entry process, this file will help you set up your project and start running it.  

### How do I get set up? ###

* You first need to have Apache Maven(version 3.0 or higher) and a Java SDk(version 8 or higher) installed
* **Compile the project using:** mvn install
* **Run the tests with:** mvn test
* **Start your application:** mvn spring-boot:run

### Apis documentation ###
The apis are all REST webservices and are designed with RESTFUL standard.
The details are on the specification.

### TODO ###
- Setup a logger
- optimize reads from the storaged transactions (it's ordered, there are smarter ways to remove expired transactions)
- Finish the O(1) resolution


### Who do I talk to? ###

* The author : Hugo Marello
* Send your feedback and questions to hugo.marello@gmail.com
