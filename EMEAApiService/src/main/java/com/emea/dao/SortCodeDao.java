package com.emea.dao;

import org.springframework.stereotype.Component;

import com.emea.model.SortCodeBo;
/**
 * Interface to fetch sort codes.
 * 
 * @author hmolla
 *
 */
@Component
public interface SortCodeDao {

    /**
     * Method to fetch sort codes
     * @param sortCode
     * @return
     */
    public SortCodeBo getSortCode(Long sortCode);

}
