package logic.dataStrucures.userModules;

import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.errorManagment.*;

public class Instructor extends User{
    // Fields
    public static final Instructor PLACE_HOLDER=new Instructor(PersonName.PLACE_HOLDER,"ID","****");
    //  Constructors
    public Instructor(PersonName name, String id, String password){
        super(name,id,password);
    }
    public Instructor(String firstName, String lastName,String id, String password){
        super(firstName,lastName,id,password);
    }
}
