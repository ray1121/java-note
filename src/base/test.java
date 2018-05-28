class Bicycle{
    int cadence = 0;
    int speed = 0;
    int gear = 1;
    void changeCadence(int newValue){
        cadence = newValue;
    }
    void changeGear(int newValue){
        gear = newValue;
    }
    void speedUp (int increment){
        speed = speed+increment;
    }
    void applyBrakes (int decrement){
        speed = speed - decrement;
    }
    void printStates (){
        System.out.println("cadence:"+cadence+ " speed:" + 
        speed + " gear:" + gear);
    }
}

class BicycleDemo{
    public static void main(String[] args){
        Bicycle bike1 = new Bicycle();
        Bicycle bike2 = new Bicycle();
        bike1.changeCadence(100);
        bike2.changeCadence(200);
        bike1.changeGear(10);
        bike2.changeGear(20);
        bike1.speedUp(1000);
        bike2.speedUp(2000);
        bike1.printStates();
        bike2.printStates();
    }
}