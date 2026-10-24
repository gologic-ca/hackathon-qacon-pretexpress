package com.horizon.pret.model;

import com.horizon.pret.util.Fmt;

import java.util.Date;

/**
 * Membre de la caisse.
 */
public class Mbr {

    private int id;
    private String noMbr;
    private String nom;
    private String prenom;
    private Date dtAdh;         // date d'adhesion
    private double revAnn;      // revenu annuel brut
    private double dettesMens;  // paiements mensuels de dettes existantes
    private int cote;           // cote de credit
    private String typ;         // REG / EMP
    private boolean elig;       // calcule par la requete (voir MbrDao)

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNoMbr() { return noMbr; }
    public void setNoMbr(String noMbr) { this.noMbr = noMbr; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }

    public Date getDtAdh() { return dtAdh; }
    public void setDtAdh(Date dtAdh) { this.dtAdh = dtAdh; }

    public double getRevAnn() { return revAnn; }
    public void setRevAnn(double revAnn) { this.revAnn = revAnn; }

    public double getDettesMens() { return dettesMens; }
    public void setDettesMens(double dettesMens) { this.dettesMens = dettesMens; }

    public int getCote() { return cote; }
    public void setCote(int cote) { this.cote = cote; }

    public String getTyp() { return typ; }
    public void setTyp(String typ) { this.typ = typ; }

    public boolean isElig() { return elig; }
    public void setElig(boolean elig) { this.elig = elig; }

    public String getNomComplet() {
        return prenom + " " + nom;
    }

    public String getRevAnnStr() { return Fmt.mnt(revAnn); }
    public String getDettesMensStr() { return Fmt.mnt(dettesMens); }
    public String getDtAdhStr() { return Fmt.dt(dtAdh); }
}
