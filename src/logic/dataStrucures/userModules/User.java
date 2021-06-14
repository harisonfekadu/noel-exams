package logic.dataStrucures.userModules;

import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.errorManagment.*;

public class User {
    // Fields
    protected PersonName name;
    protected String id;
    protected String password;
    protected LinkedList<Field> fields;
    public static final User PLACE_HOLDER=new User(PersonName.PLACE_HOLDER,"ID","****");
    //  Constructors
    public User(PersonName name, String id, String password){
        this.name=name;
        this.password=password;
        this.id=id;
        this.password=password;
        this.fields=new LinkedList<Field>(null);
    }
    public User(String firstName, String lastName, String id, String password){
        try {
            this.name=new PersonName(firstName,lastName);
        } catch (InvalidName e){
            System.out.println(e.toString());
        }
        this.password=password;
        this.id=id;
        this.password=password;
        this.fields=new LinkedList<Field>(null);
    }
    //  Methods
    public void setPassword(String password) throws InvalidPassword {
        if(Validator.validatePassword(password)){
            this.password=password;
        } else {
            throw new InvalidPassword(password);
        }
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public void setName(String firstName, String lastName) throws InvalidName {
        try {
            this.name = new PersonName(firstName,lastName);
        } catch (InvalidName e){
            throw new InvalidName(e.getName());
        }
    }
    public void setName(PersonName name) {
        this.name = name;
    }
    public PersonName getNameObject() {
        return name;
    }
    public String getName(){
        return this.name.toString();
    }
    public void setId(String id) throws InvalidId{
        if(Validator.validateId(id)){
            this.id = id;
        } else {
            throw new InvalidId(id);
        }
    }
    public String getId() {
        return id;
    }
    public void setFields(LinkedList<Field> fields) {
        this.fields = fields;
    }
    public void addField(Field field){
        if(!DuplicateManager.doesFieldExist(this.fields,field.getFieldID()))
            this.fields.add(field);
    }
    public void clearFields(){
        this.fields=null;
    }
    public Field deleteField(String fieldId) throws FieldNotFound{
        if(DuplicateManager.doesFieldExist(this.fields,fieldId)){
            try {
                for(int i=0; i<this.fields.size();i++){
                    if(this.fields.get(i).getFieldID().equals(fieldId)){
                        return this.fields.remove(i);
                    }
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking field "+e.getIndex());
            }
        } else {
            throw new FieldNotFound(fieldId);
        }
        return null;
    }
    public Field getField(String fieldId) throws FieldNotFound{
        if(DuplicateManager.doesFieldExist(this.fields,fieldId)) {
            try {
                for (int i = 0; i < this.fields.size(); i++) {
                    if (this.fields.get(i).getFieldID().equals(fieldId)) {
                        return this.fields.get(i);
                    }
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking field "+e.getIndex());
            }
        } else {
            throw new FieldNotFound(fieldId);
        }
        return null;
    }
    public String getFields(){
        String fields="";
        try {
            for(int i=0; i<this.fields.size();i++){
                fields=fields.concat(this.fields.get(i).toString()+"\n");
            }
        } catch (NodeNotFound e){
            System.out.println("Consider checking field "+e.getIndex());
        }
        return fields;
    }
    public String[] getFieldsList(){
        String[] fields=new String[this.fields.size()];
        try {
            for(int i=0; i<this.fields.size();i++){
                fields[i]=this.fields.get(i).getName();
            }
        } catch (NodeNotFound e){
            System.out.println("Consider checking field "+e.getIndex());
        }
        return fields;
    }
    public Field[] getFieldObjects(){
        Field[] fields=new Field[this.fields.size()];
        try {
            for(int i=0; i<this.fields.size();i++){
                fields[i]=this.fields.get(i);
            }
        } catch (NodeNotFound e){
            System.out.println("Consider checking field "+e.getIndex());
        }
        return fields;
    }
    public LinkedList<Field> getFieldListObject(){
        return fields;
    }
    public int numberOfField(){
        return this.fields.size();
    }
    @Override
    public String toString(){
        String fields="";
        String fieldsList[]=this.getFieldsList();
        for(int i=0; i<fieldsList.length;i++){
            fields=fields.concat(fieldsList[i]+"\n");
        }
        return "Name: "+this.name+"\nUsername: "+this.id+"Fields: \n"+fields;
    }
}
