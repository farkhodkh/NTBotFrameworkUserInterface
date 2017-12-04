package interfaces.impls;

import interfaces.QuestionAnswer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import objects.QuestionAndAnswer;

import java.util.ArrayList;

/**
 * Created by USER on 01.12.2017.
 */
public class CollectionQuestionAnswer implements QuestionAnswer {

    private ObservableList<QuestionAndAnswer> questionsList = FXCollections.observableArrayList();

    @Override
    public void add(QuestionAndAnswer questionAndAnswer) {
        questionsList.add(questionAndAnswer);
    }

    @Override
    public void update(QuestionAndAnswer questionAndAnswer) {

    }

    @Override
    public void delete(QuestionAndAnswer questionAndAnswer) {
        questionsList.remove(questionAndAnswer);
    }

    public ObservableList<QuestionAndAnswer> getQuestionAndAnswerList(){
        return questionsList;
    }

    public void fillTestData(){
        questionsList.add(new QuestionAndAnswer("Как дела?", "Хорошо, у вас как?", "как, дела, хорошо"));
        questionsList.add(new QuestionAndAnswer("Какая сегодня погода", "Дождей не обещают, для Питера это уже хорошо", "Какая, погода, дождь, Питер"));
        questionsList.add(new QuestionAndAnswer("Какой курс валюты", "1 рубль 50 долл. США. Шутка)", "Какой, курс, валюта, рубль, шутка"));
        questionsList.add(new QuestionAndAnswer("Куда поехать отдыхать", "Зависит от того сколько у Вас денег и фантазий", "Куда, ехать, путушествие, денег, фантазия"));
        questionsList.add(new QuestionAndAnswer("Где взять деньги?", "Работать, Работать и еще раз Работать!", "деньги, взять, работа"));

    }


}
