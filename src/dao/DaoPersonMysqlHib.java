package dao;


import model.Person;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import router.Router;
import util.HibernateUtil;

import java.util.ArrayList;

public class DaoPersonMysqlHib implements Dao {
    private static SessionFactory sessionFactory = null;
    private static Session session = null;

    public DaoPersonMysqlHib() {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure().build();
        sessionFactory = new MetadataSources(serviceRegistry).buildMetadata().buildSessionFactory();
        session = sessionFactory.openSession();
    }

    @Override
    public ArrayList<Person> select() {
        return (ArrayList<Person>) session.createCriteria(Person.class).list();
    }

    @Override
    public void insert(Person model) {
        session.beginTransaction();
        session.save(model);
        session.getTransaction().commit();
    }

    @Override
    public void update(Person model) {
        delete(model.getId());
        insert(model);
    }

    public Person getPerson(int id) {
        return session.get(Person.class, id);
    }

    @Override
    public void delete(int id) {
        System.out.println(id);
        session.beginTransaction();
        session.delete(getPerson(id));
        session.getTransaction().commit();
    }
}
