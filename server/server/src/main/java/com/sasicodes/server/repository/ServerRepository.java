package com.sasicodes.server.repository;

import com.sasicodes.server.model.Server;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ServerRepository extends JpaRepository<Server, Long> {

   @Query("select s from Server s where s.ipAddress = ?1")
   Server findByIpAddress(String ipAddress);
}
