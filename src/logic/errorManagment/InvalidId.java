package logic.errorManagment;
public class InvalidId extends Exception{
    //  Fields
    private String id;
    //  Constructors
    public InvalidId(String id){
        this.id=id;
    }
    //  Methods
    public String getId() {
        return id;
    }
    public void display(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return this.id+" invalid id.";
    }

}
