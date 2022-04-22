package com.baton.h2_test.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class FinancialDetails {

    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private Integer financial_aid_status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getFinancial_aid_status() {
        return financial_aid_status;
    }

    public void setFinancial_aid_status(Integer financial_aid_status) {
        this.financial_aid_status = financial_aid_status;
    }




}
