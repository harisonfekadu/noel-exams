package mainActivity;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import database.Database;
import logic.dataStrucures.dataModules.Exam;
import logic.dataStrucures.dataModules.Field;
import logic.dataStrucures.userModules.User;
import uiComponents.dashboard.ManageFieldsPanel;
import uiComponents.UserAdmission.LoginPane;
import uiComponents.takeExam.ExamPane;
import uiComponents.takeExam.ResultPane;
import uiComponents.takeExam.SelectExam;
import uiComponents.UserAdmission.SignupPane;
import uiComponents.dashboard.InstructorsDashboard;
import uiComponents.makeExam.ExamNamePanel;
import uiComponents.dashboard.StudentsDashboard;
import uiComponents.makeExam.MakeExamPanel;
public class Main extends Application {
    Stage window;
    // <Start App>
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("NoelExams");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("Noel.png"));
        startApp(primaryStage);
    }
    public void startApp(Stage primaryStage){
        login(primaryStage);
    }
    // </Start App>
    // <Login+Signup>
    public void login(Stage window){
        LoginPane pane= new LoginPane();
        window.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
        window.show();
        pane.loginButton.setOnAction(event -> {
            if(pane.finishLogin()){
                if(pane.isStudent){
                    studentDashboard(window,pane.user);
                } else {
                    instructorDashboard(window,pane.user);
                }
            }
        });
        pane.signupButton.setOnAction(event -> signup(window));
    }
    public void signup(Stage window){
        SignupPane pane= new SignupPane();
        window.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
        window.show();
        pane.loginButton.setOnAction(event -> login(window));
        pane.signupButton.setOnAction(event ->{
            if(pane.finishSignup()){
                if(pane.isStudent){
                    studentDashboard(window,pane.user);
                } else {
                    instructorDashboard(window,pane.user);
                }
            }
        });
    }
    // </Login+Signup>
    // <Dashboards>
    public void studentDashboard(Stage window, User user){
        StudentsDashboard dashboard=new StudentsDashboard(user);
        window.setScene(new Scene(dashboard, dashboard.getPrefWidth(), dashboard.getPrefHeight()));
        window.show();
        dashboard.toolsPanel.logoutButton.setOnAction(event -> login(window));
        dashboard.toolsPanel.takeExamButton.setOnAction(event -> {
            takeExamPane(window,user,true);
        });
        dashboard.toolsPanel.manageFieldsButton.setOnAction(event -> manageFields(window,user,true));
    }
    public void instructorDashboard(Stage window, User user){
        InstructorsDashboard dashboard=new InstructorsDashboard(user);
        window.setScene(new Scene(dashboard, dashboard.getPrefWidth(), dashboard.getPrefHeight()));
        window.show();
        dashboard.toolsPanel.logoutButton.setOnAction(event -> login(window));
        dashboard.toolsPanel.newExamButton.setOnAction(event -> newExam(window,user));
        dashboard.toolsPanel.manageFieldsButton.setOnAction(event -> manageFields(window,user,false));

    }
    // </Dashboards>
    // <Taking Exam>
    public void takeExamPane(Stage window, User user,boolean isStudent){
        SelectExam dashboard=new SelectExam(user,isStudent);
        window.setScene(new Scene(dashboard, dashboard.getPrefWidth(), dashboard.getPrefHeight()));
        window.show();
        dashboard.backButton.setOnAction(event ->{
            if (isStudent){
                studentDashboard(window,user);
            } else {
                instructorDashboard(window,user);
            }
        } );
        dashboard.goButton.setOnAction(event -> {
           if (dashboard.finish()){
                exam(window,user,dashboard.selectedExam,isStudent);
           }
        });
        dashboard.toolsPanel.logoutButton.setOnAction(event -> login(window));
        dashboard.toolsPanel.gradeReportButton.setOnAction(event -> {
            if (isStudent){
                studentDashboard(window,user);
            } else {
                instructorDashboard(window,user);
            }
        });
    }
    public void exam(Stage window, User user, Exam exam, boolean isStudent){
        ExamPane examPanel=new ExamPane(exam, user,isStudent);
        window.setScene(new Scene(examPanel, examPanel.getPrefWidth(), examPanel.getPrefHeight()));
        window.show();
        examPanel.cancelButton.setOnAction(event -> {
            if(isStudent){
                studentDashboard(window,user);
            } else {
                instructorDashboard(window,user);
            }
        });
        examPanel.finishButton.setOnAction(event -> {
            examPanel.finishExam();
            Database.updateExamGrades(user,exam);
            breifResultView(window,user,exam,isStudent);
        });
    }
    public void breifResultView(Stage window, User user, Exam exam, boolean isStudent){
        ResultPane pane=new ResultPane(exam,user);
        window.setScene(new Scene(pane, pane.getPrefWidth(), pane.getPrefHeight()));
        window.show();
        pane.doneButton.setOnAction(event -> {
            if(isStudent){
                studentDashboard(window,user);
            } else {
                instructorDashboard(window,user);
            }
        });
    }
    // </Taking Exam>
    // <Making New Exam>
    public void newExam(Stage window, User user){
        ExamNamePanel dashboard=new ExamNamePanel(user);
        window.setScene(new Scene(dashboard, dashboard.getPrefWidth(), dashboard.getPrefHeight()));
        window.show();
        dashboard.toolsPanel.logoutButton.setOnAction(event -> login(window));
        dashboard.toolsPanel.manageFieldsButton.setOnAction(event -> manageFields(window,user,false));
        dashboard.goButton.setOnAction(event -> {
            if(dashboard.finishNewExam()){
                makeExam(window,user,dashboard.examName,dashboard.selectedField);
            }
        });
        dashboard.backButton.setOnAction(event -> instructorDashboard(window,user));
    }
    public void makeExam(Stage window, User user,String examName, Field selectedField){
        MakeExamPanel dashboard=new MakeExamPanel(examName,user);
        window.setScene(new Scene(dashboard, dashboard.getPrefWidth(), dashboard.getPrefHeight()));
        window.show();
        dashboard.finishButton.setOnAction(event -> {
            if(dashboard.finishExam()){
                Database.addExam(selectedField,dashboard.exam);
                instructorDashboard(window,user);
            }
        });
        dashboard.cancelButton.setOnAction(event -> instructorDashboard(window,user));
    }
    // </Making New Exam>
    // <Manage Fields>
    public void manageFields(Stage window,User user,boolean isStudent){
        ManageFieldsPanel dashboard=new ManageFieldsPanel(user,isStudent);
        window.setScene(new Scene(dashboard, dashboard.getPrefWidth(), dashboard.getPrefHeight()));
        window.show();
        dashboard.saveButton.setOnAction(event -> {
            if(dashboard.finish()){
                if(isStudent){
                    studentDashboard(window,user);
                } else {
                    instructorDashboard(window,user);
                }
            }
        });
        dashboard.toolsPanel.logoutButton.setOnAction(event -> login(window));
        dashboard.toolsPanel.newExamButton.setOnAction(event -> newExam(window,user));
    }
    // </Manage Fields>


    public static void main(String[] args) {
        launch(args);
    }
}
