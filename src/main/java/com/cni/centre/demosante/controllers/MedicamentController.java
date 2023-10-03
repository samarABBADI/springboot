package com.cni.centre.demosante.controllers;

import java.util.ArrayList;
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

import com.cni.centre.demosante.beans.MedicamentRequest;
import com.cni.centre.demosante.beans.MedicamentResponse;
import com.cni.centre.demosante.beans.MedicamentTotal;
import com.cni.centre.demosante.beans.MedicamentsBean;
import com.cni.centre.demosante.beans.RoleBean;
import com.cni.centre.demosante.beans.RoleGroup;
import com.cni.centre.demosante.entities.Medicament;
import com.cni.centre.demosante.entities.Role;
import com.cni.centre.demosante.entities.Utilisateur;
import com.cni.centre.demosante.repositories.MedicamentRepository;
import com.cni.centre.demosante.repositories.RoleRepository;
import com.cni.centre.demosante.repositories.UtilisateurRepository;
import com.cni.centre.demosante.services.MedicamentService;
import com.cni.centre.demosante.services.UtilisateurService;
//@PreAuthorize(value = "")
@CrossOrigin(origins = "http://localhost:8999")
@RestController
@RequestMapping("/medicaments")
public class MedicamentController {
	
	@Autowired
	MedicamentRepository medRepository;
	
	@Autowired 
	MedicamentService medService;
	

	@GetMapping("/hello")
	public ResponseEntity<?> helloWord() {
  return ResponseEntity.status(HttpStatus.OK).body("bonjour");
		
	}
	
	@PostMapping("/add-med")
	public ResponseEntity<?> addMedicament(@RequestBody MedicamentRequest medReq) {
		
		MedicamentResponse medResponse = medService.addMedicament(medReq);
		  return ResponseEntity.status(HttpStatus.OK).body(medResponse);
				
			}

	@GetMapping("/list")
	public ResponseEntity<?> getAllMedicament() {
		try {
		List<Medicament> medList = new ArrayList<Medicament> ();
		medList=medRepository.findAll();
		
		List<MedicamentRequest> medReqList = new ArrayList<MedicamentRequest> ();
		
		
		
		if(medList.isEmpty() || medList==null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} else {
			
			for(Medicament med:medList) {
				MedicamentRequest mreq = new MedicamentRequest();
				mreq.setDescription(med.getDescription());
				mreq.setIntitule(med.getIntitile());
				mreq.setNbreMed(med.getNbrecomprimes());
				mreq.setSpId(med.getSpecialite().getSpId());
				medReqList.add(mreq);
			}
  return ResponseEntity.status(HttpStatus.OK).body(medList);
		
	}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	

	//@GetMapping("/get-med/{ref}")
//	public ResponseEntity<?> getMedicamentByRef(@PathVariable String ref) {
	@GetMapping("/get-med")
	public ResponseEntity<?> getMedicamentByRef(@RequestParam(name="r") String ref) {
		try {
		
		Medicament medicament=medRepository.findByRef(ref);
	
		if(medicament==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			
			
  return ResponseEntity.status(HttpStatus.OK).body(medicament);
		
	}
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
 
	
	@GetMapping("/group-medicaments")
	public ResponseEntity<?> getGroupMedicaments() {
try {
		List<MedicamentsBean> listMedicamentBean = medRepository.findMedicamentSpec();

		if(!listMedicamentBean.isEmpty() && listMedicamentBean!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(listMedicamentBean);
		} else {
			return ResponseEntity.noContent().build();
		}
		
} catch (Exception e) {
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }
	}
	
	@GetMapping("/total-medicaments")
	public ResponseEntity<?> getTotalGroupMedicaments() {
try {
		MedicamentTotal listMedicamentBean = medService.totMedicaments();

		if( listMedicamentBean!=null) {
			return ResponseEntity.status(HttpStatus.OK).body(listMedicamentBean);
		} else {
			return ResponseEntity.noContent().build();
		}
		
} catch (Exception e) {
    return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
  }
	}
	
	
	
}
