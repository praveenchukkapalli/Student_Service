package com.baton.h2_test.repository;

import com.baton.h2_test.model.FinancialDetails;
import com.baton.h2_test.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {



    @Query("select s from Student s where s.age > ?1")
    List<Student> FilterByAge(Integer age);




}

