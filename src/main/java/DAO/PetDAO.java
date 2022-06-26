/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import modelo.Pet;

/**
 *
 * @author Mac
 */
public class PetDAO extends JpaDAO<Pet> {

       private static PetDAO instance;

       public static PetDAO getInstance(){
         if (instance == null){
            instance = new PetDAO();
         }

         return instance;
       }

       private PetDAO() {
         super(Pet.class);
       }
       
       public List<Pet> getWantedPetsByUserId(Long userId){
         TypedQuery<Pet> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE desejadoPor LIKE :id AND adotado=false", Pet.class);

         List<Pet> wantedPets = query.setParameter("id", "%"+userId+"%").getResultList();

         return wantedPets;
       }

}
