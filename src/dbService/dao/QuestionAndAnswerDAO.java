package dbService.dao;

import dbService.dataSets.QuestionAndAnswerDataSet;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class QuestionAndAnswerDAO {
    private Session session;

    public QuestionAndAnswerDAO(Session session) {
        this.session = session;
    }

    public QuestionAndAnswerDataSet get(long id) throws HibernateException {
        return (QuestionAndAnswerDataSet) session.get(QuestionAndAnswerDataSet.class, id);
    }

    public QuestionAndAnswerDataSet get(String searchText) throws HibernateException {
        return (QuestionAndAnswerDataSet) session.find(QuestionAndAnswerDataSet.class, searchText);
    }
//    public long getQuestionAndAnswerId(String name) throws HibernateException {
//        Criteria criteria = session.createCriteria(QuestionAndAnswerDataSet.class);
//        return ((QuestionAndAnswerDataSet) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
//    }

    public long insertQuestionAndAnswer(QuestionAndAnswerDataSet questionAndAnswerDataSet) throws HibernateException {
        return (Long) session.save(questionAndAnswerDataSet);
    }
}
