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

public class EnderecoDAO extends JpaDAO<Endereco> {

       private static EnderecoDAO instance;

       public static EnderecoDAO getInstance(){
         if (instance == null){
            instance = new EnderecoDAO();
         }

         return instance;
       }

       private EnderecoDAO() {
         super(Endereco.class);
       }

}
