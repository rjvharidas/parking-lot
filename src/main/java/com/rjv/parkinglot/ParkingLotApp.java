package com.rjv.parkinglot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;

public class ParkingLotApp {

    public static void main(String[] args) {
        Executor executor = new Executor();
        switch (args[0]) {
            case "console":
                executeConsoleInput(executor);
                break;
            case "file":
                executor.executeFileInput(args[1]);
                break;
            default:
                invalidEntryMsg();
        }
    }

    private static void executeConsoleInput(Executor executor) {
        System.out.println("*********** PARKING-LOT SHELL **************");
        System.out.println("----------- ----------------- --------------");
        System.out.println("Please Enter Commands('q'-> for quit)...");
        while (true) {
            if (takeInputs(executor)) break;
        }
    }

    private static boolean takeInputs(Executor executor) {
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String inputString = bufferRead.readLine();
            if (execute(executor, inputString)) return true;
        } catch (InvocationTargetException e) {
            invalidEntryMsg();
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            invalidEntryMsg();
            e.printStackTrace();
        } catch (IOException e) {
            invalidEntryMsg();
            e.printStackTrace();
        }
        return false;
    }

    private static boolean execute(Executor executor, String inputString) throws InvocationTargetException, IllegalAccessException {
        if (inputString.equalsIgnoreCase("q")) {
            return true;
        } else if ((inputString == null) || (inputString.isEmpty())) {
        } else {
            executor.executeTextInput(inputString.trim());
        }
        return false;
    }

    private static void invalidEntryMsg() {
        System.out.println("InValid Entry, Please enter the right command");
    }
}
