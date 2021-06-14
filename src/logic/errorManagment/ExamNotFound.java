package logic.errorManagment;
public class ExamNotFound extends Exception {
    //  Fields
    private String name;
    //  Constructors
    public ExamNotFound(String name){
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
        return "Exam "+this.name +" was not found.";
    }
}