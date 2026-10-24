package com.horizon.pret.model;

public class Usr {

    private String code;
    private String nom;
    private String role;    // CON / ANA

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isAna() {
        return "ANA".equals(role);
    }
}
