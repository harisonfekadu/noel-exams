package logic.dataStrucures.dataModules;
import logic.errorManagment.*;

public class Answer {
    //  Fields
    private Choice[] choices=new Choice[4];
    private char correctChoice;
    public static final Answer PLACE_HOLDER=new Answer(Choice.PLACE_HOLDER, Choice.PLACE_HOLDER, Choice.PLACE_HOLDER, Choice.PLACE_HOLDER);
    //  Constructors
    public Answer(Choice choiceA, Choice choiceB, Choice choiceC, Choice choiceD, char correctChoice)throws InvalidChoiceId {
        this.choices[0]=choiceA;
        this.choices[1]=choiceB;
        this.choices[2]=choiceC;
        this.choices[3]=choiceD;
        if (Validator.validateChoice(correctChoice)){
            this.correctChoice=correctChoice;
        } else {
            throw new InvalidChoiceId(correctChoice);
        }
    }
    public Answer(String choiceA,String choiceB, String choiceC, String choiceD, char correctChoice)throws InvalidChoiceId {
        this.choices[0]=new Choice(choiceA,'A');
        this.choices[1]=new Choice(choiceB,'B');
        this.choices[2]=new Choice(choiceC,'C');
        this.choices[3]=new Choice(choiceD,'D');
        if (Validator.validateChoice(correctChoice)){
            this.correctChoice=correctChoice;
        } else {
            throw new InvalidChoiceId(correctChoice);
        }
    }
    public Answer(Choice choiceA, Choice choiceB, Choice choiceC, Choice choiceD){
        this.choices[0]=choiceA;
        this.choices[1]=choiceB;
        this.choices[2]=choiceC;
        this.choices[3]=choiceD;
        this.correctChoice='\0';
    }
    // Methods
    public void setCorrectChoice(char correctChoice) throws InvalidChoiceId {
        if (Validator.validateChoice(correctChoice)){
            this.correctChoice=Character.toUpperCase(correctChoice);
        } else {
            throw new InvalidChoiceId(correctChoice);
        }
    }
    public char getCorrectChoice() {
        return correctChoice;
    }
    public String getChoices() {
        String choices="";
        for (int i=0;i<this.choices.length;i++){
            choices=choices.concat(this.choices[i].toString()+"\n");
        }
        return choices;
    }
    public void addChoice(char choiceId, String choiceField) throws ChoiceExists,ChoiceOutOfBounds,InvalidChoiceId {
        boolean choicePlaced=false;
        try {
            for (int i=0;i<this.choices.length;i++) {
                if (this.choices[i] == null) {
                    choicePlaced = true;
                    if(!DuplicateManager.doesChoiceExist(this,choiceId)){
                        this.choices[i].setChoice(choiceId, choiceField);
                    } else {
                        throw new ChoiceExists(choiceId);
                    }
                }
            }
        } catch (InvalidChoiceId e){
            throw new InvalidChoiceId(e.getChoiceId());
        }
        if(!choicePlaced){
            throw new ChoiceOutOfBounds(choiceField,choiceId);
        }
    }
    public void setChoice(char choiceId, String choiceField) throws ChoiceExists,ChoiceOutOfBounds,InvalidChoiceId {
        try {
               switch (choiceId){
                   case 'A':
                       this.choices[0].setChoiceField(choiceField);
                       this.choices[0].setChoiceId(choiceId);
                       break;
                   case 'B':
                       this.choices[1].setChoiceField(choiceField);
                       this.choices[1].setChoiceId(choiceId);
                       break;
                   case 'C':
                       this.choices[2].setChoiceField(choiceField);
                       this.choices[2].setChoiceId(choiceId);
                       break;
                   case 'D':
                       this.choices[3].setChoiceField(choiceField);
                       this.choices[3].setChoiceId(choiceId);
                       break;
                   default:
                       throw new InvalidChoiceId(choiceId);
            }
        } catch (InvalidChoiceId e){
            throw new InvalidChoiceId(e.getChoiceId());
        }
    }
    public Choice getChoice(char choiceId) throws ChoiceNotFound,InvalidChoiceId {
        if (!Validator.validateChoice(choiceId)){
            throw new InvalidChoiceId(choiceId);
        }
        for (int i=0;i<this.choices.length;i++) {
            if (this.choices[i].getChoiceId() == choiceId) {
                return this.choices[i];
            }
        }
        throw new ChoiceNotFound(choiceId);
    }
    public void deleteChoice(char choiceId) throws ChoiceNotFound,InvalidChoiceId {
        if (!Validator.validateChoice(choiceId)){
            throw new InvalidChoiceId(choiceId);
        }
        for (int i=0;i<this.choices.length;i++) {
            if (this.choices[i].getChoiceId() == choiceId) {
                this.choices[i]=null;
            }else if(i==this.choices.length){
                throw new ChoiceNotFound(choiceId);
            }
        }
    }
    /*public void resetChoices(){
        this.choices=null;
    }*/
    @Override
    public String toString(){
        return getChoices();
    }
}
