package gae.levelPreferences;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import engine.goals.Goal;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import gae.gameView.CheckListItem;
import gae.gridView.NumberTextField;
import gae.openingView.UIObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.*;


public class LevelPreferencesEditor implements UIObject {
    private static final ImageView TIMER_IMAGE = new ImageView("/images/bloonsdefense.jpg");
    private static final ImageView HEALTH_IMAGE = new ImageView("/images/desktopdefense.jpg");
    private static final ImageView SCORE_IMAGE = new ImageView("/images/cartoonwars.jpg");
    private static final Text TIME = new Text("Timed Game");
    private static final Text HEALTH = new Text("Health Losing Condition");
    private static final Text SCORE = new Text("Score Winning Condition");
    private static final String DEFAULT_VALUE="0";
    private static final int TEXT_BOX_WIDTH = 100;
    private static final int NUMBER_TEXT_BOX_WIDTH = 50;
    private ScrollPane base;
    private GoalHoverPicture timedGame;
    private GoalHoverPicture health;
    private GoalHoverPicture score;
    private LevelPreferencesData data;
    private List<Goal> winningConditions;
    private List<Goal> losingConditions;
    private CheckListItem timer;

    public LevelPreferencesEditor () {
        base = new ScrollPane();
        timedGame = new GoalHoverPicture(TIMER_IMAGE, TIME, makeTimerOptions());
        DoubleOption healthOpt = new DoubleOption("Minimum health:");
        healthOpt.setAction(e -> data.setHealthGoal(healthOpt.getValue()));
        DoubleOption scoreOpt = new DoubleOption("Winning score:");
        scoreOpt.setAction(e -> data.setHealthGoal(scoreOpt.getValue()));
        health = new GoalHoverPicture(HEALTH_IMAGE, HEALTH, healthOpt.getNode());
        score = new GoalHoverPicture(SCORE_IMAGE, SCORE, scoreOpt.getNode());
        VBox vbox = new VBox(timedGame.getObject(), health.getObject(), score.getObject());
        base.setContent(vbox);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().add(saveButton());
        data = new LevelPreferencesData();

    }

    @Override
    public Node getObject () {
        return base;
    }

    public Node makeTimerOptions () {
        Label labelprompt = new Label("Timer label:");
        TextField label = new TextField();
        label.setOnAction(e -> data.setTimelabel(label.getText()));
        label.setMaxWidth(TEXT_BOX_WIDTH);
        Label timeprompt = new Label("Time:");
        GridPane grid = new GridPane();
        grid.add(labelprompt, 0, 0);
        grid.add(label, 1, 0);
        grid.add(timeprompt, 0, 1);
        grid.add(makeTimeTextField(), 1, 1);
        timer = new TextCheckListItem("Timed losing condition");
        DoubleOption timeGoalOpt = new DoubleOption("Minimum time:");
        timeGoalOpt.setAction(e -> data.setTimeGoal(timeGoalOpt.getValue()));
        timeGoalOpt.getNode().setVisible(false);
        timer.getCheckBox().selectedProperty().addListener( (obs, oldVal, newVal) -> {
            timeGoalOpt.getNode().setVisible(newVal);
        });
        VBox vbox = new VBox(grid, timer.getNode(), timeGoalOpt.getNode());
        return vbox;
    }

    private Node makeTimeTextField () {
        TextField min = new NumberTextField();
        TextField sec = new NumberTextField();
        min.setMaxWidth(NUMBER_TEXT_BOX_WIDTH);
        sec.setMaxWidth(NUMBER_TEXT_BOX_WIDTH);
        min.setPromptText("min");
        sec.setPromptText("sec");
        Label colon = new Label(":");
        min.setOnAction(e -> data.setMinutes(Integer.parseInt(min.getText())));
        sec.setOnAction(e -> data.setSeconds(Integer.parseInt(sec.getText())));
        HBox hbox = new HBox(min, colon, sec);
        return hbox;
    }

    private static class DoubleOption {
        private Node node;
        private StringProperty val = new SimpleStringProperty();
        private TextField textfield;

        public DoubleOption (String label) {
            Label prompt = new Label(label);
            textfield = new NumberTextField();
            val.bind(textfield.textProperty());
            textfield.setText(DEFAULT_VALUE);
            node = new HBox(prompt, textfield);
        }

        public double getValue () {
            return Double.parseDouble(val.getValue());
        }

        public void setAction (EventHandler<ActionEvent> event) {
            textfield.setOnAction(event);
        }

        public Node getNode () {
            return node;
        }
    }

    public List<Goal> getWinningConditions () {
        return winningConditions;
    }

    public List<Goal> getLosingConditions () {
        return losingConditions;
    }

    private Button saveButton () {
        Button button = new Button("Save");
        button.setOnAction(e -> {
            List<Goal> winlist = new ArrayList<>();
            winlist.add(data.getScoreGoal());
            winningConditions = timedGame.isSelected() ? winlist : new ArrayList<>();
            List<Goal> loselist = new ArrayList<>();
            loselist.add(health.isSelected() ? data.getHealthGoal() : null);
            loselist.add(timedGame.isSelected() && timer.getCheckBox().selectedProperty().get() ? data
                    .getTimerGoal() : null);
            losingConditions = loselist ;
            loselist.removeAll(Collections.singleton(null));
            losingConditions.forEach(g->{
                
                    System.out.println(g.getClass());
                
            });
        });
        return button;
    }

}
