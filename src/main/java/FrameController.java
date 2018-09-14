import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.ArrayList;

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
    private ArrayList <Struct> memoryList = new ArrayList();
    private ArrayList <Struct> dataList = new ArrayList();
    private int currentPos = 0;

    @FXML
    void initialize() {
        fetchButton.setDisable(true);
        executeButton.setDisable(true);
        acText.setText("0000");
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
        pcText.setText(String.valueOf(memoryList.get(currentPos).address));
        if (currentPos == 0)
            acText.setText("0");
        irText.setText(String.valueOf(memoryList.get(currentPos).value));
        /*
        dataAddButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {

            }
        });
        */
    }

    @FXML
    void execute(MouseEvent event) {
        executeButton.setDisable(true);
        fetchButton.setDisable(false);
        try {
            pcText.setText(String.valueOf(memoryList.get(currentPos + 1).address));
        }catch (Exception e){
            e.printStackTrace();
        }switch (String.valueOf(memoryList.get(currentPos).value).charAt(0)){
            case '1':
                acText.setText("0" + String.valueOf(Integer.valueOf(String.valueOf(memoryList.get(currentPos).value).substring(1))));
                break;
            case '2':
                for (int i = 0; i < dataPane.getChildren().size(); i++) {
                    if (dataList.get(0).address == Integer.valueOf(String.valueOf(memoryList.get(currentPos).value).substring(1))){
                        dataPane.getChildren().remove(i);
                        dataPane.getChildren().add(pane(Integer.valueOf(String.valueOf(memoryList.get(currentPos).value).substring(1)), Integer.valueOf(acText.getText())));
                        dataList.remove(i);
                        dataList.add(new Struct(Integer.valueOf(String.valueOf(memoryList.get(currentPos).value).substring(1)), Integer.valueOf(acText.getText())));
                        break;
                    }
                }
                break;
            case '3':
                break;
            case '4':
                break;
            case '5':
                acText.setText(String.valueOf(Integer.valueOf(String.valueOf(memoryList.get(currentPos).value).substring(1)) + Integer.valueOf(acText.getText())));
                break;


        }

        currentPos++;
    }

    @FXML
    void addMemory(MouseEvent event) {
        try {
            if (memoryAddTextField.getText().length() == 4) {
                memoryPane.getChildren().add(pane(memoryPosition, Integer.valueOf(memoryAddTextField.getText())));
                memoryList.add(new Struct(memoryPosition, Integer.valueOf(memoryAddTextField.getText())));
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
                dataList.add(new Struct(dataPosition, Integer.valueOf(dataAddTextField.getText())));
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

class Struct{
    int address;
    int value;

    public Struct(int address, int value) {
        this.address = address;
        this.value = value;
    }
}