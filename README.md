# Thunes Calculator3

This project is in fulfillment of Thunes Software Developer interview.
Calculator3 is a Java Dynamic Web Application that performs the following 

1. Perform basic calculations (add, subtract, multiple, divide).
2. Perform factorial calculation.
3. In order to eliminate any potential bugs in the software, Calculator3 includes automated tests 
written and run to ensure the integrity of the application.All unit tests are written using JUnit 5.
4. Calculator3 triggered and interacted with on the CLI.
5. Calculator3 can also be triggered and run via a web API
6. Calculator3 also comes bundled with a an inbuilt xml converter with a simple helpful click-and-go
bash script that allows non-techie users to perform calcutions and keep track of their figures in a portable
csv file compatible with MS Office and LibreOffice suite.  

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

1. Clone this repository to your workspace 
 	git clone https://github.com/kiptubei/Calculator3.git
 	git pull origin master

2. Import project into Eclipse to view and run code from the IDE
3. Modify calculator.sh script at <TOMCAT_PATH="http://localhost:8080">, change path to match youtcr tomcat installation
4. To run application without eclipse ,deploy the war file Calculator3.war to your tomcat server (or web server of your choice though this has not been tested on any other installation apart from Apache Tomcat 9.0)
5. From terminal cd to the project folder ,test your application by running calculator.sh  e.g ./calculator.sh it should generate a file
in the format <x2_{$SYSTEM_TIME}.csv> 

6. To access source code for CalculatorXML checkout
	git clone https://github.com/kiptubei/Calculator3.git
 	git pull origin master


### Prerequisites

Java 8+ JRE/JDK
Google Chrome /Mozilla Firefox
Bash
*For developers
Eclipse 2019-03
Apache Tomcat v9.0
Jersey 2.30
Gradle 6.2-rc-2
JUnit 5

#### Running Calculator3
Calculator 3 takes only 4 commands:
 "add","sub","mul","div","fac" for factorial 

Each of which in-turn takes only two parameters value1 and value2 (Factorial only takes one)

Once deployed succesfully  to your web server of choice  (Apcahe ,JBoss, Glassfish )there are 3 ways to run Calculator3

1. Web Browser
2. CLI
3. calculator.sh script  (recommended)

	Web Browser
To run from web browser simply enter the following uri

Addition
<http://localhost:8080/Calculator3/start/calculate/add/3/3>

Subtract
<http://localhost:8080/Calculator3/start/calculate/sub/5/3>

Multiply
<http://localhost:8080/Calculator3/start/calculate/mul/8/6>

Divide
<http://localhost:8080/Calculator3/start/calculate/div/768/7>

Factorial
<http://localhost:8080/Calculator3/start/calculate/fac/35>

	CLI

To use the terminal or shell we use the utility curl to accept uri's

<curl http://localhost:8080/Calculator3/start/calculate/add/10/30> 
 
hint: you could pipe the output to html2text utility to remove xml tags

	calculator.sh

This is the easiest and safest way to use Calculator3

simply execute claculator.sh and pass it the right arguments
e.g
./calculator.sh add 3 4
./calulator.sh mul 4 5
./calulator.sh fac 34

for help options
./calculator.sh -h


If you have a lot of data to calculate and save you can use calculator.sh

modify these section as needed,
TOMCAT_PATH="http://localhost:8080"

curl $TOMCAT_PATH/$APP_PATH/$FAC | cut -c 56- >>  x3cut.xml 
#curl $TOMCAT_PATH/$APP_PATH/$MUL | cut -c 56- >>  x3cut.xml
#curl $TOMCAT_PATH/$APP_PATH/$ADD | cut -c 56- >>  x3cut.xml 


you can add as many computations as you like 
execute it ./calculator.sh

it will generate a csv file for you in the project path called data.csv with all you data

 
## Running the tests

All the tests for the Calculator3 web api have been written with JUnit 5 
They can be executed from Eclipse IDE ,using select test class run as > junit test

They test the following

*  Correctness of calculations,
*  Negative numbers
*  Wrong parameter options
*  0 as an input
*  Very large factorial

Code coverage for this release is about 65%

## Built With

* [Gradle 6.2-rc-2](https://gradle.org/) - Dependency Management
* [Jersey 2.30](https://eclipse-ee4j.github.io/jersey/) - JAX-RS implementation for json and xml
* [ Apache Tomcat v9.0 ](http://tomcat.apache.org/index.html)
* [ Eclipse 2019-03 4.11.0](https://www.eclipse.org/) - IDE
* [OpenJDK version 1.8.0_242]
* [ Ubuntu 18.04 LTS -64 bit]


## Authors

* **Mark James Kiptubei** - *Initial work* - [PurpleBooth](https://github.com/Kiptubei)


## License


## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc

