package com.baton.h2_test.repository;

import com.baton.h2_test.model.FinancialDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialServicesRepository extends CrudRepository<FinancialDetails, Integer> {

    @Query(value="SELECT case when count(s)>0 then true else false end FROM FinancialDetails s WHERE s.id = ?1 AND s.financial_aid_status=0")
    //Query returns boolean, if the object;s Fin_status is 0 and the object with ID exists in Table.
    Boolean CheckFinAidStatus(Integer id);


}
