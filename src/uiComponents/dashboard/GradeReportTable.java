package uiComponents.dashboard;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class GradeReportTable extends TableView{
    // Fields
    public TableColumn studentNameColumn;
    public TableColumn studentIdColumn;
    public TableColumn examNameColumn;
    public TableColumn examFieldColumn;
    public TableColumn examFieldIdColumn;
    public TableColumn examGradeColumn;
    public TableColumn examAuthorColumn;

    // Constructors
    public GradeReportTable(){
        intitalizeTable();
        setPrefHeight(600);
        setEditable(false);
    }
    //  Methods
    public void intitalizeTable(){
        studentNameColumn=new TableColumn("Student Name");
        studentNameColumn.setPrefWidth(120);
        studentIdColumn=new TableColumn("StudentId");
        studentIdColumn.setPrefWidth(100);
        examNameColumn=new TableColumn("Exam");
        examFieldColumn=new TableColumn("Field");
        examFieldIdColumn=new TableColumn("Field Id");
        examGradeColumn=new TableColumn("Grade");
        examGradeColumn.setPrefWidth(75);
        examAuthorColumn=new TableColumn("Author");
        examAuthorColumn.setPrefWidth(120);

        getColumns().addAll(studentNameColumn,studentIdColumn,examNameColumn,examFieldColumn,examFieldIdColumn,examGradeColumn,examAuthorColumn);
    }

}
