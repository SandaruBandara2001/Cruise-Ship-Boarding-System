package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        String[] cabins = new String[12];
        String[] BookedCabin = new String[12];

        initialise(cabins);
        boolean menuLoop = true;

        while (menuLoop) {

            System.out.println(".....Menu...... ");
            System.out.println("press 'A' to Add a customer to cabin ");
            System.out.println("press 'v' to View to all cabins ");
            System.out.println("press 'E' to View to empty cabins ");
            System.out.println("press 'D' to Delete customer from cabin ");
            System.out.println("press 'F' to Find cabin from customer name ");
            System.out.println("press 'S' to Store program data into file ");
            System.out.println("press 'L' to Load program data from file");
            System.out.println("press 'O' to View passengers Ordered alphabetically by name ");
            System.out.println("press '0' to Exit the program ");


            System.out.println("......Select from the menu....");
            Scanner input2 = new Scanner(System.in);
            String input1 = input2.nextLine();

            switch (input1) {
                case "a", "A" -> addCabin(cabins);

                case "v", "V" -> viewCabin(cabins);

                case "e", "E" -> emptyCabins(cabins);

                case "d", "D" -> removecust(cabins);

                case "f", "F" -> findCabin(cabins);

                case "s", "S" -> storeProData(cabins);

                case "l", "L" -> lordProData();

                case "o", "O" -> booked(cabins, BookedCabin);

                case "0" -> menuLoop = false;

                default -> System.out.println("invalid input");
            }
        }
    }

    public static void initialise(String[] filledCabin) {
        for (int i = 0; i < filledCabin.length; i++) {
            filledCabin[i] = "e";
        }
    }


    public static void emptyCabins(String[] cabin) {
        System.out.println("Display empty cabins ....");
        for (int i = 0; i < cabin.length; i++) {
            if (cabin[i].equals("e")) {
                int cabNum = i + 1;
                System.out.println("Cabin " + cabNum + ",");
            }
        }
    }

    public static void addCabin(String[] cabin) {
        int cabNum = 0;
        String custName;

        while (cabNum < 13) {
            emptyCabins(cabin);
            Scanner input = new Scanner(System.in);
            try {
                System.out.println("select a cabin from empty cabins.. if you want to exit program enter '0' ");
                cabNum = input.nextInt();
                cabNum = cabNum - 1;
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input is invalid Please try again ");
            }
            if (cabNum == -1) {
                break;
            } else if (cabNum < 12 && cabNum > -1) {
                System.out.println("please enter customer name...");
                custName = input.next();
                cabin[cabNum] = custName;
            }
        }
    }

    public static void viewCabin(String[] cabin) {
        int cabNum;
        for (int i = 0; i < cabin.length; i++) {
            if (cabin[i].equals("e")) {
                cabNum = i + 1;
                System.out.println("Cabin " + cabNum + " is empty...");
            } else {
                cabNum = i + 1;
                System.out.println("Cabin " + cabNum + " is booked by " + cabin[i]);
            }
        }

    }

    public static void removecust(String[] cabin) {
        int cabNum = 0;
        String custName;
        while (cabNum < 12) {

            Scanner input = new Scanner(System.in);
            try {
                System.out.println("select the cabin number to remove the customer or press 13 to exit");
                cabNum = input.nextInt();
                cabNum = cabNum - 1;

            } catch (InputMismatchException e) {
                System.out.println("invalid input please try again......");
            }
            if (cabNum == -1) {
                break;
            } else if (cabNum < 12 && cabNum > -1) {
                System.out.println("please enter customer name....");
                custName = input.next();
                if (cabin[cabNum].equals(custName)) {
                    cabin[cabNum] = "e";
                    cabNum = cabNum + 1;
                    System.out.println(custName + " is removed from cabin " + cabNum);
                }
            }
        }
    }

    public static void BookedCab(String[] cabin) {
        int cabNum;
        for (int i = 0; i < cabin.length; i++) {
            if (!cabin[i].equals("e")) {
                cabNum = i + 1;
                System.out.println("cabin" + cabNum + " is booked by " + cabin[i]);

            }
        }
    }

    public static void findCabin(String[] cabin) {
        String custName;

        Scanner input = new Scanner(System.in);
        System.out.println(" Enter customer name.....");
        custName = input.nextLine();

        for (int i = 0; i < cabin.length; i++) {
            if (cabin[i].equals(custName)) {
                int cabNum = i + 1;
                System.out.println(custName + " is in cabin  " + cabNum);
            }
        }
    }

    public static void storeProData(String[] cabin) throws IOException {

        try (FileWriter myWriter = new FileWriter("inputData.text")) {
            for (int i = 0; i < cabin.length; i++) {
                myWriter.write(cabin[i] + "\n");

            }
            myWriter.close();
            System.out.println("data write to th file.....");

        } catch (IOException e) {
            System.out.println("....error...");
            e.printStackTrace();
        }
    }

    public static void lordProData() throws IOException {
        int linecount = 1;
        try {
            File inputFile = new File("inputData.txt");
            Scanner read = new Scanner(inputFile);
            String fileline;
            while (read.hasNext()) {
                fileline = read.nextLine();
                if (!fileline.equals("e")) {
                    System.out.println("cabin " + linecount + "" + fileline);
                }
                linecount++;
            }
            read.close();
        } catch (IOException e) {
            System.out.println("Error IOException is " + e);
        }
    }

    public static void booked(String[] cabin, String[] booked) {
        initialise(booked);
        int inumber = 0;
        for (int i = 0; i < cabin.length; i++) {
            if (!(cabin[i].equals("e"))) {
                booked[inumber] = cabin[i];
                inumber++;
            }
        }
        System.out.println(Arrays.toString(cabin));
        for (int i = 0; i < booked.length; i++) ;
        {
            int i = 0;
            for (int j = i + 1; j < booked.length; j++) {
                if (!(cabin.equals("e"))) {
                    int cha = booked[i].compareTo(booked[j]);
                    if (cha > 0) {
                        String temp = booked[i];
                        booked[i] = booked[j];
                        booked[j] = temp;
                    }
                }
            }
            System.out.println("Ordered alphabetically");
            for (String customer : booked) {
                if (!customer.equals("e")) {
                    System.out.println(customer);

                }
            }
        }
    }
}