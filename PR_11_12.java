package com.company;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;


public class PR_11_12 {
    public static void main(String[] args) {
        Calculator a = new Calculator();
        a.run();
    }

    public static class HelloWorld extends Application {
        public void run() {
            Application.launch();
        }

        @Override
        public void start(Stage stage) {
            Text text = new Text("Hello World");
            text.setFont(Font.font(90));
            text.setLayoutY(228);
            text.setLayoutX(420);
            text.setFill(Color.WHITE);

            Group group = new Group(text);

            Scene scene = new Scene(group, Color.BLACK);
            stage.setScene(scene);
            stage.setTitle("task 11_12");
            stage.setWidth(1337);
            stage.setHeight(690);
            stage.show();
        }
    }


    public static class Calculator extends Application {
        private double a = 0, b = 0;
        String c;
        private TextField textField;

        public void run() {
            Application.launch();
        }

        private TextField textFieldMaker() {
            textField = new TextField();
            textField.setPrefSize(428, 100);
            textField.setFont(Font.font(30));
            return textField;
        }

        Button newButton(int x, int y, String text) {
            Button res = new Button(text);
            res.setFont(Font.font(80));
            res.setLayoutX(x);
            res.setLayoutY(y);
            res.setPrefSize(200, 200);
            return res;
        }

        void setOnClick(Button btn, String action) {
            btn.setOnAction(actionEvent -> {
                try {
                    a = Double.parseDouble(textField.getText());
                    textField.clear();
                }catch (Exception e) {
                    textField.setText("Wrong input!");
                }
                c = action;
            });
        }

        public void start(Stage stage) {
            Button plus = newButton(0, 100, "+");
            Button minus = newButton(228, 100, "-");
            Button multiply = newButton(0, 328, "*");
            Button divide = newButton(228, 328, "/");
            Button equals = newButton(0, 528, "=");
            equals.setPrefSize(428, 100);

            Group group = new Group(textFieldMaker(), multiply, minus, plus, divide, equals);

            Scene scene = new Scene(group, Color.BLACK);
            stage.setScene(scene);
            stage.setTitle("Big Black Calc");
            stage.setWidth(500);
            stage.setHeight(750);
            stage.show();

            setOnClick(minus, "-");
            setOnClick(plus, "+");
            setOnClick(multiply, "*");
            setOnClick(divide, "/");

            equals.setOnAction(actionEvent -> {
                try {
                    b = Double.parseDouble((textField.getText()));
                    textField.clear();
                }catch (Exception e){
                    textField.setText("Wrong input!");
                }
                calc(c);
            });
        }

        private void calc(String c) {
            switch (c) {
                case "*":
                    textField.setText(String.valueOf(a * b));
                    break;
                case "-":
                    textField.setText(String.valueOf(a - b));
                    break;
                case "/":
                    textField.setText(String.valueOf(a / b));
                    break;
                default:
                    textField.setText(String.valueOf(a + b));
                    break;
            }
        }
    }
}
