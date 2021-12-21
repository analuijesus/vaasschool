package br.com.vaasschool.useful;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUseful {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("vaasschool");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }
}