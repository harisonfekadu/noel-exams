package uiComponents.dashboard;

import database.Database;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.Instructor;
import logic.dataStrucures.userModules.Student;
import logic.dataStrucures.userModules.User;
import logic.errorManagment.DuplicateManager;
import uiComponents.dashboard.DashBoard;

public class ManageFieldsPanel extends DashBoard{
    //  Fields
    protected Label fieldLabel;
    protected ListView<Field> fieldListView;
    protected ListView<Field> addedFieldListView;
    protected Button addButton;
    protected Button removeButton;
    protected VBox fieldPanel;
    protected HBox fieldListPanel;
    protected final LinkedList<User> userList= Database.getUserList();
    protected final LinkedList<Field> fieldList=Database.getFieldList();
    public Label newFieldNameLabel;
    public Label newFieldIdLabel;
    public TextField newFieldNameField;
    public TextField newFieldIdField;
    public Button newFieldButton;
    public HBox newFieldPanel;
    public boolean isStudent;
    public Label messageLabel;
    public Button saveButton;
    //  Constructors
    public ManageFieldsPanel(User user,boolean isStudent){
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
        fieldListView=new ListView<Field>();
        fieldListView.setPrefWidth(300);
        fieldListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        addedFieldListView=new ListView<Field>();
        addedFieldListView.setPrefWidth(300);
        try {
            for(int i=0; i<fieldList.size();i++){
                fieldListView.getItems().add(fieldList.get(i));
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        addButton=new Button("Add Field");
        removeButton=new Button("Remove Field");
        HBox hBox=new HBox();
        hBox.getChildren().addAll(addButton,removeButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(8);
        addButton.setOnAction(event -> {
            report("");
            if(fieldListView.getSelectionModel().getSelectedItems().isEmpty()){
                report("Please select a field to add.");
            } else {
                if(!DuplicateManager.doesFieldExist(addedFieldListView.getItems(),fieldListView.getItems().get(fieldListView.getSelectionModel().getSelectedIndex()).getFieldID()))
                    addedFieldListView.getItems().addAll(fieldListView.getSelectionModel().getSelectedItems());
            }
        });
        removeButton.setOnAction(event -> {
            report("");
            if(addedFieldListView.getSelectionModel().getSelectedItems().isEmpty()){
                report("Please select a field to delete.");
            } else {
                 addedFieldListView.getItems().remove(addedFieldListView.getSelectionModel().getSelectedIndex());
            }
        });
        fieldListPanel=new HBox();
        fieldListPanel.getChildren().addAll(fieldListView,addedFieldListView);
        fieldListPanel.setSpacing(8);
        fieldPanel=new VBox();
        fieldPanel.getChildren().addAll(fieldLabel,fieldListPanel,hBox);
        fieldPanel.setSpacing(8);
        fieldPanel.setAlignment(Pos.CENTER);
        fieldPanel.setPadding(elementPadding);
        fieldListPanel.setAlignment(Pos.CENTER);

        newFieldNameLabel=new Label("Field name:");
        newFieldNameField=new TextField();
        newFieldIdLabel=new Label("Field ID:");
        newFieldIdField=new TextField();
        newFieldButton=new Button("Add New Field");
        newFieldButton.setOnAction(event -> {
            report("");
            if(newFieldNameField.getText().isEmpty()||newFieldIdField.getText().isEmpty()){
                report("Please fill in Field Name and ID.");
            } else {
                if(DuplicateManager.doesFieldExist(addedFieldListView.getItems(),newFieldIdField.getText())){
                    report("Field already exists.");
                } else {
                    addedFieldListView.getItems().add(new Field(newFieldNameField.getText(),newFieldIdField.getText()));
                }
            }
        });
        newFieldPanel=new HBox();
        newFieldPanel.getChildren().addAll(newFieldNameLabel,newFieldNameField,newFieldIdLabel,newFieldIdField,newFieldButton);
        newFieldPanel.setSpacing(8);
        newFieldPanel.setAlignment(Pos.CENTER);
        newFieldPanel.setPadding(elementPadding);
        newFieldPanel.setVisible(!isStudent);

        saveButton=new Button("Save");
        saveButton.setAlignment(Pos.CENTER);
        HBox buttonBox=new HBox();
        buttonBox.getChildren().addAll(saveButton);
        buttonBox.setAlignment(Pos.CENTER);
        messageLabel=new Label();
        messageLabel.setId("messageLabel");
        messageLabel.setAlignment(Pos.CENTER);
        messageLabel.setPadding(new Insets(5,5,5,30));
        getChildren().addAll(fieldPanel,newFieldPanel,buttonBox,messageLabel);
    }
    public boolean finish(){
        report("");
        try {
            if(isStudent){
                Student student=new Student(user.getNameObject(),user.getId(),user.getPassword());
                LinkedList<Field> temp=user.getFieldListObject();
                for(int i=0;i<user.getFieldListObject().size();i++){
                    boolean found=false;
                    for(Field field:user.getFieldObjects()){
                        if(field.getFieldID().equals(addedFieldListView.getItems().get(i).getFieldID())){
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        temp.remove(i);
                        user.setFields(temp);
                        student.setFields(temp);
                    }
                }
                Database.updateStudent(student);
                return true;
            } else {
                Instructor instructor=new Instructor(user.getNameObject(),user.getId(),user.getPassword());
                LinkedList<Field> temp=user.getFieldListObject();
                for(int i=0;i<user.getFieldListObject().size();i++){
                    boolean found=false;
                    for(Field field:user.getFieldObjects()){
                        if(field.getFieldID().equals(addedFieldListView.getItems().get(i).getFieldID())){
                            found=true;
                            break;
                        }
                    }
                    if(!found){
                        temp.remove(i);
                        user.setFields(temp);
                        instructor.setFields(temp);
                    }
                }
                Database.updateInstructor(instructor);
                Database.updateFields(instructor.getFieldListObject());
                return true;
            }

        } catch (Exception e){
            System.out.println(e.toString());
            report(e.toString());
        }
        return false;
    }

    public boolean isStudent() {
        return isStudent;
    }
    public void report(String message){
        this.messageLabel.setText(message);
    }

}
