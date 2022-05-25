/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Mac
 */
import JPA.EntityManagerSingleton;
import java.util.List;

import javax.persistence.EntityManager;
import modelo.*;

public abstract class JpaDAO <Entity extends IEntity>{

       private final Class<Entity> entity;
       protected EntityManager entityManager;
       
       protected JpaDAO (Class<Entity> entity) {
           this.entity = entity;
           this.entityManager = getEntityManager();
       }

       private EntityManager getEntityManager() {
           
           entityManager = EntityManagerSingleton.getInstance();

           return entityManager;
       }

       public Entity getById(final int id) {
         return entityManager.find(this.entity, id);
       }

       @SuppressWarnings("unchecked")
       public List<Entity> findAll() {
         return entityManager.createQuery("SELECT A FROM " + this.entity.getName() + " A").getResultList();
       }

       public void persist(Entity entityObj) {
         try {
            entityManager.getTransaction().begin();
            entityManager.persist(entityObj);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
         }
       }

       public void merge(Entity entityObj) {
         try {
            entityManager.getTransaction().begin();
            entityManager.merge(entityObj);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
         }
       }

       public void remove(IEntity entityObj) {
         try {
            entityManager.getTransaction().begin();
            entityObj = entityManager.find(this.entity, entityObj.getId());
            entityManager.remove(entityObj);
            entityManager.getTransaction().commit();
         } catch (Exception ex) {
            ex.printStackTrace();
            entityManager.getTransaction().rollback();
         }
       }

       public void removeById(final int id) {
         try {
            Entity entityObj = getById(id);
            remove(entityObj);
         } catch (Exception ex) {
            ex.printStackTrace();
         }
       }

}
