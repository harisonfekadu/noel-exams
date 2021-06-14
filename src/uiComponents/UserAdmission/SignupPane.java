package uiComponents.UserAdmission;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import database.Database;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.Instructor;
import logic.dataStrucures.userModules.PersonName;
import logic.dataStrucures.userModules.Student;
import logic.dataStrucures.userModules.User;
import logic.errorManagment.DuplicateManager;
import logic.errorManagment.InvalidName;
import logic.errorManagment.Validator;


public class SignupPane extends VBox{

    //  Fields
    protected Label header;
    protected VBox headerPanel;
    protected Label usernameLabel;
    protected Label passwordLabel;
    protected Label confirmPasswordLabel;
    protected TextField usernameField;
    protected PasswordField passwordField;
    protected PasswordField confirmPasswordField;
    public Button loginButton;
    public Button signupButton;
    protected VBox usernamePanel;
    protected VBox passwordPanel;
    protected HBox buttonGroup;
    protected Label messageLabel;
    public Insets elementPadding;
    protected final Label Logo=new Label("NOEL EXAMS");
    protected Label firstNameLabel;
    protected Label lastNameLabel;
    protected Label fieldLabel;
    protected TextField firstNameField;
    protected TextField lastNameField;
    protected ListView<Field> fieldListView;
    protected ListView<Field> addedFieldListView;
    protected Button addButton;
    protected Button removeButton;
    protected HBox namePanel;
    protected VBox firstNamePanel;
    protected VBox lastNamePanel;
    protected VBox fieldPanel;
    protected HBox fieldListPanel;
    protected final LinkedList<User> userList=Database.getUserList();
    protected final LinkedList<Field> fieldList=Database.getFieldList();
    public RadioButton studentRadioButton;
    public RadioButton instructorRadioButton;
    public ToggleGroup radioButtonGroup;
    public HBox radioButtonPanel;
    public Label newFieldNameLabel;
    public Label newFieldIdLabel;
    public TextField newFieldNameField;
    public TextField newFieldIdField;
    public Button newFieldButton;
    public HBox newFieldPanel;
    public String username;
    public User user;
    public boolean isStudent;

    //  Constructors
    public SignupPane(){
        setElementPadding(5);
        Logo.setFont(new Font("Mathlete Bulky",50));
        Logo.setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(Logo);
        initialize();
        setPrefWidth(850);
        setPrefHeight(850);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(20,5,130,5));
    }

    //  Methods
    public void initialize(){
        header=new Label("Sign up");
        header.setFont(new Font("Roboto",28));
        headerPanel=new VBox();
        headerPanel.getChildren().addAll(header);
        headerPanel.setSpacing(4);
        headerPanel.setAlignment(Pos.CENTER_LEFT);
        headerPanel.setPadding(elementPadding);
        headerPanel.setMaxWidth(200);

        firstNameLabel=new Label("First Name: ");
        lastNameLabel=new Label("Last Name: ");
        firstNameField=new TextField();
        lastNameField=new TextField();
        firstNamePanel=new VBox();
        firstNamePanel.getChildren().addAll(firstNameLabel,firstNameField);
        lastNamePanel=new VBox();
        lastNamePanel.getChildren().addAll(lastNameLabel,lastNameField);
        namePanel=new HBox();
        namePanel.getChildren().addAll(firstNamePanel,lastNamePanel);
        namePanel.setSpacing(8);
        namePanel.setAlignment(Pos.CENTER_LEFT);
        namePanel.setPadding(elementPadding);
        namePanel.setMaxWidth(400);

        usernameLabel=new Label("Username/Id: ");
        usernameField=new TextField();
        usernamePanel=new VBox();
        usernamePanel.getChildren().addAll(usernameLabel,usernameField);
        usernamePanel.setSpacing(4);
        usernamePanel.setAlignment(Pos.CENTER_LEFT);
        usernamePanel.setPadding(elementPadding);
        usernamePanel.setMaxWidth(200);

        passwordLabel=new Label("Password: ");
        passwordField=new PasswordField();
        confirmPasswordLabel=new Label("Confirm Password: ");
        confirmPasswordField=new PasswordField();
        passwordPanel=new VBox();
        passwordPanel.getChildren().addAll(passwordLabel,passwordField,confirmPasswordLabel,confirmPasswordField);
        passwordPanel.setSpacing(4);
        passwordPanel.setAlignment(Pos.CENTER_LEFT);
        passwordPanel.setPadding(elementPadding);
        passwordPanel.setMaxWidth(200);

        fieldLabel=new Label("Select Field");
        fieldListView=new ListView<Field>();
        fieldListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        addedFieldListView=new ListView<Field>();
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
            if(!DuplicateManager.doesFieldExist(addedFieldListView.getSelectionModel().getSelectedItems(),fieldListView.getItems().get(fieldListView.getSelectionModel().getSelectedIndex()).getFieldID()))
                addedFieldListView.getItems().addAll(fieldListView.getSelectionModel().getSelectedItems());
        });
        removeButton.setOnAction(event -> {
            addedFieldListView.getItems().remove(addedFieldListView.getSelectionModel().getSelectedIndex());
        });
        fieldListPanel=new HBox();
        fieldListPanel.getChildren().addAll(fieldListView,addedFieldListView);
        fieldListPanel.setSpacing(8);
        fieldPanel=new VBox();
        fieldPanel.getChildren().addAll(fieldLabel,fieldListPanel,hBox);
        fieldPanel.setSpacing(4);
        fieldPanel.setAlignment(Pos.CENTER_LEFT);
        fieldPanel.setPadding(elementPadding);
        fieldPanel.setMaxWidth(500);

        newFieldNameLabel=new Label("Field name:");
        newFieldNameField=new TextField();
        newFieldIdLabel=new Label("Field ID:");
        newFieldIdField=new TextField();
        newFieldButton=new Button("Add Field");
        newFieldButton.setOnAction(event -> {
            if(newFieldNameField.getText().isEmpty()||newFieldIdField.getText().isEmpty()){
                report("Please fill in Field Name and ID.");
            } else {
                if(DuplicateManager.doesFieldExist(fieldList,newFieldIdField.getText())){
                    report("Field already exists.");
                } else {
                    addedFieldListView.getItems().add(new Field(newFieldNameField.getText(),newFieldIdField.getText()));
                    fieldList.add(new Field(newFieldNameField.getText(),newFieldIdField.getText()));
                }
            }
        });
        newFieldPanel=new HBox();
        newFieldPanel.getChildren().addAll(newFieldNameLabel,newFieldNameField,newFieldIdLabel,newFieldIdField,newFieldButton);
        newFieldPanel.setSpacing(8);
        newFieldPanel.setAlignment(Pos.CENTER_LEFT);
        newFieldPanel.setPadding(elementPadding);

        studentRadioButton=new RadioButton("Student");
        instructorRadioButton=new RadioButton("Instructor");
        radioButtonGroup=new ToggleGroup();
        instructorRadioButton.setToggleGroup(radioButtonGroup);
        studentRadioButton.setToggleGroup(radioButtonGroup);
        studentRadioButton.setOnMouseClicked(event -> {
            isStudent=radioButtonGroup.getSelectedToggle()==studentRadioButton;
            newFieldPanel.setVisible(!isStudent);
        });
        instructorRadioButton.setOnMouseClicked(event -> {
            isStudent=radioButtonGroup.getSelectedToggle()==studentRadioButton;
            newFieldPanel.setVisible(!isStudent);
        });

        radioButtonPanel=new HBox();
        radioButtonPanel.getChildren().addAll(instructorRadioButton,studentRadioButton);
        radioButtonPanel.setSpacing(8);
        radioButtonPanel.setPadding(elementPadding);

        loginButton=new Button("< Back");
        signupButton=new Button("Finish >");
        signupButton.setOnAction(event -> finishSignup());
        buttonGroup=new HBox();
        buttonGroup.getChildren().addAll(loginButton,signupButton);
        buttonGroup.setSpacing(6);
        buttonGroup.setAlignment(Pos.CENTER_LEFT);
        buttonGroup.setPadding(new Insets(15,5,5,5));
        buttonGroup.setMaxWidth(200);


        messageLabel=new Label();
        messageLabel.setId("messageLabel");

        VBox vBox=new VBox();
        vBox.getChildren().addAll(headerPanel,radioButtonPanel,namePanel,usernamePanel,passwordPanel,fieldPanel,newFieldPanel,buttonGroup,messageLabel);
        vBox.setPadding(elementPadding);
        vBox.setAlignment(Pos.TOP_LEFT);
        vBox.setPadding(new Insets(5,5,5,180));
        getChildren().addAll(vBox);
    }
    public boolean finishSignup(){
        PersonName name;
        String id;
        String password;
        report("");
        if(usernameField.getText().isEmpty() || firstNameField.getText().isEmpty()||lastNameField.getText().isEmpty()||passwordField.getText().isEmpty()||confirmPasswordField.getText().isEmpty()||addedFieldListView.getItems().isEmpty()){
            report("Please Fill all fields.");
            return false;
        } else {
                try {
                    name=new PersonName(firstNameField.getText(),lastNameField.getText());

                } catch (InvalidName e){
                    report(e.toString());
                    return false;
                }
            if(Validator.validateId(usernameField.getText())){
                id=usernameField.getText();
                username=id;
                if(DuplicateManager.doesIdExist(userList,id)){
                    report("Sorry the username "+id+"is taken.");
                    return false;
                }
            } else {
                report("Invalid Username");
                return false;
            }
            if(!Validator.validatePassword(passwordField.getText())){
                report("Invalid Password");
            }
            if(!passwordField.getText().equals(confirmPasswordField.getText())){
                report("Password does not match.");
                return false;
            } else {
                password=passwordField.getText();
            }
        }
        try {
            if(isStudent){
                Student student=new Student(name,id,password);
                for(int i=0; i<addedFieldListView.getItems().size();i++){
                    student.addField(addedFieldListView.getItems().get(i));
                }
                Database.addStudent(student);
                user=student;
                isStudent=true;
                return true;
            } else {
                Instructor instructor=new Instructor(name,id,password);
                for(int i=0; i<addedFieldListView.getItems().size();i++){
                    instructor.addField(addedFieldListView.getItems().get(i));
                }
                Database.addInstructor(instructor);
                user=instructor;
                isStudent=false;
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
    public void setElementPadding(double value) {
        this.elementPadding = new Insets(value,value,value,value);
    }
}
