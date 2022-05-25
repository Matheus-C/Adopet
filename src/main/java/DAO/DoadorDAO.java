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

public class DoadorDAO extends JpaDAO<Doador> {

       private static DoadorDAO instance;

       public static DoadorDAO getInstance(){
         if (instance == null){
            instance = new DoadorDAO();
         }

         return instance;
       }

       private DoadorDAO() {
         super(Doador.class);
       }

}
