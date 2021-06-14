package uiComponents.dashboard;

import logic.dataStrucures.userModules.User;

public class StudentsDashboard extends DashBoard {
    // Fields
    public GradeReportTable summaryTable;
    public User user;
    // Constructors
    public StudentsDashboard(User user){
        super(user);
        this.user=user;
        initializeStudent();
    }
    // Methods
    void initializeStudent(){
        summaryTable=new GradeReportTable();
        getChildren().addAll(summaryTable);
        this.toolsPanel.newExamGroup.setVisible(false);
    }
}
