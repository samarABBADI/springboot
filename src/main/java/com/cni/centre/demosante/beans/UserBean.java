package com.cni.centre.demosante.beans;

import com.cni.centre.demosante.entities.Utilisateur;

public class UserBean {
	

private MessageBean messageBean;
private Utilisateur utilisateur;



public UserBean(MessageBean messageBean, Utilisateur utilisateur) {
	
	this.messageBean = messageBean;
	this.utilisateur = utilisateur;
}
public UserBean() {
	
}
public MessageBean getMessageBean() {
	return messageBean;
}
public void setMessageBean(MessageBean messageBean) {
	this.messageBean = messageBean;
}
public Utilisateur getUtilisateur() {
	return utilisateur;
}
public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
}



}
