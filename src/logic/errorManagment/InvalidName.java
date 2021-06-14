package logic.errorManagment;
public class InvalidName extends Exception{
    //  Fields
    private String name;
    //  Constructors
    public InvalidName(String name){
        this.name=name;
    }
    //  Methods
    public String getName() {
        return name;
    }
    public void display(){
        System.out.println(this.name+" invalid name.");
    }
    @Override
    public String toString(){
        return this.name+" invalid name.";
    }

}
