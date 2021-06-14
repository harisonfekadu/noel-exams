package uiComponents.takeExam;

import database.Database;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.User;
import logic.errorManagment.DuplicateManager;
import uiComponents.dashboard.DashBoard;

public class SelectExam extends DashBoard{
    //  Fields
    protected Label fieldLabel;
    protected Label examLabel;
    protected ListView<Field> fieldListView;
    protected ListView<Exam> examListView;
    public Button goButton;
    public Button backButton;
    protected VBox fieldPanel;
    protected VBox examPanel;
    protected HBox listPanel;
    protected final LinkedList<Field> fieldList=Database.getFieldList();
    public boolean isStudent;
    public Label messageLabel;
    public Exam selectedExam;
    //  Constructors
    public SelectExam(User user, boolean isStudent){
        super(user);
        this.isStudent=isStudent;
        setElementPadding(5);
        setPrefWidth(850);
        setPrefHeight(850);
        initialize();
    }
    // Methods
    public void initialize(){
        this.toolsPanel.newExamGroup.setVisible(!isStudent);
        fieldLabel=new Label("Select Field");
        examLabel=new Label("Select Exam");
        fieldListView=new ListView<Field>();
        examListView=new ListView<Exam>();
        fieldListView.setPrefWidth(300);
        examListView.setPrefWidth(300);
        fieldListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        examListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        try {
            for(int i=0; i<fieldList.size();i++){
                fieldListView.getItems().add(fieldList.get(i));
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        fieldListView.setOnMouseClicked(event -> {
            if(fieldListView.getSelectionModel().getSelectedItems().isEmpty()){
                report("No field selected.");
            } else{
                try {
                    for(int i=0; i<fieldListView.getItems().size();i++){
                        if(!DuplicateManager.doesExamExist(examListView.getItems(),fieldListView.getItems().get(fieldListView.getSelectionModel().getSelectedIndex()).getExam(i).getExamName()))
                            examListView.getItems().add(fieldListView.getItems().get(fieldListView.getSelectionModel().getSelectedIndex()).getExam(i));
                    }
                } catch (Exception e){
                    System.out.println(e.toString());
                }
            }
        });
        fieldPanel=new VBox();
        fieldPanel.getChildren().addAll(fieldLabel,fieldListView);
        fieldPanel.setSpacing(8);
        fieldPanel.setAlignment(Pos.CENTER);
        fieldPanel.setPadding(elementPadding);
        examPanel=new VBox();
        examPanel.getChildren().addAll(examLabel,examListView);
        examPanel.setSpacing(8);
        examPanel.setAlignment(Pos.CENTER);
        examPanel.setPadding(elementPadding);
        listPanel =new HBox();
        listPanel.getChildren().addAll(fieldPanel, examPanel);
        listPanel.setSpacing(8);
        listPanel.setPadding(new Insets(100,5,5,5));
        listPanel.setAlignment(Pos.CENTER);

        goButton =new Button("Go >");
        backButton =new Button("< Back");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(backButton, goButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(16);

        messageLabel=new Label();
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setPadding(new Insets(5,5,5,30));
        getChildren().addAll(listPanel,hBox,messageLabel);
    }
    public boolean finish(){
        report("");
        if(examListView.getSelectionModel().getSelectedItems().isEmpty()){
            report("Please Select an exam first.");
            return false;
        }
        selectedExam=examListView.getSelectionModel().getSelectedItem();
        return true;
    }
    public void report(String message){
        this.messageLabel.setText(message);
    }

}
