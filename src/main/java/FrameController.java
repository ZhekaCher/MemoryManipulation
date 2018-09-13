import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Scanner;

import javax.swing.*;

public class FrameController {

    @FXML
    private TextField memoryAddTextField;

    @FXML
    private TextField dataAddTextField;

    @FXML
    private Button dataAddButton;

    @FXML
    private Button memoryAddButton;

    @FXML
    private FlowPane  memoryPane;

    @FXML
    private FlowPane dataPane;

    @FXML
    private Button fetchButton;

    @FXML
    private Button executeButton;

    @FXML
    private Button startButton;

    @FXML
    private Text pcText;

    @FXML
    private Text irText;

    @FXML
    private Text acText;

    private int memoryPosition = 100;
    private int dataPosition = 900;
    private ArrayList <AnchorPane> memoryList = new ArrayList();
    private ArrayList <AnchorPane> dataList = new ArrayList();

    @FXML
    void initialize() {
        fetchButton.setDisable(true);
        executeButton.setDisable(true);
    }

    @FXML
    void reset(MouseEvent event) {
        pcText.setText("");
        irText.setText("");
        acText.setText("");
        memoryPosition = 100;
        dataPosition = 900;
        startButton.setDisable(false);
        dataAddTextField.setDisable(false);
        dataAddButton.setDisable(false);
        memoryAddTextField.setDisable(false);
        memoryAddButton.setDisable(false);
        fetchButton.setDisable(true);
        executeButton.setDisable(true);
    }

    @FXML
    void start(MouseEvent event) {
        startButton.setDisable(true);
        dataAddTextField.setDisable(true);
        dataAddTextField.setText("");
        dataAddButton.setDisable(true);
        memoryAddTextField.setDisable(true);
        memoryAddTextField.setText("");
        memoryAddButton.setDisable(true);
        fetchButton.setDisable(false);
        executeButton.setDisable(true);
    }

    @FXML
    void fetch(MouseEvent event) {
        fetchButton.setDisable(true);
        executeButton.setDisable(false);
    }

    @FXML
    void execute(MouseEvent event) {
        executeButton.setDisable(true);
        fetchButton.setDisable(false);
    }

    @FXML
    void addMemory(MouseEvent event) {
        try {
            if (memoryAddTextField.getText().length() == 4) {
                memoryPane.getChildren().add(pane(memoryPosition, Integer.valueOf(memoryAddTextField.getText())));
                memoryPosition++;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Your command can't contain non-numerical symbols");
        }
    }

    @FXML
    void addData(MouseEvent event) {
        try {
            if (dataAddTextField.getText().length() == 4) {
                dataPane.getChildren().add(pane(dataPosition, Integer.valueOf(dataAddTextField.getText())));
                dataPosition++;
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Your value can't contain non-numerical symbols");
        }
    }

    AnchorPane pane(int address, int value){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setPrefHeight(55);
        anchorPane.setPrefWidth(183);
        anchorPane.setStyle("-fx-border-radius: 20px;-fx-border-style: line; -fx-border-width: 1px; -fx-border-color: black");
        Text addressText = new Text(String.valueOf(address));
        addressText.setX(15);
        addressText.setY(35);
        addressText.setFont(Font.font(20));
        anchorPane.getChildren().add(addressText);

        Text valueText = new Text(String.valueOf(value));
        valueText.setX(85);
        valueText.setY(35);
        valueText.setFont(Font.font(20));
        anchorPane.getChildren().add(valueText);

        return anchorPane;
    }
}
