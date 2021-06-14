package logic.errorManagment;
public class InvalidGrade extends Exception{
    //  Fields
    private char grade;
    //  Constructors
    public InvalidGrade(char grade){
        this.grade=grade;
    }
    //  Methods
    public char getGrade() {
        return grade;
    }
    public void display(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return this.grade+"is an invalid grade.";
    }
}
