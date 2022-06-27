/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.util.Date;
import java.util.List;
import java.util.Set;
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
       
      public Pet findById(int id) {
        TypedQuery<Pet> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE id=:id AND adotado=false", Pet.class);

        Pet petEncontrado = query.setParameter("id", id).getResultList().get(0);

            return petEncontrado;

      }

      public List<Pet> findByIdDono(int id) {
        TypedQuery<Pet> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE id_dono=:id", Pet.class);

        List<Pet> petsEncontrados = query.setParameter("id", id).getResultList();

        if (!petsEncontrados.isEmpty()) {
            return petsEncontrados;
        }

        return null;

      }

      public List<Pet> findFilter(float gasto, int id ){
        TypedQuery<Pet> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE id_variacao=:id AND gastoMensal<=:gasto", Pet.class);

        List<Pet> petsEncontrados = query.setParameter("id", id).setParameter("gasto", gasto).getResultList();

        if (!petsEncontrados.isEmpty()) {
            return petsEncontrados;
        }

        return null;
      }

      public void updatePet(int id, String nome, 
      Date dataNasc, 
      double peso,
      double altura,
      Variacao variacao,
      double gasto, 
      String obs,
      String fotos){
        TypedQuery<Pet> query = (TypedQuery<Pet>) this.entityManager
                .createQuery("UPDATE "+ this.entity.getName() + " SET nome=:nome, SET dataNasc=:dataNasc, SET peso=:peso, SET altura=:altura, SET variacao=:variacao, SET gasto=:gasto, SET obs=:obs, SET fotos=:fotos WHERE id=:id");
                int updateCount = query.setParameter("nome", nome)
                .setParameter("dataNasc", dataNasc)
                .setParameter("peso", peso)
                .setParameter("altura", altura)
                .setParameter("variacao", variacao)
                .setParameter("gasto", gasto)
                .setParameter("obs", obs)
                .setParameter("fotos", fotos)
                .setParameter("id", id).executeUpdate();
      }

      public void mudarDesejo(int idPet, int idUser){
        TypedQuery<Pet> query = this.entityManager
                .createQuery("SELECT A FROM " + this.entity.getName() + " A WHERE id=:id", Pet.class);
          Set<Integer> desejam;
          desejam = query.setParameter("id", idPet).getResultList().get(0).getDesejadoPor();
          desejam.add(idUser);
        query = (TypedQuery<Pet>) this.entityManager
                .createQuery("UPDATE "+ this.entity.getName() + " SET desejadoPor=:desejo WHERE id=:id");
        int updateCount = query.setParameter("desejo", desejam)
                               .setParameter("id", idPet).executeUpdate();

      }
}
