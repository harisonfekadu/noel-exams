package uiComponents.dashboard;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.dataStrucures.dataModules.Answer;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.dataModules.Question;
import logic.dataStrucures.structureModules.LinkedList;

public class GradeViewTable extends TableView<Question>{
    // Fields
    public TableColumn<Question, Integer> questionNumberColumn;
    public TableColumn<Question, Character> correctAnswerColumn;
    public TableColumn<Question, Character> usersAnswerColumn;
    public TableColumn<Question, Double> pointColumn;
    public TableColumn<Question, Double> gradeColumn;
    public ObservableList<Question> questions;
    public Exam exam;
    // Constructors
    public GradeViewTable(Exam exam){
        this.exam=exam;
        this.questions=exam.getQuestions().toObservableList();
        initializeTable();
    }
    // Methods
    public void initializeTable(){

        questionNumberColumn=new TableColumn<Question, Integer>("Number");
        questionNumberColumn.setCellValueFactory(new PropertyValueFactory<>("questionNumber"));
        correctAnswerColumn=new TableColumn<Question, Character>("Correct Answer");
        correctAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("correctAnswer"));
        usersAnswerColumn=new TableColumn<Question, Character>("Your Answer");
        usersAnswerColumn.setCellValueFactory(new PropertyValueFactory<>("usersAnswer"));
        pointColumn=new TableColumn<Question, Double>("Point");
        pointColumn.setCellValueFactory(new PropertyValueFactory<>("point"));
        gradeColumn=new TableColumn<Question, Double>("Grade");
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));


        this.setItems(questions);
        this.setMaxWidth(400);
        this.getColumns().addAll(questionNumberColumn,correctAnswerColumn,usersAnswerColumn,pointColumn,gradeColumn);


    }

}
