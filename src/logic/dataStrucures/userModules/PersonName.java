package logic.dataStrucures.userModules;
import logic.errorManagment.InvalidName;
import logic.errorManagment.Validator;

public class PersonName {
    //  Fields
    protected String firstName;
    protected String lastName;
    public static final PersonName PLACE_HOLDER=new PersonName();
    //  Constructors
    public PersonName(String firstName, String lastName) throws InvalidName {
        if(Validator.validateName(firstName)){
            this.firstName=firstName;
        } else {
            throw new InvalidName(firstName);
        }
        if(Validator.validateName(lastName)){
            this.lastName=lastName;
        } else {
            throw new InvalidName(lastName);
        }
    }
    public PersonName(){
        this.firstName="First Name";
        this.lastName="Last Name";
    }
    //  Methods
    public void setFirstName(String firstName) throws InvalidName {
        if(Validator.validateName(firstName)){
            this.firstName=firstName;
        } else {
            throw new InvalidName(firstName);
        }
    }
    public void setLastName(String lastName) throws InvalidName {
        if(Validator.validateName(lastName)){
            this.lastName=lastName;
        } else {
            throw new InvalidName(lastName);
        }
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    @Override
    public String toString(){
        return this.firstName+" "+this.lastName;
    }
}
