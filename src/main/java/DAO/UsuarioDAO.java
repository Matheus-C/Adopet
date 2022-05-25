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

public class UsuarioDAO extends JpaDAO<Usuario> {

       private static UsuarioDAO instance;

       public static UsuarioDAO getInstance(){
         if (instance == null){
            instance = new UsuarioDAO();
         }

         return instance;
       }

       private UsuarioDAO() {
         super(Usuario.class);
       }

}
