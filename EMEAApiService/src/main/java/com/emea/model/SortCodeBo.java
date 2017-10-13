package com.emea.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SORT_CODE")
public class SortCodeBo {
    @Id
    @NotNull
    @Column(name = "SORT_CODE")
    long sortCode;

    public long getSortCode() {
        return sortCode;
    }

    public void setSortCode(long sortCode) {
        this.sortCode = sortCode;
    }
}
