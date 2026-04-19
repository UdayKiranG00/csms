package com.csms.repository;

import com.csms.entities.Officer;
import com.csms.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficerRepository extends JpaRepository<Officer,Integer> {

    @Query(value="select * from officers where off_name=:uname and off_password=:password",nativeQuery=true)
    public Officer findByUnameAndPassword(@Param("uname") String uname,@Param("password") String password);

    @Query(value="select * from tokens where service_id in " +
     "(select ser_id from off_ser_tbl where off_id=:offId) and state='ACTIVE' order by token_id asc limit 1",nativeQuery=true)
    public Token findTokenByServices(@Param("offId") int off_id);

}
