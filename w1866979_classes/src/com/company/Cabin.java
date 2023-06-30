package com.company;

public class Cabin {
    String passenger01;
    String passenger02;
    String passenger03;

    public void displayCabin(){
        System.out.println(" Passenger 1 "+passenger01);
        System.out.println(" Passenger 2 "+passenger02);
        System.out.println(" Passenger 3 "+passenger03);
    }
    public String getPassenger(){ return passenger01+"\n"+passenger02+"\n"+passenger03+"\n";}

    public void setPassenger(String passenger01,String passenger02,String passenger03){
        this.passenger01 = passenger01;
        this.passenger02 = passenger02;
        this.passenger03 = passenger03;
    }
    public String getPassenger01(){
        return passenger01;
    }
    public String getPassenger02() {

        return passenger02;
    }
    public String getPassenger03(){

        return passenger03;
    }

    public void setPassenger01(String name){passenger01 = name;}
    public void setPassenger02(String name){passenger02 = name;}
    public void setPassenger03(String name){passenger03 = name;}

    public void removePassenger1(){passenger01 = "e" ;}
    public void removePassenger2(){passenger02 = "e"; }
    public void removePassenger3(){passenger03 = "e"; }


}
