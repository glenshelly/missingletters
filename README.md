# Missing Letters

This application finds letters missing from the input string, and returns them.

Full requirements are in the 'BackendCodeTest' pdf in this directory.


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
```angular2html

   > mvn clean package
```

This will create the following jar file: ./target/missing_letters.jar


Running the unit tests
----------------------
Run the included unit tests using the standard maven test phase:
```$xslt
> mvn test
```


Running the application
-----------------------
To run the application on a single given string, execute the missing_letters jar with a input string parameter
```xml

   > java  -jar  target/missing_letters.jar  <input-string-parameter>
```

For example, to test a string such as "alert brown dog," 
run the application as follows (using quotes to surround input with spaces): 
```angular2html

   > java -jar target/missing_letters.jar "alert brown dog"
```


Generating Large Strings
------------------------

A helper class called StringSubmitter (in com.glen.missingletters.generator) can be used to generate
Very Large Strings for testing.   Adjust the values at the start of the main() method to 
vary the contents, size and quantity of the auto-generated and submitted strings.

Using this approach revealed that a 100,000,000 character String took between 1 and 2 seconds to process.
Variations depended on the number of missing letters (less letters missing resulted in faster processing time);



Notes on the Implementation
===========================

Assumptions regarding the requirements
--------------------------------------

1. An input of null will be treated the same as an input of ""

Likely Improvements in real life
--------------------------------
Depending on the actual usage for this (a one-off script?  a production-grade application?), the
following improvements might be appropriate:

- Use a properties file for such things as chunkSize
- Spreading the chunks across various threads, as described in the comments of MissingLetter.java
  (depending on profile of usage, and results of performance profiling)
- Add additional unit 
- Use a real logging system (e.g., log4j)



