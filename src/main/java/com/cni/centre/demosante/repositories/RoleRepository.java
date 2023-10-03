package com.cni.centre.demosante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cni.centre.demosante.beans.RoleGroup;
import com.cni.centre.demosante.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	
         public Role findByLibrol (String librole);
	
	@Query(value="select new com.cni.centre.demosante.beans.RoleGroup(c.roleId, count(c)) from Role c"
			+ " group by c.roleId")
	public List<RoleGroup> getRoleGroup();

}
