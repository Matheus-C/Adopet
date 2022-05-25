/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import modelo.Instituicao;

/**
 *
 * @author Mac
 */
public class InstituicaoDAO extends JpaDAO<Instituicao> {

       private static InstituicaoDAO instance;

       public static InstituicaoDAO getInstance(){
         if (instance == null){
            instance = new InstituicaoDAO();
         }

         return instance;
       }

       private InstituicaoDAO() {
         super(Instituicao.class);
       }

}
