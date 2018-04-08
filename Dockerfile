FROM java:8

COPY build/libs/parking-lot-1.0.jar Deployments/

COPY src/test/java/com/rjv/parkinglot/Sample_Input_File.txt Deployments/

CMD java -jar Deployments/parking-lot-1.0.jar file Deployments/Sample_Input_File.txt
