# Missing Letters

This application finds letters missing from the input string


Building and Running
====================

Assumptions 
-----------
The following software is assumed to be installed and available for the building and running of this application
- Java 8
- Maven
 

Building the application with Maven
-----------------------------------
Run the maven package or install phase from the root of the application:

   > mvn clean package

This will create the following jar file: ./target/missing_letters.jar


Running the application
-----------------------
To run the application, execute the missing_letters jar with a single parameter to specify the location of the input files

   > java  -jar  target/missing_letters.jar  <input-file-location-parameter>

For example, if the input files are in '/data" beneath the directory you're running the application in,
run the application as follows: 

   > java -jar target/missing_letters.jar ./data 


Results Location
----------------
The results will overwrite the original values in slcsp.csv


Notes on the Implementation
===========================

Assumptions regarding the requirements
--------------------------------------
?

Likely Improvements in real life
--------------------------------
Depending on the actual usage for this (a one-off script?  a production-grade application?), the
following improvements might be appropriate:

- Use a properties file for such things as the input file location
- Add junit/jmockit tests that run automatically during the build
- Use a real logging system (e.g., log4j)



