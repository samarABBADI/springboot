package com.cni.centre.demosante.services;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cni.centre.demosante.beans.MedicamentBean;
import com.cni.centre.demosante.beans.MedicamentRequest;
import com.cni.centre.demosante.beans.MedicamentResponse;
import com.cni.centre.demosante.beans.MedicamentTotal;
import com.cni.centre.demosante.beans.MedicamentsBean;
import com.cni.centre.demosante.beans.MessageBean;
import com.cni.centre.demosante.entities.Medicament;
import com.cni.centre.demosante.entities.Specialite;
import com.cni.centre.demosante.repositories.MedicamentRepository;
import com.cni.centre.demosante.repositories.SpecialiteRepository;


@Service
public class MedicamentService {
	
	@Autowired
	SpecialiteRepository specialiteRepository;
	
	@Autowired
	MedicamentRepository medicamentRepository;
	
	
	public MedicamentResponse addMedicament(MedicamentRequest medicament) {
		MedicamentResponse medResponse = new MedicamentResponse();
			MessageBean msg = new MessageBean();
			
			try {
			String ref = medicament.getIntitule() +"_" +medicament.getSpId();
			
			
			
			Medicament med = medicamentRepository.findById(ref).orElse(null);
			if(med!=null) {
				msg.setCode(0);
				msg.setMessage("medicament deja existe");
				medResponse.setMedicament(med);
				medResponse.setMsg(msg);
				
			} else {
				
				Medicament medic = new Medicament();
				medic.setRef(ref);
				medic.setDescription(medicament.getDescription());
				medic.setIntutile(medicament.getIntitule());
				medic.setNbrecomprimes(medicament.getNbreMed());
				Specialite specialite = specialiteRepository.findById(medicament.getSpId()).get();
				medic.setSpecialite(specialite);
				Medicament medicamentsaved = medicamentRepository.save(medic);
				msg.setCode(1);
				msg.setMessage("medicament ajouté avec succés");
				medResponse.setMedicament(medicamentsaved);
				medResponse.setMsg(msg);
			}
		return medResponse;
			} catch(Exception e) {
				msg.setCode(2);
				msg.setMessage("error interne");
				medResponse.setMedicament(null);
				medResponse.setMsg(msg);
				return medResponse;
			}
		
	}
	
	
	
	
	
	
	
	

public MedicamentTotal totMedicaments() {
	
	List<MedicamentsBean> medicamentList = new ArrayList<MedicamentsBean>();
	MedicamentTotal medicamentTotal = new MedicamentTotal();
	medicamentList = medicamentRepository.findMedicamentSpec();
	long nbreTotal =0;
	for(MedicamentsBean m :medicamentList  ) {
		
		nbreTotal+=m.getNbreMedicament();
	}
	
	
	medicamentTotal.setNbreTotal(nbreTotal);
	
	medicamentTotal.setMedicamentsBean(medicamentList);
	
	return medicamentTotal;
}

/*


public MedicamentBean updateMedicament (String id, Medicament medicament) {
	
	MedicamentBean medicamentBean = new MedicamentBean ();
	MessageBean msg = new MessageBean();
	
	
	Medicament m = medicamentRepository.findById(id).orElse(null);

			if(m!=null) {
				m.setRef(medicament.getRef());
				m.setIntutile(medicament.getIntutile());
				m.setDescription(medicament.getDescription());
				m.setNbrecomprimes(medicament.getNbrecomprimes());
				m.setSpecialite(medicament.getSpecialite());
				medicamentRepository.save(m);
				
				msg.setCode(1);
				msg.setMessage("medicament updated successuful");
				medicamentBean.setMessageBean(msg);
				medicamentBean.setMedicament(m);
			} else {
				msg.setCode(0);
				msg.setMessage("medicament not found");
				medicamentBean.setMessageBean(msg);
				medicamentBean.setMedicament(null);
				
			}
	 
		

	return medicamentBean;
	
}


public MessageBean deleteMedicament (String id) {


MessageBean msg = new MessageBean();

//Utilisateur u = utilisateurRepository.findById(id).orElse(null);
Medicament m = medicamentRepository.findById(id).orElse(null);
	
 
		if(m!=null) {
			medicamentRepository.deleteById(id);
			msg.setCode(1);
			msg.setMessage("medicament deleted successuful");
			
		} else {
			msg.setCode(0);
			msg.setMessage("medicament not found");
		
			
		}
 
	

return msg;

}


*/












}
