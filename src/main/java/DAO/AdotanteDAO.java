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

import modelo.*;

public class AdotanteDAO extends JpaDAO<Doador> {

       private static AdotanteDAO instance;

       public static AdotanteDAO getInstance(){
         if (instance == null){
            instance = new AdotanteDAO();
         }

         return instance;
       }

       private AdotanteDAO() {
         super(Doador.class);
       }

}
