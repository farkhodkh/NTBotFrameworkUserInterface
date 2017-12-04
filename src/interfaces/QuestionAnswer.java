package interfaces;

import objects.QuestionAndAnswer;

/**
 * Created by USER on 30.11.2017.
 */
public interface QuestionAnswer {
    void add(QuestionAndAnswer questionAndAnswer);

    void update(QuestionAndAnswer questionAndAnswer);

    void delete(QuestionAndAnswer questionAndAnswer);
}

