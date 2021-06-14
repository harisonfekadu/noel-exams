package uiComponents.dashboard;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import logic.dataStrucures.userModules.User;

public class DashBoard extends VBox{
    //  Fields
    public ToolsPanel toolsPanel;

    public HBox statusPanel;
    public Label statusLabel;
    public Insets elementPadding;
    public User user;
    //  Constructors
    public DashBoard(User user){
        this.user=user;
        setElementPadding(5);
        addFields();
        setPrefWidth(850);
        setPrefHeight(850);
    }
    // Methods
    public void addFields(){
        // tools panel
        toolsPanel=new ToolsPanel(user.getId(),elementPadding);
        // status
        statusLabel=new Label();
        statusPanel=new HBox();
        statusPanel.getChildren().add(statusLabel);
        statusPanel.setMaxHeight(10);
        // add all
        getChildren().addAll(toolsPanel,statusPanel);
    }
    public void setElementPadding(double value) {
        this.elementPadding = new Insets(value,value,value,value);
    }
}
