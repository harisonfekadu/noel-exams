package uiComponents.dashboard;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
public class ToolsPanel extends ToolBar {
    // Fields
    public Button takeExamButton;
    public Button newExamButton;
    public Button manageExamsButton;
    public Button gradeReportButton;
    public Button manageFieldsButton;
    public Button logoutButton;
    public Label takeExamLabel;
    public Label newExamLabel;
    public Label manageExamsLabel;
    public Label gradeReportLabel;
    public Label manageFieldsLabel;
    public Label usernameLabel;
    public VBox takeExamGroup;
    public VBox newExamGroup;
    public VBox manageExamsGroup;
    public VBox gradeReportGroup;
    public VBox manageFieldsGroup;
    public VBox logoutGroup;
    public VBox userGroup;
    public Insets elementPadding;
    public String username;
    // Constructors
    public ToolsPanel(String username,Insets padding){
        this.username=username;
        this.elementPadding = padding;
        addFields();
    }
    // Methods
    public void addFields(){
        // take examListView
        takeExamButton=new Button("Take Exam");
        takeExamLabel=new Label("Take Exam");
        takeExamGroup=new VBox(4);
        takeExamGroup.setAlignment(Pos.CENTER);
        takeExamGroup.getChildren().addAll(takeExamButton/*,takeExamLabel*/);
        takeExamGroup.setPadding(elementPadding);
        // new examListView
        newExamButton=new Button("New Exam");
        newExamLabel=new Label("New Exam");
        newExamGroup=new VBox(4);
        newExamGroup.setAlignment(Pos.CENTER);
        newExamGroup.getChildren().addAll(newExamButton/*,newExamLabel*/);
        newExamGroup.setPadding(elementPadding);
        // manage exams
        manageExamsButton=new Button("Manage Exams");
        manageExamsLabel=new Label("Manage Exams");
        manageExamsGroup=new VBox(4);
        manageExamsGroup.setAlignment(Pos.CENTER);
        manageExamsGroup.getChildren().addAll(manageExamsButton/*,manageExamsLabel*/);
        manageExamsGroup.setPadding(elementPadding);
        // grade report
        gradeReportButton=new Button("Grade Report");
        gradeReportLabel=new Label("Grade Report");
        gradeReportGroup=new VBox(4);
        gradeReportGroup.setAlignment(Pos.CENTER);
        gradeReportGroup.getChildren().addAll(gradeReportButton/*,gradeReportLabel*/);
        gradeReportGroup.setPadding(elementPadding);
        // manage fields
        manageFieldsButton=new Button("Manage Fields");
        manageFieldsLabel=new Label("Manage Fields");
        manageFieldsGroup=new VBox(4);
        manageFieldsGroup.setAlignment(Pos.CENTER);
        manageFieldsGroup.getChildren().addAll(manageFieldsButton/*,manageFieldsLabel*/);
        manageFieldsGroup.setPadding(elementPadding);
        // logout
        logoutButton=new Button("Logout");
        logoutGroup =new VBox(4);
        logoutGroup.setAlignment(Pos.CENTER);
        logoutGroup.getChildren().addAll(logoutButton);
        logoutGroup.setPadding(elementPadding);
        // users
        usernameLabel=new Label(username);
        usernameLabel.setFont(new Font("Roboto",20));
        userGroup=new VBox(4);
        userGroup.setAlignment(Pos.CENTER);
        userGroup.getChildren().addAll(usernameLabel);
        userGroup.setPadding(elementPadding);
        // tools panel
        getItems().addAll(gradeReportGroup,new Separator(),manageFieldsGroup,new Separator(),manageExamsGroup,takeExamGroup,newExamGroup,new Separator(),userGroup, logoutGroup);
        setPadding(elementPadding);
    }
}
