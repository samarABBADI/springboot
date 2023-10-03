package com.cni.centre.demosante.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cni.centre.demosante.beans.RoleBean;
import com.cni.centre.demosante.beans.RoleGroup;
import com.cni.centre.demosante.entities.Role;
import com.cni.centre.demosante.entities.Utilisateur;
import com.cni.centre.demosante.repositories.RoleRepository;
import com.cni.centre.demosante.repositories.UtilisateurRepository;
import com.cni.centre.demosante.services.UtilisateurService;
//@PreAuthorize(value = "")
@CrossOrigin(origins = "http://localhost:8999")
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	RoleRepository roleRepository;
	

	@GetMapping("/list")
	public ResponseEntity<?> getAllRole() {
try {
		List<Role> listRole = roleRepository.findAll();
		
		if(!listRole.isEmpty() && listRole!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(listRole);
		} else {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(listRole);
		}
		
} catch (Exception e) {
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }
	}
	
	@GetMapping("/get-role/{id}")
	public ResponseEntity<?> getRole(@PathVariable Integer id) {
		try {
		Role role=roleRepository.findById(id).orElse(null);
		if(role==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(role);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@GetMapping("/get-role-lib")
	public ResponseEntity<?> getRoleByLibRole(@RequestParam(name="lib") String librole) {
		try {
		Role role=roleRepository.findByLibrol(librole);
		RoleBean rbean= new RoleBean();
		rbean.setRoleId(role.getRoleId());
		//if(rbean==null) {
		//	return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not exist");
		//}
		return ResponseEntity.status(HttpStatus.OK).body(rbean);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	@GetMapping("/get-role-group")
	public ResponseEntity<?> getRoleGroup() {
		try {
			List<RoleGroup> roleg=roleRepository.getRoleGroup();
		
		if(roleg==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not exist");
		}
		return ResponseEntity.status(HttpStatus.OK).body(roleg);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	
	
 
	
}
