package com.cni.centre.demosante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cni.centre.demosante.beans.MedicamentsBean;
import com.cni.centre.demosante.entities.Medicament;
import com.cni.centre.demosante.entities.Utilisateur;

@Repository
public interface MedicamentRepository extends JpaRepository<Medicament, String>{

	public Medicament findByRef (String ref);
	public Medicament findByIntitile (String intutile);
	public Medicament findByIntitileAndDescription (String intutile, String description);
	public Medicament findByIntitileLike (String intutile);
	public Medicament findByIntitileContaining (String intutile);
	public boolean  existsByIntilile (String intutile);
	public boolean  existsByRef(String ref);
	
	
		@Query(value = "SELECT new com.cni.centre.demosante.beans.MedicamentsBean( m.specialite.libsp ,count(m)) FROM Medicament m GROUP BY m.specialite.libsp")
		List<MedicamentsBean> findMedicamentSpec();
		
		


}
