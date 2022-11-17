package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.stream.IntStream;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage){

        //описание поля ввода
        TextField field = new TextField("Поле ввода");
        field.setFont(new Font(18));
        field.setPrefHeight(50);

        //описание кнопки ввода данных
        Button enterButton = new Button("Ввод");
        enterButton.setPrefWidth(550);
        enterButton.setPrefHeight(50);
        enterButton.setFont(new Font(18));

        Button infoButton = new Button("О программе");


        //описание зоны текста
        TextArea mainText = new TextArea("Поле ответа");
        mainText.setPrefHeight(600);
        mainText.setWrapText(true);
        mainText.setFont(new Font(18));
        //mainText.setEditable(false);

        Alert alert = new Alert(Alert.AlertType.NONE,
                """
                        Автор: Красильников Егор

                        О программе:
                        В поле ввода сверху введите число простых чисел которое хотите получить (от 1 до 1000), программа выведет их в порядке возрастания, начиная с числа 2""",
                                ButtonType.OK);


        //событие при нажатии на кнопку ввода
        enterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                enterButton.setDisable(true);
                ArrayList<Integer> list = new ArrayList<>();//список с полученными значениями

                String str = field.getCharacters().toString();//получаем значение поля ввода, в блоке try, catch обрабатываем возможность исключения при конвертации из String в int, если на входе пришел символ, а не число
                try{
                    int n = Integer.parseInt(str);

                    if(n > 1000){
                        n = 1000;
                        field.setText("1000");
                    }
                    else if(n<=0){
                        n = 1;
                        field.setText("1");
                    }

                    //перебираем все числа, проверяя их на простоту, пока переменная-счетчик совпадений не будет равна числу, полученному на входе
                    int i = 0;//переменная счетчик
                    int num = 2;//число, с которого нужно начать перебор
                    while(i != n){
                        if(isPrime(num)){//проверка простое ли число
                            i++;
                            list.add(num);//если число простое, добавляем в список простых чисел
                        }
                        num++;
                    }

                    //собираем красивый ответ в StringBuilder, чтобы не грузить память постоянным пересоздаием простого String при конкатенации
                    StringBuilder answ = new StringBuilder();
                    for (int number:
                            list) {
                        answ.append(number);
                        answ.append(" ");
                    }
                    mainText.setText(answ.toString());//выводим ответ
                }catch (Exception e){
                    mainText.setText("Принимаются только числа");//ответ при получении символа, а не числа на входе программы
                }
                enterButton.setDisable(false);
            }
        });

        infoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                alert.showAndWait();
            }
        });

        //загрузка всех элепентов в окно программы
        VBox flowPane = new VBox(field, enterButton, mainText, infoButton);
        Scene scene = new Scene(flowPane);
        stage.setScene(scene);

        //настройка окна программы
        stage.setTitle("Задача про простые числа");
        stage.setHeight(800);
        stage.setWidth(550);
        stage.setResizable(true);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    //функция проверки на простое число
    public static boolean isPrime(int number) {
        if (number<=1)
            return false;
        else if (number <=3)
            return true;
        else if (number%2==0 || number %3 ==0)
            return false;
        int n = 5;
        while (n*n <=number){
            if (number % n ==0 || number % (n+2) == 0)
                return false;
            n=n+6;
        }
        return true;
    }
}