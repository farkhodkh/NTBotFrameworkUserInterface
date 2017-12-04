package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="QUESTIONSANDANSWERS")
public class QuestionAndAnswerDataSet implements Serializable{
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "question", unique = true, updatable = false)
    private String question;

    public QuestionAndAnswerDataSet(String question, String answer, String keyWord) {
        this.question = question;
        this.answer = answer;
        this.keyWord = keyWord;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public long getId() {

        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public String getKeyWord() {
        return keyWord;
    }

    @Column(name = "answer", unique = true, updatable = false)
    private String answer;

    @Column(name = "keyWord")
    private String keyWord;

    @Override
    public String toString() {
        return "QuestionAndAnswer{" +
                "Номер=" + id +
                ", Вопрос='" + question + '\'' +
                ", Ответ='" + answer + '\'' +
                ", Ключевые слова='" + keyWord + '\'' +
                '}';
    }
}
