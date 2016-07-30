# TSIDGenerator
This project aims to solve the common coding problem of creating IDs that are guaranteed to be universally unique at the time of their generation. The solution given by this code is used to serve as a more general and flexible version of UUIDs, allowing users to choose the length of their IDs (vs UUIDs which are of fixed length). The benefits of using the TSIDGenerator to provide unique IDs for your project are that the generated IDs have guaranteed universal uniqueness, are of variable length specified as input, are sufficiently obfuscated and appear to be randomly generated (important if security concerns are an issue), and have a very fast generation time even when created in large batches. 

## Code Example 

The TSIDGenerator can be used in your code as follows: 

    String newId = TSIDGenerator.nextId(24, false); // to create a new unique ID of length 24 characters
  
And some sample output of when the code is called to generate 5 IDs of length 24 characters in consecutive milliseconds:

    4BHSB6KIGQIUX0CD1PHWPD2E
    TB8ITAZ5E7RI34WSFWNAF541
    7PDWZTT93RLTQACX0EGKQXJ2
    P3ELS5BI757BFIJQ0UT89E0S
    H3A4EW4UOHXFZI73WSDOF23X
