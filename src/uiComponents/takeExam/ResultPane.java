package uiComponents.takeExam;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.userModules.User;
import uiComponents.dashboard.GradeViewTable;
public class ResultPane extends VBox{
    // Fields
    public Label resultMessageLabel;
    public Label resultLabel;
    public Exam exam;
    public User user;
    public GradeViewTable gradeTable;
    public Button doneButton;
    // Constructors
    public ResultPane(Exam exam, User user){
        this.exam=exam;
        this.user=user;
        initialize();
        setPadding(new Insets(5,5,5,5));
        setPrefWidth(850);
        setPrefHeight(850);
        setSpacing(8);
        setAlignment(Pos.CENTER);
    }
    // Methods
    public void initialize(){
        resultMessageLabel=new Label("Name: "+user.getName()+"\nExam: "+exam.getExamName()+"\nGrade: "+exam.getGrade()+"/"+exam.getPoint());
        resultMessageLabel.setFont(new Font("Roboto",28));
        resultLabel=new Label(exam.getGradePercent()+"%");
        resultLabel.setFont(new Font("Roboto",45));
        gradeTable=new GradeViewTable(exam);
        doneButton=new Button("Done");
        getChildren().addAll(resultMessageLabel,resultLabel,gradeTable,doneButton);
    }
}
