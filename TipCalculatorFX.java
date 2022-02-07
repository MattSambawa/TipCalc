import java.text.NumberFormat;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TipCalculatorFX extends Application {

    private Slider tipPercentSlider = new Slider(0, 30, 15);
    private Label tipPercentLabel = new Label("15%");
    private TextField billAmountTF = new TextField();
    private TextField tipAmountTF = new TextField();
    private TextField totalAmountTF = new TextField();
    private Button calculateButton = new Button("Calculate");
    private Button clearButton = new Button("Clear");

    @Override
    public void start(Stage primaryStage) {
        tipPercentSlider.setBlockIncrement(1);
        tipPercentSlider.setMajorTickUnit(5);
        tipPercentSlider.setShowTickLabels(true);
        tipPercentSlider.setShowTickMarks(true);
        tipAmountTF.setEditable(false);
        totalAmountTF.setEditable(false);
        tipAmountTF.setFocusTraversable(false);
        totalAmountTF.setFocusTraversable(false);

        GridPane gridPane = new GridPane();
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.add(new Label("Bill:"), 0, 0);
        gridPane.add(billAmountTF, 1, 0);
        gridPane.add(tipPercentLabel, 0, 1);
        gridPane.add(tipPercentSlider, 1, 1);
        gridPane.add(new Label("Tip:"), 0, 2);
        gridPane.add(tipAmountTF, 1, 2);
        gridPane.add(new Label("Total:"), 0, 3);
        gridPane.add(totalAmountTF, 1, 3);

        HBox hBox = new HBox();
        hBox.setSpacing(10);
        hBox.setAlignment(Pos.CENTER_RIGHT);
        hBox.getChildren().add(clearButton);
        hBox.getChildren().add(calculateButton);

        gridPane.add(hBox, 1, 5);
        gridPane.setAlignment(Pos.CENTER);
        GridPane.setHalignment(calculateButton, HPos.RIGHT);
        GridPane.setHalignment(clearButton, HPos.RIGHT);

        //Functions
        billAmountTF.setAlignment(Pos.BOTTOM_RIGHT);
        tipAmountTF.setAlignment(Pos.BOTTOM_RIGHT);
        totalAmountTF.setAlignment(Pos.BOTTOM_RIGHT);
        clearButton.setOnAction(event -> clear());
        calculateButton.setOnAction(event -> calculate());
        tipPercentSlider.valueProperty().addListener((obs, oldVal, newVal) -> updateTipPercent(newVal));

        Scene scene = new Scene(gridPane, 400, 250);
        primaryStage.setTitle("Tip Calculator");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void updateTipPercent(Number newValue) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        tipPercentLabel.setText(percent.format(newValue.doubleValue()/100));
        try {tipCalculate();}
        catch(Exception e){}
    }

    public void clear() {
        billAmountTF.clear();
        tipPercentSlider.setValue(15);
        tipAmountTF.clear();
        totalAmountTF.clear();
    }

    public void tipCalculate() {
        double billAmount = Double.parseDouble(billAmountTF.getText());
        double tipPercent = tipPercentSlider.getValue() / 100;
        double tipAmount = billAmount*tipPercent;
        double total = billAmount + tipAmount;
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        tipAmountTF.setText(currency.format(tipAmount));
    }

    public void calculate() {
        double billAmount = Double.parseDouble(billAmountTF.getText());
        double tipPercent = tipPercentSlider.getValue() / 100;
        double tipAmount = billAmount*tipPercent;
        double total = billAmount + tipAmount;
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        totalAmountTF.setText(currency.format(total));
    }

    class clearHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent arg0) {
            clear();
        }

    }

    public static void main(String[] args)
    {
        Application.launch(args);
    }

}