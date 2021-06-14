package logic.errorManagment;
public class ExamExists extends Exception {
    //  Fields
    private String name;
    //  Constructors
    public ExamExists(String name){
        this.name = name;
    }
    //  Methods
    public String getName() {
        return name;
    }
    public void display(){
        System.out.println(this.toString());
    }
    @Override
    public String toString(){
        return "Exam "+this.name +" was found.";
    }
}