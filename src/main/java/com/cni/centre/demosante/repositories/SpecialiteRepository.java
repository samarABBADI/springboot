package com.cni.centre.demosante.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cni.centre.demosante.entities.Specialite;

@Repository
public interface SpecialiteRepository extends JpaRepository<Specialite, Integer>{
	
	

}
