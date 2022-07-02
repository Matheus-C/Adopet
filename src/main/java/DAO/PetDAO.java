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
import modelo.Adotante;
import modelo.FiltroAdotante;
import modelo.Pet;
import modelo.Variacao;

/**
 *
 * @author Mac
 */
public class PetDAO extends JpaDAO<Pet> {

    private static PetDAO instance;

    public static PetDAO getInstance() {
        if (instance == null) {
            instance = new PetDAO();
        }

        return instance;
    }

    private PetDAO() {
        super(Pet.class);
    }

    public List<Pet> findPetsFor(Adotante adotante) {

        FiltroAdotante filtro = adotante.getFiltro();

        String queryWhere = "";

        Double alturaMenor = filtro.getAlturaMenor();
        if (alturaMenor != null) {
            queryWhere += " AND P.altura<:alturaMenor";
        }
        Double gastoMensalMenor = filtro.getGastoMensalMenor();
        if (gastoMensalMenor != null) {
            queryWhere += " AND P.gastoMensal<:gastoMensalMenor";
        }
        Double pesoMenor = filtro.getPesoMenor();
        if (pesoMenor != null) {
            queryWhere += " AND P.peso<:pesoMenor";
        }
        String especie = filtro.getEspecie();
        if (especie != null) {
            queryWhere += " AND V.especie=:especie";
        }
        String porte = filtro.getPorte();
        if (porte != null) {
            queryWhere += " AND V.porte=:porte";
        }
        String raca = filtro.getRaca();
        if (raca != null) {
            queryWhere += " AND V.raca=:raca";
        }

        if (queryWhere.length() > 0) {
            queryWhere = "WHERE 1=1 " + queryWhere;
        }

        TypedQuery<Pet> query = this.entityManager
                .createQuery("SELECT P FROM " + this.entity.getName() + " P JOIN P.variacao V " + queryWhere, Pet.class);

        if (alturaMenor != null) {
            query.setParameter("alturaMenor", alturaMenor);
        }
        if (gastoMensalMenor != null) {
            query.setParameter("gastoMensalMenor", gastoMensalMenor);
        }
        if (pesoMenor != null) {
            query.setParameter("pesoMenor", pesoMenor);
        }
        if (especie != null) {
            query.setParameter("especie", especie);
        }
        if (porte != null) {
            query.setParameter("porte", porte);
        }
        if (raca != null) {
            query.setParameter("raca", raca);
        }

        List<Pet> petsEncontrados = query.getResultList();

        return petsEncontrados;
    }

}
