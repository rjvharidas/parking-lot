package com.rjv.parkinglot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParkingLotProcessor {

    private ArrayList<Integer> availableSlotList;
    private Map<String, Car> slots;
    private Map<String, String> regNums;
    private Map<String, ArrayList<String>> colors;

    public int getNumParkingLot() {
        return numParkingLot;
    }

    public ArrayList<Integer> getAvailableSlotList() {
        return availableSlotList;
    }

    private int numParkingLot = 0;

    public void createParkingLot(String count) {
        addNumOfSlots(count);
        createSlots();
        initMaps();
        System.out.println("Created " + count + " parking slots...\n");
    }

    private void addNumOfSlots(String count) {
        try {
            numParkingLot = Integer.parseInt(count);
        } catch (Exception e) {
            System.out.println("Invalid Entry\n");
        }
    }

    private void initMaps() {
        slots = new HashMap<>();
        regNums = new HashMap<>();
        colors = new HashMap<>();
    }

    private void createSlots() {
        availableSlotList = new ArrayList<>();
        IntStream.rangeClosed(1, this.numParkingLot).forEach(i -> availableSlotList.add(i));
    }

    public void park(String regNo, String color) {
        if (getNumParkingLot() == 0) {
            System.out.println("Opps!!.. Parking lot is not available\n");
        } else if (this.slots.size() == getNumParkingLot()) {
            System.out.println("Opps!!.. Parking slots are full\n");
        } else {
            allocateParkingLot(regNo, color);
        }
    }

    private void allocateParkingLot(String regNo, String color) {
        Collections.sort(availableSlotList);
        String slot = availableSlotList.get(0).toString();
        Car car = new Car(regNo, color);
        slots.put(slot, car);
        regNums.put(regNo, slot);
        if (colors.containsKey(color)) {
            addRegNumColor(regNo, color);
        } else {
            addColor(regNo, color);
        }
        System.out.println("Parked at slot number " + slot + "\n");
        availableSlotList.remove(0);
    }

    private void addColor(String regNo, String color) {
        ArrayList<String> regNoList = new ArrayList<>();
        regNoList.add(regNo);
        colors.put(color, regNoList);
    }

    private void addRegNumColor(String regNo, String color) {
        ArrayList<String> regNoList = colors.get(color);
        colors.remove(color);
        regNoList.add(regNo);
        colors.put(color, regNoList);
    }

    public void leave(String slotNo) {
        if (getNumParkingLot() == 0) {
            System.out.println("Opps!!.. Parking lot is not available\n");
        } else if (slots.size() > 0) {
            leaveSlot(slotNo);
        } else {
            System.out.println("Parking lot is empty\n");
        }
    }

    private void leaveSlot(String slotNo) {
        Car carToLeave = slots.get(slotNo);
        if (carToLeave != null) {
            slots.remove(slotNo);
            regNums.remove(carToLeave.getRegNo());
            ArrayList<String> regNoList = colors.get(carToLeave.getColor());
            if (regNoList.contains(carToLeave.getRegNo())) {
                regNoList.remove(carToLeave.getRegNo());
            }
            availableSlotList.add(Integer.parseInt(slotNo));
            System.out.println("Slot number " + slotNo + " is free\n");
        } else {
            System.out.println("Slot number " + slotNo + " is already empty\n");
        }
    }

    public void status() {
        if (getNumParkingLot() == 0) {
            System.out.println("Opps!!.. Parking lot is not available");
            System.out.println();
        } else if (slots.size() > 0) {
            System.out.println("Slot No.\tRegistration No.\tColor");
            IntStream.rangeClosed(1, this.numParkingLot).forEachOrdered(this::printStatus);
            System.out.println();
        } else {
            System.out.println("Parking lot is empty");
            System.out.println();
        }
    }

    private void printStatus(int i) {
        String key = Integer.toString(i);
        if (slots.containsKey(key)) {
            Car car = slots.get(key);
            System.out.println(i + "\t" + car.getRegNo() + "\t" + car.getColor());
        }
    }

    public void getRegistrationNumbersFromColor(String color) {
        if (getNumParkingLot() == 0) {
            System.out.println("Opps!!.. Parking lot is not available\n");
        } else if (colors.containsKey(color)) {
            ArrayList<String> regNums = colors.get(color);
            System.out.println();
            IntStream.range(0, regNums.size()).forEachOrdered(i -> printRegNumByColor(regNums, i));
        } else {
            System.out.println("Not found\n");
        }
    }

    private void printRegNumByColor(ArrayList<String> regNums, int i) {
        if (!(i == regNums.size() - 1)) {
            System.out.print(regNums.get(i) + ",");
        } else {
            System.out.print(regNums.get(i));
        }
    }

    public void getSlotNumbersFromColor(String color) {
        if (getNumParkingLot() == 0) {
            System.out.println("Opps!!.. Parking lot is not available\n");
        } else if (colors.containsKey(color)) {
            ArrayList<String> regNoList = this.colors.get(color);
            ArrayList<Integer> slotList;
            System.out.println();
            slotList = getSlots(regNoList);
            IntStream.range(0, slotList.size()).forEachOrdered(j -> printSlotsByColor(slotList, j));
            System.out.println();
        } else {
            System.out.println("Not found\n");
        }
    }

    private void printSlotsByColor(ArrayList<Integer> slotList, int j) {
        if (!(j == slotList.size() - 1)) {
            System.out.print(slotList.get(j) + ",");
        } else {
            System.out.print(slotList.get(j));
        }
    }

    private ArrayList<Integer> getSlots(ArrayList<String> regNoList) {
        ArrayList<Integer> slotList;
        slotList = regNoList.stream().map(s -> Integer.valueOf(regNums.get(s))).sorted().collect(Collectors.toCollection(ArrayList::new));
        return slotList;
    }

    public void getSlotNumberFromRegNo(String regNo) {
        if (getNumParkingLot() == 0) {
            System.out.println("Opps!!.. Parking lot is not available\n");
        } else if (regNums.containsKey(regNo)) {
            System.out.println(regNums.get(regNo));
        } else {
            System.out.println("Not found\n");
        }
    }
}
