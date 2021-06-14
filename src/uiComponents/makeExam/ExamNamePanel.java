package uiComponents.makeExam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import database.Database;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.User;
import uiComponents.dashboard.DashBoard;

public class ExamNamePanel extends DashBoard {
    // Fields
    protected ListView<Field> fieldListView;
    protected Label fieldLabel;
    protected Label examLabel;
    protected TextField examField;
    public Button goButton;
    public Button backButton;
    protected VBox fieldPanel;
    protected HBox examPanel;
    protected LinkedList<Field> fieldList;
    public String examName;
    public Field selectedField;
    protected Label messageLabel;
    // Constructors
    public ExamNamePanel(User user){
        super(user);
        fieldList=Database.getFieldList(user.getId());
        initialize();
        setPadding(new Insets(5,5,5,5));
        setPrefWidth(850);
        setPrefHeight(850);
        setSpacing(8);
        setAlignment(Pos.TOP_CENTER);
    }
    // Methods
    public void initialize(){
        fieldLabel=new Label("Select Field");
        fieldListView=new ListView<Field>();
        fieldListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try {
            for(int i=0; i<fieldList.size();i++){
                fieldListView.getItems().add(fieldList.get(i));
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        fieldPanel=new VBox();
        fieldPanel.setPrefWidth(300);
        fieldPanel.getChildren().addAll(fieldLabel,fieldListView);

        examLabel=new Label("Exam name");
        examField=new TextField();
        goButton =new Button("Go >");
        backButton =new Button("< Back");
        goButton.setAlignment(Pos.BOTTOM_CENTER);
        backButton.setAlignment(Pos.BOTTOM_CENTER);
        backButton.setPadding(new Insets(5,5,5,30));
        goButton.setPadding(new Insets(5,5,5,30));
        examPanel=new HBox();
        examPanel.getChildren().addAll(backButton,examLabel,examField,goButton);
        examPanel.setAlignment(Pos.BOTTOM_CENTER);

        messageLabel=new Label();
        VBox vBox=new VBox();
        vBox.getChildren().addAll(fieldPanel,examPanel,messageLabel);
        vBox.setPadding(new Insets(100,100,100,100));
        vBox.setPrefWidth(800);
        vBox.setPrefHeight(800);
        vBox.setSpacing(8);
        vBox.setAlignment(Pos.CENTER);
        getChildren().add(vBox);
    }
    public boolean finishNewExam(){
        report("");
        if(examField.getText().isEmpty()){
            report("Please enter examListView name.");
            return false;
        } if(fieldListView.getSelectionModel().isEmpty()){
            report("Please select examListView field.");
            return false;
        }
        selectedField=fieldListView.getSelectionModel().getSelectedItem();
        examName=examField.getText();
        return true;
    }
    public void report(String message){
        this.messageLabel.setText(message);
    }
}
