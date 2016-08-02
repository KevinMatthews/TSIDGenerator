# TSID-Generator
The TSID-Generator is a lightweight and flexible tool that can be used to generate IDs in any situation, no matter how strict the ID requirements are for your project. This project aims to solve the common coding problem of creating IDs that are guaranteed to be universally unique at the time of their generation. The solution given by this code is used to serve as a more general and flexible version of UUIDs, allowing users to choose the length of their IDs (vs UUIDs which are of fixed length). The benefits of using the TSIDGenerator to provide unique IDs for your project are that the generated IDs have guaranteed universal uniqueness, are of variable length specified as input, are sufficiently obfuscated and appear to be randomly generated (important if security concerns are an issue), and have a very fast generation time even when created in large batches. 

## Code Example 

The TSIDGenerator can be used in your code as follows: 

    String newId = TSIDGenerator.nextId(24); // to create a new unique ID of length 24 characters
  
And some sample output of when the code is called to generate 5 IDs of length 24 characters in consecutive milliseconds:

    4BHSB6KIGQIUX0CD1PHWPD2E
    TB8ITAZ5E7RI34WSFWNAF541
    7PDWZTT93RLTQACX0EGKQXJ2
    P3ELS5BI757BFIJQ0UT89E0S
    H3A4EW4UOHXFZI73WSDOF23X
    
## Why You Should Use the TSID-Generator In Your Project

This tool was created to simplify the process of generating unique IDs in Java. It was intended to be used in a specific situation, namely, where the IDs must:

* Be universally unique
* Appear random and obfuscated
* Have a specific length
* Have a fast generation time

If your IDs meet all of the above criteria, then this tool was created specifically for you! However, this tool is general enough to be an easy-to-use and practical tool for ID generation in any situation. It is faster than most alternatives, as it avoids storing previously generated IDs in a database and performing a lookup to ensure uniqueness each time a new ID is generated. The IDs appear to be generated with complete randomness, which is important if security is a consideration in your project. Furthermore, the length of the IDs is specified by user instead of being a set length ID that may not fit your specific requirements. 

## How to Use The TSID-Generator

The TSID-Generator guarantees universal uniqueness of its generated IDs by using a combination of time and space to generate the IDs. Essentially, the current time-stamp is aggregated with a unique ID assigned to each Java Virtual Machine (JVM) that your project runs on, and then a number of random characters are appended in order to meet your length requirements. The input length of the IDs must be at least 8 characters, as this is the length of the time-stamp used. 

If your project will only ever generate IDs on one JVM at a time, then you may simply download the code and use begin generating unique IDs using the TSIDGenerator.nextID(int) method, as shown in the Code Example section of this README. However, if you intend to generate IDs on multiple separate JVMs simultaneously, you must implement the uniqueMachineId() method!!! This is crucial, as in order to guarantee uniqueness across multiple JVMs, a time-stamp as well as a unique JVM ID is needed. 

Implementation of this part of the code was left to the user, as implementations will vary depending on the user's Operating System and specific project setup, as well as other factors. 