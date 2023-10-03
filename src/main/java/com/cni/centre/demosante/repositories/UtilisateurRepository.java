package com.cni.centre.demosante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cni.centre.demosante.entities.Utilisateur;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer>{
	
	public Utilisateur findByUsername(String username);
	public Utilisateur findByUserId(Integer userId );
	public Utilisateur findByUserIdAndUsername(Integer userId , String username);
	public List<Utilisateur> findByUsernameContaining(String username);
	public boolean existsByUsername(String username);

}
