package com.cni.centre.demosante.beans;

public class RoleGroup {
	private Integer idRole;
	private long nbre;
	public Integer getIdRole() {
		return idRole;
	}
	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}
	public long getNbre() {
		return nbre;
	}
	public void setNbre(long nbre) {
		this.nbre = nbre;
	}
	public RoleGroup(Integer idRole, long nbre) {
		super();
		this.idRole = idRole;
		this.nbre = nbre;
	}
	public RoleGroup() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
