package com.emea.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.emea.model.AccountInfoBo;
import com.emea.model.SortCodeBo;
/**
 * Interface to fetch sort codes.
 * 
 * @author hmolla
 *
 */
@Component
public class SortCodeDaoImpl implements SortCodeDao{
    private static final Logger LOG = Logger.getLogger(SortCodeDaoImpl.class);
    
    private static final String SORT_CODE = "sortCode";
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Method to fetch sort codes
     * @param sortCode
     * @return
     */
    public SortCodeBo getSortCode(Long sortCode){
        SortCodeBo result = null;
        LOG.info("Started executing getSortCode");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<SortCodeBo> cq = cb.createQuery(SortCodeBo.class);
        Root<SortCodeBo> sortCodeBo = cq.from(SortCodeBo.class);
        cq.select(sortCodeBo);
        cq.where(cb.equal(sortCodeBo.get(SORT_CODE), sortCode));
        TypedQuery<SortCodeBo> q = entityManager.createQuery(cq);
        List<SortCodeBo> allUsers = q.getResultList();

        if (allUsers != null && allUsers.size() > 0) {
            result = allUsers.get(0);
        }
        LOG.info("Finished executing getSortCode");
        return result;
    }

}
