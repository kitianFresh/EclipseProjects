package test;
/*class Vehicle {
    private final void run(){
        System.out.println("Vehicle");
    }
}
public class Car extends Vehicle{
    public static void main (String[] args){
        new  Car().run();
    }
    
    private final void run(){
        System.out.println ("Car");
    }
}*/

class Car {
	   protected int wheelNum;
	   protected String name;
	   public Car(String name) {
	     this.name = name;
	     this.wheelNum = 4;
	   }
	   public String startUp() {
	     return "hong hong!";
	   }
	   public String getInfo() {
	     return "name: " + name + ", wheels: " + wheelNum;
	   }
	 }
public class ExtraLongLincoln extends Car {
	   public ExtraLongLincoln(String name) {
		   super(name);
		   super.name = name;
	   super.wheelNum = 300; // Yes, it is very long...
	   }
	 }
