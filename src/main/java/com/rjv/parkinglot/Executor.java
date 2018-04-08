package com.rjv.parkinglot;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Executor {
    private static ParkingLotProcessor parkingLotProcessor = new ParkingLotProcessor();

    private Map<String, Method> entryKeyMap = initEntryKeyMap();

    private static Map<String, Method> initEntryKeyMap() {
        try {
            return Stream.of(
                    entry("create_parking_lot", ParkingLotProcessor.class.getMethod("createParkingLot", String.class)),
                    entry("park", ParkingLotProcessor.class.getMethod("park", String.class, String.class)),
                    entry("leave", ParkingLotProcessor.class.getMethod("leave", String.class)),
                    entry("status", ParkingLotProcessor.class.getMethod("status")),
                    entry("registration_numbers_for_cars_with_colour", ParkingLotProcessor.class.getMethod("getRegistrationNumbersFromColor", String.class)),
                    entry("slot_numbers_for_cars_with_colour", ParkingLotProcessor.class.getMethod("getSlotNumbersFromColor", String.class)),
                    entry("slot_number_for_registration_number", ParkingLotProcessor.class.getMethod("getSlotNumberFromRegNo", String.class)))
                    .collect(Collectors.toConcurrentMap(AbstractMap.SimpleEntry::getKey, AbstractMap.SimpleEntry::getValue));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static AbstractMap.SimpleEntry<String, Method> entry(String key, Method value) {
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    private void invalidMsg() {
        System.out.println("Invalid Entry");
    }

    private Method getMethod(String inputString) {
        return entryKeyMap.get(inputString);
    }

    public void executeFileInput(String filePath) {
        File inputFile = new File(filePath);
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            try {
                processFile(br);
            } catch (IOException ex) {
                System.out.println("Invalid input file.");
                ex.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            e.printStackTrace();
        }
    }

    private void processFile(BufferedReader br) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            try {
                executeTextInput(line.trim());
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeTextInput(String inputString) throws InvocationTargetException, IllegalAccessException {
        String[] inputs = inputString.split(" ");
        Method method;
        switch (inputs.length) {
            case 1:
                method = getMethod(inputString);
                if (method != null) {
                    method.invoke(parkingLotProcessor);
                } else {
                    invalidMsg();
                }
                break;
            case 2:
                method = getMethod(inputs[0]);
                if (method != null) {
                    method.invoke(parkingLotProcessor, inputs[1]);
                } else {
                    invalidMsg();
                }
                break;
            case 3:
                method = getMethod(inputs[0]);
                if (method != null) {
                    method.invoke(parkingLotProcessor, inputs[1], inputs[2]);
                } else {
                    invalidMsg();
                }
                break;
            default:
                invalidMsg();
        }
    }
}
