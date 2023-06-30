package com.company;

public class Passenger {
    private String firstName = "e";
    private String surName = "e";
    private int expenses = 100;
    private static int totalExpenses;

    public void Name(String fname,String sname){
        firstName = fname;
        surName = sname;
        totalExpenses = totalExpenses + expenses;

    }
    public String getName() { return firstName+" "+surName;}
    public int getExpenses() { return expenses;}
    public void expenses(int expenses){
        this.expenses = expenses;
        totalExpenses = totalExpenses + expenses;

    }

    public int getTotalExpenses(){return totalExpenses;}
    public void removePassen(){
        firstName = "e";
        surName = "e";
        totalExpenses = totalExpenses + expenses;
    }

    public void setNames(String customerFname, String customerLname) {

    }
}
