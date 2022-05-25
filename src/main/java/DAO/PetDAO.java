/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import javax.persistence.EntityManager;
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

}
