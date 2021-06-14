package logic.dataStrucures.userModules;

import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.errorManagment.*;

public class Student extends User{
    // Fields
    public static final Student PLACE_HOLDER=new Student(PersonName.PLACE_HOLDER,"ID","****");
    //  Constructors
    public Student(PersonName name, String id, String password){
        super(name,id,password);
    }
    public Student(String firstName, String lastName,String id, String password){
        super(firstName,lastName,id,password);
    }
}
