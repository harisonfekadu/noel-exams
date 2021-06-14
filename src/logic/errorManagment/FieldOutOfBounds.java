package logic.errorManagment;
import logic.dataStrucures.userModules.Student;

public class FieldOutOfBounds extends Exception {
    //  Fields
    private Student student;
    private int index;
    //  Constructors
    public FieldOutOfBounds(Student student, int index){
        this.student=student;
        this.index=index;
    }
    //  Methods
    public Student getStudent() {
        return student;
    }
    public int getIndex() {
        return index;
    }
    public void display(){
        System.out.println(this.student.getName()+" does not have a field at index "+this.index);
    }
    @Override
    public String toString(){
        return this.student.getName()+" does not have a field at index "+this.index;
    }
}
