package com.emea.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.emea.model.AccountInfoBo;
import com.emea.model.SortCodeBo;
import com.emea.util.CommonUtility;

@RunWith(MockitoJUnitRunner.class)
public class SortCodeDaoTest  {

   
    private SortCodeDao sortCodeDao;
    @Mock
    private EntityManager entityManager;
    @Mock
    CriteriaBuilder cb;
    @Mock
    CriteriaQuery<SortCodeBo> cq;
    @Mock
    Root<SortCodeBo> sortCodeBo;
    @Mock
    TypedQuery<SortCodeBo> q;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        sortCodeDao = new SortCodeDaoImpl();
        CommonUtility.setPrivateField(((SortCodeDaoImpl) sortCodeDao).getClass(), sortCodeDao, "entityManager", entityManager);
        
    }

    @Test
    public void testGetSortCodeWithData() {
        
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(cb.createQuery(SortCodeBo.class)).thenReturn(cq);
        Mockito.when(cq.from(SortCodeBo.class)).thenReturn(sortCodeBo);
        Mockito.when(entityManager.createQuery(cq)).thenReturn(q);
        List<SortCodeBo> sortCodeBos = new ArrayList();
        SortCodeBo sortCodeBo = new SortCodeBo();
        sortCodeBo.setSortCode(122312L);
        sortCodeBos.add(sortCodeBo);
        Mockito.when(q.getResultList()).thenReturn(sortCodeBos);
        
        SortCodeBo sortCodeBoOutput = sortCodeDao
                .getSortCode( 122312L);

        assertNotNull(sortCodeBoOutput);
    }
    
    
    @Test
    public void testGetSortCodeWithNoData() {
        Mockito.when(entityManager.getCriteriaBuilder()).thenReturn(cb);
        Mockito.when(cb.createQuery(SortCodeBo.class)).thenReturn(cq);
        Mockito.when(cq.from(SortCodeBo.class)).thenReturn(sortCodeBo);
        Mockito.when(entityManager.createQuery(cq)).thenReturn(q);
        List<SortCodeBo> sortCodeBos = new ArrayList();
        SortCodeBo sortCodeBo = new SortCodeBo();
        sortCodeBo.setSortCode(122312L);
        sortCodeBos.add(sortCodeBo);
        Mockito.when(q.getResultList()).thenReturn(null);
        
        SortCodeBo sortCodeBoOutput = sortCodeDao
                .getSortCode( 122312L);

        assertNull(sortCodeBoOutput);
    }
    
    

}
