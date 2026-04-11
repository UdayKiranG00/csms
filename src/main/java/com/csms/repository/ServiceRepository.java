package com.csms.repository;

import com.csms.entities.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceRecord,Integer> {

    @Query(value="select document_name from documents where document_id in " +
            "(select document_id from ser_doc_tbl where service_id = :serviceid)", nativeQuery=true)
    public List<String> getDocs(@Param("serviceid") int serviceId);
}
