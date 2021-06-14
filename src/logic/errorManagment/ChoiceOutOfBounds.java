package logic.errorManagment;

import logic.dataStrucures.dataModules.Choice;

public class ChoiceOutOfBounds extends Exception {
    //  Fields
    private String choiceField;
    private char choiceId;
    private Choice choice;

    //  Constructors
    public ChoiceOutOfBounds(String choiceField, char choiceId) throws InvalidChoiceId{
        this.choiceId = choiceId;
        this.choiceField = choiceField;
        try {
            this.choice = new Choice(choiceField, choiceId);
        } catch (InvalidChoiceId e){
            throw new InvalidChoiceId(e.getChoiceId());
        }
    }
    //  Methods
    public char getChoiceId() {
        return choiceId;
    }

    public String getChoiceField() {
        return choiceField;
    }

    public void display() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        return "Choice out of bounds : " + this.choice.toString() + " couldn't be added to the choice list.";
    }
}
