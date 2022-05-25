/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import modelo.Variacao;

/**
 *
 * @author Mac
 */
public class VariacaoDAO extends JpaDAO<Variacao> {

       private static VariacaoDAO instance;

       public static VariacaoDAO getInstance(){
         if (instance == null){
            instance = new VariacaoDAO();
         }

         return instance;
       }

       private VariacaoDAO() {
         super(Variacao.class);
       }

}
