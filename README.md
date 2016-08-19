# DataConsumer
The application is an implementation of the algorithm which consumes data from two channels and output them as pairs in the order
of arrival.

## Problem Statement

There are two separate infinite sources of data.  
 
Data is arriving "simultaneously" into the system as two streams via two channels: Channel 1 and Channel 2. 
     
The data could be of three types: R, G and B.  
 
Each data element should have two properties: channelNumber and uniqueID. 
 
These three types of Data are arriving in a random sequence on the two channels. 
 
The program creates pairs of "same types" arriving on two channels in their "order of arrival". 
 
### Example:
# If a sample sequence is as follows: 
  Channel 1: R1_1 R1_2 R1_3 B1_4 B1_8 G1_5 
  Channel 2: B2_6 B2_8 R2_9 G2_10 B2_7 R2_20 
 
# Output is: 
    (R1_1, R2_9) (B1_4, B2_6) (B1_8, B2_8) (G1_5, G2_10) (R1_2, R2_20) 
