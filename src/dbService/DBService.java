package dbService;

import dbService.dao.QuestionAndAnswerDAO;
import dbService.dataSets.QuestionAndAnswerDataSet;
import objects.QuestionAndAnswer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";

    private final SessionFactory sessionFactory;


    public DBService() {
        Configuration configuration = getMSSqlConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getMSSqlConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(QuestionAndAnswerDataSet.class);

        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServer2012Dialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.microsoft.sqlserver.jdbc.SQLServerDriver");
        configuration.setProperty("hibernate.connection.url", "jdbc:sqlserver://localhost:1433/QUESTIONSANDANSWERS");
        configuration.setProperty("hibernate.connection.username", "saadmin");
        configuration.setProperty("hibernate.connection.password", "19820809");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;
    }

    public long addQuestionAndAnswerData(QuestionAndAnswer questionAndAnswer) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            QuestionAndAnswerDAO dao = new QuestionAndAnswerDAO(session);

            QuestionAndAnswerDataSet questionAndAnswerDataSet = new QuestionAndAnswerDataSet(questionAndAnswer.getQuestion(), questionAndAnswer.getAnswer(), questionAndAnswer.getKeyWord());

            long id = dao.insertQuestionAndAnswer(questionAndAnswerDataSet);

            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
}
