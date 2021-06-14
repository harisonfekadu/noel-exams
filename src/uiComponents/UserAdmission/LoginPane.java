package uiComponents.UserAdmission;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.dataStrucures.structureModules.LinkedList;
import logic.dataStrucures.userModules.Instructor;
import logic.dataStrucures.userModules.Student;
import logic.dataStrucures.userModules.User;


public class LoginPane extends VBox{

    //  Fields
    protected Label header;
    protected VBox headerPanel;
    protected Label usernameLabel;
    protected Label passwordLabel;
    protected TextField usernameField;
    protected PasswordField passwordField;
    public Button loginButton;
    public Button signupButton;
    protected VBox usernamePanel;
    protected VBox passwordPanel;
    protected HBox buttonGroup;
    protected Label messageLabel;
    public Insets elementPadding;
    public boolean isStudent;
    public User user;
    protected final Label Logo=new Label("NOEL EXAMS");

    //  Constructors
    public LoginPane(){
        user=User.PLACE_HOLDER;
        setElementPadding(5);
        Logo.setFont(new Font("Mathlete Bulky",150));
        Logo.setAlignment(Pos.TOP_CENTER);
        getChildren().addAll(Logo);
        initialize();
        setPrefWidth(850);
        setPrefHeight(850);
        setAlignment(Pos.CENTER);
        setPadding(new Insets(5,5,150,5));
    }

    //  Methods
    public void initialize(){
        header=new Label("Login");
        header.setFont(new Font("Roboto",28));
        headerPanel=new VBox();
        headerPanel.getChildren().addAll(header);
        headerPanel.setSpacing(4);
        headerPanel.setAlignment(Pos.CENTER_LEFT);
        headerPanel.setPadding(elementPadding);
        headerPanel.setMaxWidth(200);

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
        passwordPanel=new VBox();
        passwordPanel.getChildren().addAll(passwordLabel,passwordField);
        passwordPanel.setSpacing(4);
        passwordPanel.setAlignment(Pos.CENTER_LEFT);
        passwordPanel.setPadding(elementPadding);
        passwordPanel.setMaxWidth(200);

        loginButton=new Button("Login");
        signupButton=new Button("Sign Up");
        buttonGroup=new HBox();
        buttonGroup.getChildren().addAll(loginButton,signupButton);
        buttonGroup.setSpacing(6);
        buttonGroup.setAlignment(Pos.CENTER);
        buttonGroup.setPadding(elementPadding);
        buttonGroup.setMaxWidth(200);

        messageLabel=new Label();
        messageLabel.setId("messageLabel");

        getChildren().addAll(headerPanel,usernamePanel,passwordPanel,buttonGroup,messageLabel);
        setPadding(elementPadding);
    }
    public boolean isStudent() {
        return isStudent;
    }
    public boolean finishLogin(){
        String id=usernameField.getText();
        String password=passwordField.getText();
        report("");
        if(id.isEmpty() ||password.isEmpty()){
            report("Please Fill all fields.");
            return false;
        }
        // check DB
        LinkedList<Student> studentList=new LinkedList<>();
        studentList.add(new Student("Harison", "Fekadu","hsn","1433"));
        LinkedList<Instructor> instructorList=new LinkedList<>();
        instructorList.add(new Instructor("Harison", "Fekadu","admin","1433"));
        try {
            for(int i=0;i<studentList.size();i++){
                if(studentList.get(i).getId().equals(id) && studentList.get(i).getPassword().equals(password)){
                    isStudent=true;
                    user=studentList.get(i);
                    return true;
                }
                if(instructorList.get(i).getId().equals(id) && instructorList.get(i).getPassword().equals(password)){
                    isStudent=false;
                    user=instructorList.get(i);
                    return true;
                }
            }
            report("Invalid username or password.");
        } catch (Exception e){
            report(e.toString());
            System.out.println(e.toString());
        }
        return false;
    }
    public void report(String message){
        this.messageLabel.setText(message);
    }
    public void setElementPadding(double value) {
        this.elementPadding = new Insets(value,value,value,value);
    }

}
