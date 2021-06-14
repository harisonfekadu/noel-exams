package logic.dataStrucures.dataModules;
import logic.errorManagment.InvalidChoiceId;
import logic.errorManagment.Validator;

public class Choice {
    //  Fields
    private String choiceField;
    private char choiceId;
    public final static Choice PLACE_HOLDER=new Choice("Insert Choice Here...");
    //  Constructors
    public Choice(String choiceField, char choiceId) throws InvalidChoiceId {
        if (Validator.validateChoice(choiceId)){
            this.choiceId=Character.toUpperCase(choiceId);
        } else {
            throw new InvalidChoiceId(choiceId);
        }
        this.choiceField = choiceField;
    }
    public Choice(String choiceField) {
        this.choiceId='\0';
        this.choiceField = choiceField;
    }
    //  Methods
    public void setChoiceId(char choiceId) throws InvalidChoiceId {
        if (Validator.validateChoice(choiceId)){
            this.choiceId=Character.toUpperCase(choiceId);
        } else {
            throw new InvalidChoiceId(choiceId);
        }
    }
    public char getChoiceId() {
        return choiceId;
    }
    public void setChoiceField(String choiceField) {
        this.choiceField = choiceField;
    }
    public String getChoiceField() {
        return choiceField;
    }
    public void setChoice(char choiceId, String choiceField) throws InvalidChoiceId {
        if (Validator.validateChoice(choiceId)){
            this.choiceId=Character.toUpperCase(choiceId);
        } else {
            throw new InvalidChoiceId(choiceId);
        }
        this.choiceField = choiceField;
    }
    @Override
    public String toString(){
        return getChoiceId()+") "+ getChoiceField();
    }
}
