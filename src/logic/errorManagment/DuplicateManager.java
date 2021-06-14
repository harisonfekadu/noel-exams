package logic.errorManagment;
import javafx.collections.ObservableList;
import logic.dataStrucures.dataModules.Answer;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.dataModules.Question;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.Student;
import logic.dataStrucures.userModules.User;

public class DuplicateManager {
    //  Methods
    public static boolean doesFieldExist(LinkedList<Field> fields, String fieldId){
        if(fields.isEmpty()){
            return false;
        }
        fieldId=fieldId.toLowerCase();
        for (int i=0; i<fields.size();i++){
            try {
                if(fields.get(i).getFieldID().toLowerCase().equals(fieldId)){
                    return true;
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking field "+e.getIndex());
            }
        }
        return false;
    }
    public static boolean doesFieldExist(ObservableList<Field> fields, String fieldId){
        if(fields.isEmpty()){
            return false;
        }
        fieldId=fieldId.toLowerCase();
        for (int i=0; i<fields.size();i++){
            if(fields.get(i).getFieldID().toLowerCase().equals(fieldId)){
                return true;
            }
        }
        return false;
    }
    public static boolean doesChoiceExist(Answer answer, char choiceId) throws InvalidChoiceId{
        if(answer==null){
            return false;
        }
        choiceId=Character.toUpperCase(choiceId);
        try {
            return answer.getChoice(choiceId)==null;
        } catch (InvalidChoiceId e){
            throw new InvalidChoiceId(e.getChoiceId());
        } catch (ChoiceNotFound e){
            return true;
        }
    }
    public static boolean doesQuestionExist(LinkedList<Question> questions, int questionNumber){
        if(questions.isEmpty()){
            return false;
        }
        for(int i=0; i<questions.size();i++){
            try {
                if(questions.get(i).getQuestionNumber()==questionNumber){
                    return true;
                }
            } catch (NodeNotFound e){
                return false;
            }
        }
        return false;
    }
    public static boolean doesQuestionExist(LinkedList<Question> questions, Question question){
        if(questions.isEmpty()){
            return false;
        }
        for(int i=0; i<questions.size();i++){
            try {
                if((questions.get(i).getQuestionNumber()==question.getQuestionNumber())&&(questions.get(i).getQuestionField().equals(question.getQuestionField()))&&(questions.get(i).getAnswerObject().equals(question.getAnswerObject()))){
                    return true;
                }
            } catch (NodeNotFound e){
                return false;
            }
        }
        return false;
    }
    public static boolean doesExamExist(LinkedList<Exam> exams, String name){
        if(exams.isEmpty()){
            return false;
        }
        name=name.toLowerCase();
        for(int i=0; i<exams.size();i++){
            try {
                if(exams.get(i).getExamName().toLowerCase().equals(name)){
                    return true;
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking question "+e.getIndex());
            }
        }
        return false;
    }
    public static boolean doesExamExist(ObservableList<Exam> exams, String name){
        if(exams.isEmpty()){
            return false;
        }
        name=name.toLowerCase();
        for(int i=0; i<exams.size();i++) {
            if (exams.get(i).getExamName().toLowerCase().equals(name)) {
                return true;
            }
        }
        return false;
    }
    public static boolean doesIdExist(LinkedList<User> users, String id){
        if(users.isEmpty()){
            return false;
        }
        for(int i=0; i<users.size();i++){
            id=id.toLowerCase();
            try {
                if(users.get(i).getId().toLowerCase().equals(id)){
                    return true;
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking student "+e.getIndex());
            }
        }
        return false;
    }
}
