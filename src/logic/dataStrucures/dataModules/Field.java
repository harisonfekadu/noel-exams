package logic.dataStrucures.dataModules;
import javafx.collections.ObservableList;
import logic.dataStrucures.structureModules.LinkedList;
import logic.errorManagment.DuplicateManager;
import logic.errorManagment.ExamExists;
import logic.errorManagment.ExamNotFound;
import logic.errorManagment.NodeNotFound;

public class Field {
    //  Fields
    protected String fieldID;
    protected String name;
    protected LinkedList<Exam> exams;
    public static final Field PLACE_HOLDER=new Field("Field Name","Field ID",new LinkedList<Exam>(Exam.PLACE_HOLDER));
    //  Constructors
    public Field(String name, String fieldID){
        this.name=name;
        this.fieldID =fieldID;
        this.exams=new LinkedList<Exam>(null);
    }
    public Field(String name, String fieldID,LinkedList<Exam> exams){
        this.name=name;
        this.fieldID =fieldID;
        this.exams=exams;
    }
    //  Methods
    public String getFieldID() {
        return fieldID;
    }
    public void setFieldID(String fieldID) {
        this.fieldID = fieldID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String[] getExamList(){
        String[] exams=new String[this.exams.size()];
        try {
            for(int i=0; i<this.exams.size();i++){
                exams[i]=this.exams.get(i).getExamName();
            }
        } catch (NodeNotFound e){
            System.out.println("Consider checking question "+e.getIndex());
            return exams;
        }
        return exams;
    }
    public Exam getExam(int index) throws ExamNotFound{
        try {
            for(int i=0; i<this.exams.size();i++){
                if (i==index){
                    return this.exams.get(i);
                }
            }
        } catch (NodeNotFound e){
            throw new ExamNotFound("at index: "+e.getIndex());
        }
        return null;
    }
    public Exam getExam(String name) throws ExamNotFound{
        if(DuplicateManager.doesExamExist(this.exams,name)){
            try {
                for(int i=0; i<this.exams.size();i++){
                    if (this.exams.get(i).getExamName().equals(name)){
                        return this.exams.get(i);
                    }
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking question "+e.getIndex());
            }
        } else {
            throw new ExamNotFound(name);
        }
        return null;
    }
    public Exam deleteExam(String name) throws ExamNotFound{
        if(DuplicateManager.doesExamExist(this.exams,name)){
            try {
                for(int i=0; i<this.exams.size();i++){
                    if (this.exams.get(i).getExamName().equals(name)){
                        return this.exams.remove(i);
                    }
                }
            } catch (NodeNotFound e){
                System.out.println("Consider checking examListView "+e.getIndex());
            }
        } else {
            throw new ExamNotFound(name);
        }
        return null;
    }
    public int numberOfExams(){
        if(this.exams.isEmpty()){
            return 0;
        }
        return this.exams.size();
    }
    public void addExam(Exam exam) throws ExamExists{
        if (this.exams.isEmpty()){
            this.exams.add(exam);
        } else {
            if(!DuplicateManager.doesExamExist(this.exams,exam.getExamName())){
                this.exams.add(exam);
            } else {
                throw new ExamExists(exam.getExamName());
            }
        }
    }
    public String export(){
        String[] exams=this.getExamList();
        String field="Name: "+this.getName()+"\nField ID: "+ this.getFieldID()+"\nExams:\n";
        for(String display:exams){
            field=field.concat(display+"\n");
        }
        return field;
    }
    @Override
    public String toString(){
        return "Name: "+this.getName()+"\tField ID: "+ this.getFieldID();
    }
}
