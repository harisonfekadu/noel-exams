package logic.errorManagment;

public class InvalidPassword extends Exception{
    //  Fields
    private String password;
    //  Constructors
    public InvalidPassword(String password){
        this.password = password;
    }
    //  Methods
    public String getPassword() {
        return password;
    }
    public void display(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return this.password +" is an invalid password.";
    }

}
