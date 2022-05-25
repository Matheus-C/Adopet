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

public class ContatoDAO extends JpaDAO<Contato> {

       private static ContatoDAO instance;

       public static ContatoDAO getInstance(){
         if (instance == null){
            instance = new ContatoDAO();
         }

         return instance;
       }

       private ContatoDAO() {
         super(Contato.class);
       }

}
