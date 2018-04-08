package com.rjv.parkinglot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ParkingLotProcessorTest {

    private ParkingLotProcessor parkingLotProcessor;
    private ByteArrayOutputStream outContent;

    @Before
    public void setUp() {
        parkingLotProcessor = new ParkingLotProcessor();
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void clean() {
        System.setOut(null);
    }

    private String getReplace() {
        return outContent.toString().trim().replace(" ", "");
    }

    @Test
    public void createParkingLot() throws Exception {
        parkingLotProcessor.createParkingLot("4");
        assertEquals(4, parkingLotProcessor.getAvailableSlotList().size());
        assertTrue("Created4parkingslots...".equalsIgnoreCase(getReplace()));
    }

    @Test
    public void status() throws Exception {
        parkingLotProcessor.status();
        assertEquals("Opps!!..Parkinglotisnotavailable", getReplace());
        parkingLotProcessor.createParkingLot("6");
        parkingLotProcessor.park("KL-14-AD-3433", "Red");
        parkingLotProcessor.park("AA-22-SH-339", "White");
        parkingLotProcessor.status();
        assertEquals("Opps!!..Parkinglotisnotavailable\n" +
                "\n" +
                "Created6parkingslots...\n" +
                "\n" +
                "Parkedatslotnumber1\n" +
                "\n" +
                "Parkedatslotnumber2\n" +
                "\n" +
                "SlotNo.\tRegistrationNo.\tColor\n" +
                "1\tKL-14-AD-3433\tRed\n" +
                "2\tAA-22-SH-339\tWhite", getReplace());
    }

    @Test
    public void leave() throws Exception {
        parkingLotProcessor.leave("2");
        assertEquals("Opps!!..Parkinglotisnotavailable", getReplace());
        parkingLotProcessor.createParkingLot("4");
        parkingLotProcessor.park("KL-14-AA-5566", "Red");
        parkingLotProcessor.park("KL-01-CA-1256", "White");
        parkingLotProcessor.leave("4");
        assertEquals("Opps!!..Parkinglotisnotavailable\n" +
                "\n" +
                "Created4parkingslots...\n" +
                "\n" +
                "Parkedatslotnumber1\n" +
                "\n" +
                "Parkedatslotnumber2\n" +
                "\n" +
                "Slotnumber4isalreadyempty", getReplace());
    }

    @Test
    public void getSlotNumbersFromColor() throws Exception {
        parkingLotProcessor.getSlotNumbersFromColor("White");
        assertEquals("Opps!!..Parkinglotisnotavailable", getReplace());
        parkingLotProcessor.createParkingLot("6");
        parkingLotProcessor.park("KL-02-CA-4455", "White");
        parkingLotProcessor.park("KL-12-AA-3344", "White");
        parkingLotProcessor.getSlotNumbersFromColor("White");
        assertEquals("Opps!!..Parkinglotisnotavailable\n" +
                "\n" +
                "Created6parkingslots...\n" +
                "\n" +
                "Parkedatslotnumber1\n" +
                "\n" +
                "Parkedatslotnumber2\n" +
                "\n" +
                "\n" +
                "1,2", getReplace());
        parkingLotProcessor.getSlotNumbersFromColor("Red");
        assertEquals("Opps!!..Parkinglotisnotavailable\n" +
                "\n" +
                "Created6parkingslots...\n" +
                "\n" +
                "Parkedatslotnumber1\n" +
                "\n" +
                "Parkedatslotnumber2\n" +
                "\n" +
                "\n" +
                "1,2\n" +
                "Notfound", getReplace());
    }

    @Test
    public void park() throws Exception {
        parkingLotProcessor.park("KL-14-AA-5566", "Red");
        parkingLotProcessor.park("KL-01-CA-1256", "White");
        assertEquals("Opps!!..Parkinglotisnotavailable\n" +
                "\n" +
                "Opps!!..Parkinglotisnotavailable", getReplace());
        parkingLotProcessor.createParkingLot("6");
        parkingLotProcessor.park("KL-14-AA-5566", "Red");
        parkingLotProcessor.park("KL-01-CA-1256", "White");
        assertEquals(4, parkingLotProcessor.getAvailableSlotList().size());
    }
}