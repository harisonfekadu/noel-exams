package uiComponents.takeExam;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import logic.dataStrucures.dataModules.Question;


public class QuestionPane extends VBox{
    // Fields
    public Label questionNumberLabel;
    public ToolBar headPanel;
    public TextArea questionField;
    public Label pointLabel;
    public Label timeLabel;
    public RadioButton choiceAButton;
    public TextField choiceAField;
    public HBox choiceAPanel;
    public RadioButton choiceBButton;
    public TextField choiceBField;
    public HBox choiceBPanel;
    public RadioButton choiceCButton;
    public TextField choiceCField;
    public HBox choiceCPanel;
    public RadioButton choiceDButton;
    public TextField choiceDField;
    public HBox choiceDPanel;
    public ToggleGroup choiceToggleGroup;
    public VBox choicePanel;
    public Label messageLabel;
    public Question question;
    public char usersAnswer;

    // Constructors
    public QuestionPane(Question question){
        this.question=question;
        initialize();
        setPadding(new Insets(5,5,5,5));
        setPrefWidth(830);
        setSpacing(8);
        setAlignment(Pos.CENTER);
    }
    // Methods
    public void initialize(){
        questionNumberLabel=new Label(Integer.toString(question.getQuestionNumber()));
        questionNumberLabel.setFont(new Font(20));
        questionNumberLabel.setAlignment(Pos.TOP_LEFT);
        pointLabel=new Label("Point: "+question.getPoint());
        timeLabel=new Label("Time: "+question.getTime());

        headPanel=new ToolBar(questionNumberLabel,new Separator(),pointLabel,new Separator(),timeLabel);

        questionField=new TextArea(question.getQuestionField());
        questionField.setEditable(false);
        questionField.setPrefWidth(400);
        questionField.setPrefHeight(400);

        try {
            choiceAButton = new RadioButton("A: ");
            choiceAField = new TextField(question.getAnswerObject().getChoice('A').getChoiceField());
            choiceAField.setPrefWidth(600);
            choiceAField.setEditable(false);
            choiceAPanel = new HBox();
            choiceAPanel.getChildren().addAll(choiceAButton, choiceAField);
            choiceBButton = new RadioButton("B: ");
            choiceBField = new TextField(question.getAnswerObject().getChoice('B').getChoiceField());
            choiceBField.setEditable(false);
            choiceBField.setPrefWidth(600);
            choiceBPanel = new HBox();
            choiceBPanel.getChildren().addAll(choiceBButton, choiceBField);
            choiceCButton = new RadioButton("C: ");
            choiceCField = new TextField(question.getAnswerObject().getChoice('C').getChoiceField());
            choiceCField.setEditable(false);
            choiceCField.setPrefWidth(600);
            choiceCPanel = new HBox();
            choiceCPanel.getChildren().addAll(choiceCButton, choiceCField);
            choiceDButton = new RadioButton("D: ");
            choiceDField = new TextField(question.getAnswerObject().getChoice('D').getChoiceField());
            choiceDField.setEditable(false);
            choiceDField.setPrefWidth(600);
            choiceDPanel = new HBox();
            choiceDPanel.getChildren().addAll(choiceDButton, choiceDField);
        } catch (Exception e){
            System.out.println(e.toString());
            report(e.toString());
        }
        choiceAButton.setOnAction(event -> {
            usersAnswer='A';
        });
        choiceBButton.setOnAction(event -> {
            usersAnswer='B';
        });
        choiceCButton.setOnAction(event -> {
            usersAnswer='C';
        });
        choiceDButton.setOnAction(event -> {
            usersAnswer='D';
        });
        choiceToggleGroup=new ToggleGroup();
        choiceAButton.setToggleGroup(choiceToggleGroup);
        choiceBButton.setToggleGroup(choiceToggleGroup);
        choiceCButton.setToggleGroup(choiceToggleGroup);
        choiceDButton.setToggleGroup(choiceToggleGroup);
        choiceAButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceBButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceCButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceDButton.setAlignment(Pos.BOTTOM_LEFT);
        choiceAButton.setSelected(true);
        choicePanel=new VBox();
        choicePanel.getChildren().addAll(choiceAPanel,choiceBPanel,choiceCPanel,choiceDPanel);
        choicePanel.setSpacing(8);

        messageLabel=new Label();
        getChildren().addAll(headPanel,questionField,choicePanel,messageLabel);
    }
    public Question finishQuestion(){
        report("");
        try {
            switch (usersAnswer){
                case 'A':
                case 'B':
                case 'C':
                case 'D':
                    question.setUsersAnswer(usersAnswer);
                    return question;
                default:
                    question.setUsersAnswer('\0');
                    report("You didn't answer this question.");
                    return question;
            }
        } catch (Exception e){
            System.out.println(e.toString());
            return question;
        }
    }
    public void report(String message){
        messageLabel.setText(message);
    }
}
