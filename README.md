About Parking Lot Problem

I own a multi­storey parking lot that can hold up to 'n' cars at any given point in time. Each slot is given a number starting at 1 increasing with increasing distance from the entry point in steps of one. I want to create an automated ticketing system that allows my customers to use my parking lot without human intervention. 

When a car enters my parking lot, I want to have a ticket issued to the driver. The ticket issuing process includes us documenting the registration number (number plate) and the colour of the car and allocating an available parking slot to the car before actually handing over a ticket to the driver (we assume that our customers are nice enough to always park in the slots allocated to them). The customer should be allocated a parking slot which is nearest to the entry. At the exit the customer returns the ticket which then marks the slot they were using as being available.

Due to government regulation, the system should provide me with the ability to find out:

a) Registration numbers of all cars of a particular colour.
b) Slot number in which a car with a given registration number is parked.
c) Slot numbers of all slots where a car of a particular colour is parked.

We interact with the system via a simple set of commands which produce a specific output. Please take a look at the example below, which includes all the commands you need to support - they're self explanatory. The system should allow input in two ways. Just to clarify, the same codebase should support both modes of input - we don't want two distinct submissions.

1) It should provide us with an interactive command prompt based shell where commands can be typed in
2) It should accept a filename as a parameter at the command prompt and read the commands from that file


Prerequisites
=============
* Java 1.8
* Gradle 2.0+
* Junit 4.12+

Building
========

From IDE (Eclipse, Intellij)
----------------------------

Open as a Gradle project and compile.

From Command Line
-----------------

    ./gradlew clean build

Running the Application
======================

#1 File Input

To run the program:
#####java -jar build/libs/parking-lot-1.0.jar file [filepath]

sample: java -jar build/libs/parking-lot-1.0.jar file /Users/A-5605/workspace/parking-lot/src/test/java/com/rjv/parkinglot/Sample_Input_File.txt

in file:

```sh
create_parking_lot 6
park KL­11­CC­5534 White
park KL­07­CB­4455 Black
park KL­15­FD­4321 Red
park KL­07­CB­4255 Black
park KL­07­CB­6455 Green
leave 4
status
park KL­14­FD­4321 White
park KL­10­FD­4321 White
registration_numbers_for_cars_with_colour White
slot_numbers_for_cars_with_colour White
slot_number_for_registration_number KL­07­CB­6455
slot_number_for_registration_number GA­01­AA­4455
```

Response:

```sh
Created 6 parking slots...

Parked at slot number 1

Parked at slot number 2

Parked at slot number 3

Parked at slot number 4

Parked at slot number 5

Slot number 4 is free

Slot No.	Registration No.	Color
1	KL­11­CC­5534	White
2	KL­07­CB­4455	Black
3	KL­15­FD­4321	Red
5	KL­07­CB­6455	Green

Parked at slot number 4

Parked at slot number 6


KL­11­CC­5534,KL­14­FD­4321,KL­10­FD­4321
1,4,6
5
Not found
```

#2 Console

To launch the console run  : 
#####java -jar build/libs/parking-lot-1.0.jar console

Try the above commands one by one in terminal.

#3 Try Docker
Build docker using below command:
####docker build -t parking-lot . 
And Run Docker conatiner 
####docker container run parking-lot
Now you can see the file inputs are working as expected.
