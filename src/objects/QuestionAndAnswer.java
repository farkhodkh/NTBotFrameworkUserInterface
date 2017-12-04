package objects;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by USER on 30.11.2017.
 */
public class QuestionAndAnswer {
    private SimpleStringProperty question = new SimpleStringProperty("");
    private SimpleStringProperty answer = new SimpleStringProperty("");
    private SimpleStringProperty keyWord = new SimpleStringProperty("");

    public String getKeyWord() {
        return keyWord.get();
    }

    public SimpleStringProperty keyWordProperty() {
        return keyWord;
    }

    public QuestionAndAnswer(String question, String answer, String keyWord) {
        this.question = new SimpleStringProperty(question);
        this.answer = new SimpleStringProperty(answer);
        this.keyWord =new SimpleStringProperty(keyWord);
    }

    public void setKeyWord(String keyWord) {
        this.keyWord.set(keyWord);
    }

    public String getQuestion() {
        return question.get();
    }

    public void setQuestion(String question) {
        this.question.set(question);
    }

    public String getAnswer() {
        return answer.get();
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public SimpleStringProperty questionProperty(){
        return question;
    }

    public SimpleStringProperty answerProperty(){
        return answer;
    }
    @Override
    public String toString(){
        return "Вопрос: " + question +
                ", Ответ: " + answer +
                ", Ключевые слова: " + keyWord;
    }
}
