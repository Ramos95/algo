/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.util.Collections;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import ues.occ.edu.sv.ingenieria.prn335.cineData.entity.Genero;

/**
 *
 * @author estuardo
 */
@Stateless
public class GeneroFacade extends AbstractFacade<Genero> {
     @PersistenceContext(unitName = "cinePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GeneroFacade() {
        super(Genero.class);
    }
    
    public List<Genero> complete(String parametro){
        if (em != null) {
            Query query = em.createQuery("SELECT g FROM Genero g WHERE g.nombre LIKE :nombre");
            query.setParameter("nombre", parametro + "%");
            return query.getResultList();
        }
        return Collections.EMPTY_LIST;
    }
}
