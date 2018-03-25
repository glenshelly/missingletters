# Missing Letters

This application determines which letters are missing from a given input string.

Full requirements for this "Missing Letters" assignment are contained in the 'BackendCodeTest' pdf in this directory.


Building and Running
====================

Assumptions 
-----------
The following software is assumed to be installed and available for the building and running of this application
- Java 8
- Maven, with junit dependency
 

Building the application with Maven
-----------------------------------
Run the maven package phase or install phase from the root of the application:
```angular2html
   > mvn clean package
```

This will compile the code and create the following jar file: ./target/missing_letters.jar


Running the unit tests
----------------------
You can run the included unit tests using the standard maven test phase:
```$xslt
   > mvn clean test
```


Running the application
-----------------------
To run the application on a single given string, execute the missing_letters jar with a input string parameter
```$xslt
   > java  -jar  target/missing_letters.jar  {input-string-parameter}
```

For example, to test a string such as "alert brown dog," 
run the application as follows (using quotes to surround any input with spaces): 
```angular2html

   > java -jar target/missing_letters.jar "alert brown dog"
```


Generating and Testing Large Strings
------------------------
A helper class called StringSubmitter (in com.glen.missingletters.generator) can be used to generate
Very Large Strings for testing.   Adjust the variable values at the start of the main() method to 
vary the content, size and quantity of the auto-generated and submitted strings.

Using this approach on a basic development laptop provided the following preliminary benchmark: 
a 100,000,000 character String took between 1 and 2 seconds to process.
(Time varied based on, among other things, the number of missing letters, with less missing-etters 
resulting in a faster processing time.)



Notes on the Implementation
===========================

Assumptions regarding the requirements
--------------------------------------

1. An input of null to the getMissingLetters() method of the MissingLetters.java class 
will be treated the same as an input of ""

Likely Improvements in real life
--------------------------------
Depending on the actual usage for this (A one-off script?  A production-grade application?), the
following improvements might be appropriate:

- Spreading the chunks across various threads, as described in the comments of MissingLetters.java
- Use a properties file for such things as chunkSize
- Add additional unit testing 
- Use a real logging system (e.g., log4j)
- An cache configured to hold and return frequently requested Strings, or recently requested strings.  (The cost vs. 
benefit of this would entirely depend on usage patterns.)



