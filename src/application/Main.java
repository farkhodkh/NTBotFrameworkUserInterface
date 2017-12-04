package application;

import interfaces.impls.CollectionQuestionAnswer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import objects.QuestionAndAnswer;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Ввод вопросов/ответо для Skype BOT");
        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(400);
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();

        //testData();
    }

    private void testData(){
//        CollectionQuestionAnswer questionAnswerList = new CollectionQuestionAnswer();
//        QuestionAndAnswer questionAndAnswer1 = new QuestionAndAnswer("Как дела?","Хорошо, у Вас как?");
//        QuestionAndAnswer questionAndAnswer2 = new QuestionAndAnswer("Какая сегодня погода?","Хорошо, дождей не обещают!");
//
//        questionAnswerList.add(questionAndAnswer1);
//        questionAnswerList.add(questionAndAnswer2);

    }
    public static void main(String[] args) {
        launch(args);
    }

}
