package com.cni.centre.demosante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.cni.centre.demosante.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	


}
