package com.rjv.parkinglot;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class ExecutorTest {

    @Test
    public void executeInput() throws Exception {
        Executor executor = new Executor();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        executor.executeTextInput("parkxx");
        assertEquals("InvalidEntry", outContent.toString().trim().replace(" ", ""));
        executor.executeTextInput("status");
        assertEquals("InvalidEntry\nOpps!!..Parkinglotisnotavailable", outContent.toString().trim().replace(" ", ""));
    }
}