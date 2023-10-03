package com.cni.centre.demosante.services;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cni.centre.demosante.beans.MessageBean;
import com.cni.centre.demosante.beans.UserBean;
import com.cni.centre.demosante.entities.Utilisateur;
import com.cni.centre.demosante.repositories.RoleRepository;
import com.cni.centre.demosante.repositories.SpecialiteRepository;
import com.cni.centre.demosante.repositories.UtilisateurRepository;
import com.cni.centre.demosante.utils.Cryptage;

@Service
public class UtilisateurService {
	
	@Autowired
	UtilisateurRepository utilisateurRepository;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	SpecialiteRepository specialiteRepository;
	
	public UserBean addUser (Utilisateur user) {
		
		UserBean userBean = new UserBean ();
		MessageBean msg = new MessageBean();
		 try {
		if(utilisateurRepository.existsByUsername(user.getUsername())) {
			
			Utilisateur u = utilisateurRepository.findByUsername(user.getUsername());
			System.out.println("user found" +u.getUsername());
			msg.setCode(1);
			msg.setMessage("user exist");
			userBean.setMessageBean(msg);
			userBean.setUtilisateur(u);
		} else {
			Utilisateur newUser = new Utilisateur();
			newUser.setEmail(user.getEmail());
		System.out.println("le password " +Cryptage.crypte("cni@2023"));
		newUser.setPassword(Cryptage.crypte("cni@2023"));
		newUser.setRole(user.getRole());
		newUser.setSpecialite(user.getSpecialite());
		newUser.setUsername(user.getUsername());
		newUser.setDateCreation(new Date());
		newUser.setStatus(0);
		Utilisateur savedUser = utilisateurRepository.save(newUser);
		msg.setCode(2);
		msg.setMessage("user created successufuly");
		userBean.setMessageBean(msg);
		userBean.setUtilisateur(savedUser);
		
		}
		System.out.println("user found !!!!!!!!!!" +userBean.getUtilisateur().getUsername());
		return userBean;
		 } catch (Exception e) {
		      return new UserBean(new MessageBean(0,"error"),null);
		    }
	}

public UserBean updateUser (Integer id, Utilisateur user) {
	
		UserBean userBean = new UserBean ();
		MessageBean msg = new MessageBean();
		
	//	Utilisateur u = utilisateurRepository.findById(id).orElse(null);
		
		Utilisateur u = utilisateurRepository.findByUserId(id);
		System.out.println("tttttttttttt");
				if(u!=null) {
					u.setUsername(user.getUsername());
					u.setEmail(user.getEmail());
					
					u.setPassword(Cryptage.crypte(user.getPassword()));
					u.setRole(user.getRole());
					u.setSpecialite(user.getSpecialite());
					u.setUsername(user.getUsername());
					u.setDateCreation(new Date());
					u.setStatus(1);
					utilisateurRepository.save(u);
					msg.setCode(1);
					msg.setMessage("user updated successuful");
					userBean.setMessageBean(msg);
					userBean.setUtilisateur(u);
				} else {
					msg.setCode(0);
					msg.setMessage("user not found");
					userBean.setMessageBean(msg);
					userBean.setUtilisateur(null);
					
				}
		 
			
	
		return userBean;
		
	}


public MessageBean deleteUser (Integer id) {
	
	
	MessageBean msg = new MessageBean();
	
	//Utilisateur u = utilisateurRepository.findById(id).orElse(null);
	Utilisateur u = utilisateurRepository.findById(id).orElse(null);
		
	 
			if(u!=null) {
				utilisateurRepository.deleteById(id);;;
				msg.setCode(1);
				msg.setMessage("user deleted successuful");
				
			} else {
				msg.setCode(0);
				msg.setMessage("user not found");
			
				
			}
	 
		

	return msg;
	
}
	
	
}
