package com.baton.h2_test.repository;
import com.baton.h2_test.model.AcademicDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.baton.h2_test.model.AcademicDetails;
import java.util.List;


@Repository
public interface AcademicServicesRepository extends CrudRepository<AcademicDetails, Integer> {

    @Query("select s from AcademicDetails s where s.gpa > ?1")
    List<AcademicDetails> FilterByGpa(Float gpa);



}
