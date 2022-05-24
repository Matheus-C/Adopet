/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JPA;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.EntityManager;
/**
 *
 * @author Mac
 */
public class EntityManagerSingleton {

    private static EntityManager instance;

    public static EntityManager getInstance(){
        if (instance == null){
            EntityManagerFactory factory =
                Persistence.createEntityManagerFactory("persistenceUnit");
            instance = factory.createEntityManager();
        }

        return instance;
    }

}
