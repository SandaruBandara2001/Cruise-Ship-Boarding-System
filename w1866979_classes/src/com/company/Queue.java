package com.company;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Queue {

    static int start = 0;
    static int end = 0;
    static int numOfEmptyCabins = 12;

    static String[] passengerQueue = new String[15];

    public static void main(String[] args) {
        Passenger[] passengers = new Passenger[36];
        Cabin[] cabins = new Cabin[12];

        for (int i = 0; i < 12; i++) {
            cabins[1] = new Cabin();

        }
        for (int i = 0; i < passengers.length; i++) {
            passengers[i] = new Passenger();
        }

        initialise(cabins);
        initialiseQueue(passengerQueue);

        boolean menuloop = true;

        while (menuloop) {
            setNumOfEmptycabs(cabins);          // getting number of empty cabins

            System.out.println(".....Menu...... ");
            System.out.println("press 'A' to Add a customer to cabin ");
            System.out.println("press 'v' to View to all cabins ");
            System.out.println("press 'E' to View to empty cabins ");
            System.out.println("press 'D' to Delete customer from cabin ");
            System.out.println("press 'F' to Find cabin from customer name ");
            System.out.println("press 'S' to Store program data into file ");
            System.out.println("press 'L' to Load program data from file");
            System.out.println("press 'T' to View Expenses ");
            System.out.println("press '0' to Exit the program ");


            System.out.println("......Select from the menu....");
            Scanner input2 = new Scanner(System.in);
            String input1 = input2.nextLine();

            switch (input1) {
                case "a", "A" -> {
                        if (passengerQueueCount() == passengerQueue.length && numOfEmptyCabins > 0) {
                            addPassenger(cabins, passengers);
                        }
                        else{
                        queueManage(cabins);
                    }
                }

                case "v", "V" -> displayCabin(cabins);

                case "e", "E" -> displayEmptyCabins(cabins);

                case "d", "D" -> removePassenger(cabins, passengers);

                case "f", "F" -> findCabinFromName(cabins);

                case "s", "S" -> storeData(cabins);

                case "l", "L" -> lordData(cabins);

                case "t", "T" -> printExpenses(passengers);

                case "0" -> System.exit(0);
               
                default -> System.out.println("invalid input");

            }
        }
    }

    private static void setNumOfEmptycabs(Cabin[] array) {
        int emptyCabins = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i].getPassenger01().equals("e") || array[i].getPassenger02().equals("e") || array[i].getPassenger03().equals("e")) {
                emptyCabins += 1;
            }
        }
        numOfEmptyCabins = emptyCabins;
    }

    public static void initialise(Cabin[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i].setPassenger("e", "e", "e");
        }
    }

    public static void displayCabin(Cabin[] array) {
        for (int i = 0; i < array.length; i++) {
            int cabNum = +1;
            System.out.println("Cabin " + cabNum + ",");
            array[i].displayCabin();
        }
    }
    // Display th empty cabins if passengers assigning  with 'e'


    public static void displayEmptyCabins(Cabin[] array) {
        int emptyCabCount = 0;
        System.out.println("Display empty cabins ....");
        for (int i = 0; i < array.length; i++) {
            if (array[i].getPassenger01().equals("e") || (array[i].getPassenger02().equals("e") || (array[i].getPassenger03().equals("e")))) {
                int cabNum = i + 1;
                System.out.println("Cabin " + cabNum + ",");
                emptyCabCount = emptyCabCount + 1;
            }
        }
        numOfEmptyCabins = emptyCabCount;

    }

    public static void addPassenger(Cabin[] array, Passenger[] array2) {
        int cabNum = 0;
        String customerFname;
        String customerLname;

        while (cabNum < 13) {
            displayEmptyCabins(array);              //Display the cabins
            if (numOfEmptyCabins == 0) {
                System.out.println(".....Cabins are filled.....");
                break;
            }
            Scanner input = new Scanner(System.in);
            try {
                System.out.println("select a cabin from empty cabins.. if you want to exit program enter '0' ");
                cabNum = input.nextInt();
                cabNum = cabNum - 1;
                input.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Input is invalid Please try again ");
                continue;
            }
            if (cabNum == -1) {
                break;

            } else if (cabNum < 12 && cabNum > -1) {
                if (array[cabNum].getPassenger01().equals("e") || (array[cabNum].getPassenger02().equals("e")) || (array[cabNum].getPassenger03().equals("e"))) {
                    System.out.println("Please Enter Your First Name...")
                    ;
                    customerFname = input.next();
                    System.out.println("Please Enter Your Sur Name...")
                    ;
                    customerLname = input.next();

                    //add passenger to object class.....

                    for (int i = 0; i < array2.length; i++) {

                        String name = array2[i].getName();
                        if (name.equals("e e")) {
                            array2[i].setNames(customerFname, customerLname);
                            i = array2.length;
                        }
                    }
                    //Add Passenger To Cabin Object If Passenger Is 'e' in Cabin Object
                    if (array[cabNum].passenger01.equals("e")) {
                        array[cabNum].passenger01 = customerFname + " " + customerLname;
                    } else if (array[cabNum].passenger02.equals("e")) {
                        array[cabNum].passenger02 = customerFname + " " + customerLname;
                    } else if (array[cabNum].passenger03.equals("e")) {
                        array[cabNum].passenger03 = customerFname + " " + customerLname;
                    }
                    cabNum = cabNum + 1;
                    System.out.println(customerFname + " " + customerLname + " added to cabin" + cabNum);
                } else {
                    System.out.println("That cabin is no empty ");
                }
            } else {
                System.out.println(" invalid input please try again ");
            }
        }
    }



    public static void removePassenger(Cabin[] array, Passenger[] array2) {
        String passengerName;
        boolean loop = true;

        while (loop) {
            int numberoferror = 0;
            Scanner input = new Scanner(System.in);
            System.out.println("enter passenger name need to remove or press 0 to exit ");
            passengerName = input.nextLine();
            if (passengerName.equals("0")) {
                loop = false;
            } else {
                for (int i = 0; i < array.length; i++) {              //remove passenger
                    int cabNum = i + 1;
                    if (array[i].passenger01.equalsIgnoreCase(passengerName)) {
                        array[i].removePassenger1();
                        System.out.println(passengerName + " removed form Cabin" + cabNum);
                    } else if (array[i].passenger02.equalsIgnoreCase(passengerName)) {
                        array[i].removePassenger2();
                        System.out.println(passengerName + " removed form Cabin" + cabNum);
                    } else if (array[i].passenger03.equalsIgnoreCase(passengerName)) {
                        array[i].removePassenger3();
                        System.out.println(passengerName + " removed form Cabin" + cabNum);
                    } else {
                        numberoferror = numberoferror + 1;
                    }
                }
                if (numberoferror == array.length) {
                    System.out.println("name you entered is not in cabin list");
                }
                for (int i = 0; i < array2.length; i++) {
                    String Name = array2[i].getName();
                    if (Name.equalsIgnoreCase(passengerName)) {
                        array2[i].removePassen();
                    }
                }
            }
        }
    }

    public static void findCabinFromName(Cabin[] array) {
        String passengerName;
        boolean loop = true;

        while (loop) {
            Scanner input = new Scanner(System.in);
            System.out.println(" Enter passenger name to find cabin or press '0' to exit");
            passengerName = input.nextLine();
            int cabNum = 0;
            if (passengerName.equals("0")) {
                loop = false;
            } else {
                cabNum = 0;
                for (int i = 0; i < array.length; i++) {
                    if (array[i].passenger01.equalsIgnoreCase(passengerName) || (array[i].passenger02.equalsIgnoreCase(passengerName) || (array[i].passenger03.equalsIgnoreCase(passengerName))))
                        cabNum = i + 1;
                }
                System.out.println(passengerName + " Cabin is " + cabNum);
            }
            if (cabNum == 0) {
                System.out.println("name you entered is not in cabin name list ");
            }
        }
    }

    // Write data to file

    public static void storeData(Cabin[] array) {               //https://www.infoworld.com/article/2076301/learn-how-to-store-data-in-objects.html
        try {
            FileWriter dataWrite = new FileWriter("inputData.txt");
            for (int i=0; i<array.length;i++){
                dataWrite.write(array[i].getPassenger());

            }
            dataWrite.close();
            System.out.println(".....Data stored.....");
        } catch (IOException e) {
            System.out.println("....Error....");
            e.printStackTrace();
        }
    }
    //read from the text file

    public static void lordData(Cabin[] array) {
        String[] data = new String[36];

        try {
            File myFile = new File("inputData,txt");
            Scanner reader = new Scanner(myFile);
            int n = 0;
            while (reader.hasNext()) {
                data[n] = reader.nextLine();
                n++;

            }
            int count = 0;
            int cabNum = 0;
            while (count < 36) {
                for (int i = 0; i < array.length; i++) {
                    String data01 = data[count];
                    count += 1;
                    String data02 = data[count];
                    count += 1;
                    String data03 = data[count];
                    count += 1;
                    cabNum = i + 1;
                    System.out.println("\n Cabin " + cabNum + "\n Passenger 01 " + data01 + "\n Passenger 02 " + data02 + "\n Passenger 03 " + data03);
                }
            }
            System.out.println("\n ......Data Lorded ......");
        } catch (FileNotFoundException e) {
            System.out.println(".....Error.....");
            e.printStackTrace();
        }
    }

    public static void printExpenses(Passenger[] array) {
        for (int i = 0; i < array.length; i++) {
            int expense = array[i].getExpenses();
            String name = array[i].getName();
            if (!name.equals("e e")) {
                System.out.println(name + " Total Expenses " + expense);
            }

        }
        int totalExpense = array[0].getTotalExpenses();
        System.out.println("Total Expenses " + totalExpense);

    }

    public static void initialiseQueue(String[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = "e";

        }
    }

    public static void addQueue(String name) {
        for (int i = 0; i < passengerQueue.length; i++) {
            if (passengerQueue[i].equals("e")) {
                end = i;
                i = passengerQueue.length;
            }
        }
        passengerQueue[end] = name;
        System.out.println(name + " Added to Queue");
        end++;

    }

    public static String takeQueue(Passenger[] array2, String inputName) {
        String passengerName = "";
        if (end > start) {
            System.out.println("\n .....input taken....." + passengerQueue[start]);
            passengerName = passengerQueue[start];

            for (int i = 0; i < passengerQueue.length; i++) {
                if (i != passengerQueue.length - 1) {
                    passengerQueue[i] = passengerQueue[i + 1];
                    passengerQueue[i + 1] = "e";
                }
            }

            String[] split = passengerName.split("");//https://www.geeksforgeeks.org/split-string-java-examples/
            String customerFname = split[0];
            String customerlname = split[1];

            for (int i = 0; i < array2.length; i++) {
                String name = array2[i].getName();
                if (name.equals(inputName)) {
                    array2[i].removePassen();
                    array2[i].setNames(customerFname, customerlname);
                    i = array2.length;
                }
            }
        } else {
            System.out.println("........Queue is Empty........");}
            return passengerName;
        }

    public static void queueManage(Cabin[] array){
        boolean loop = true;
        while (loop){
            System.out.println("Cabins are filld with passengers added to th queue...");
            Scanner input = new Scanner(System.in);
            System.out.println("enter your first name or Press '0' to exit");
            Scanner customerFname = input.reset();
            if (customerFname.equals("0")){
                break;
            }
            System.out.println("enter your sur name or Press '0' to exit");
            Scanner customerLname = input.reset();
            if (customerLname.equals("0")){
                break;
            }
            String name = customerFname+ " "+customerLname;

            if (numOfEmptyCabins == 0){
                addQueue(name);
            }
        }
    }
    public static int passengerQueueCount(){
        int passengerQueueCount = 0;
        for (int i = 0; i <passengerQueue.length; i++){
            if (passengerQueue[i].equals("e")){
                passengerQueueCount = passengerQueueCount+1;
            }
        }
        return passengerQueueCount;
    }
}

