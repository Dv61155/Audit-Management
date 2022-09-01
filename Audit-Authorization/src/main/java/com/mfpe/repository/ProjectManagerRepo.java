package com.mfpe.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mfpe.model.AuthenticationRequest;

@Repository
public interface ProjectManagerRepo extends JpaRepository<AuthenticationRequest, String>{

}
