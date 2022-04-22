package com.baton.h2_test.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class AcademicDetails {
    @Id
    @Column
    private int id;

    public int getId() {
        return id;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column
    private float gpa;

}
