package com.horizon.pret.model;

import com.horizon.pret.util.Fmt;

import java.util.Date;

/**
 * Demande de pret personnel.
 */
public class Dmd {

    private int id;
    private int mbrId;
    private String mbrNom;      // jointure, affichage seulement
    private double mnt;         // montant demande
    private int duree;          // en mois
    private double taux;        // taux annuel en %
    private double pmt;         // paiement mensuel
    private double frais;
    private String statut;
    private String crtBy;
    private Date crtDt;
    private Date souDt;
    private String decBy;
    private Date decDt;
    private String motif;

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMbrId() { return mbrId; }
    public void setMbrId(int mbrId) { this.mbrId = mbrId; }

    public String getMbrNom() { return mbrNom; }
    public void setMbrNom(String mbrNom) { this.mbrNom = mbrNom; }

    public double getMnt() { return mnt; }
    public void setMnt(double mnt) { this.mnt = mnt; }

    public int getDuree() { return duree; }
    public void setDuree(int duree) { this.duree = duree; }

    public double getTaux() { return taux; }
    public void setTaux(double taux) { this.taux = taux; }

    public double getPmt() { return pmt; }
    public void setPmt(double pmt) { this.pmt = pmt; }

    public double getFrais() { return frais; }
    public void setFrais(double frais) { this.frais = frais; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getCrtBy() { return crtBy; }
    public void setCrtBy(String crtBy) { this.crtBy = crtBy; }

    public Date getCrtDt() { return crtDt; }
    public void setCrtDt(Date crtDt) { this.crtDt = crtDt; }

    public Date getSouDt() { return souDt; }
    public void setSouDt(Date souDt) { this.souDt = souDt; }

    public String getDecBy() { return decBy; }
    public void setDecBy(String decBy) { this.decBy = decBy; }

    public Date getDecDt() { return decDt; }
    public void setDecDt(Date decDt) { this.decDt = decDt; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    // une demande soumise depuis plus de 30 jours est expiree
    public boolean isExp() {
        if (!"SOU".equals(statut) || souDt == null) return false;
        long j = (System.currentTimeMillis() - souDt.getTime()) / (1000L * 60 * 60 * 24);
        return j > 30;
    }

    public String getStatutLib() {
        if (isExp()) return "Expiree";
        switch (statut) {
            case "BRO": return "Brouillon";
            case "SOU": return "Soumise";
            case "APP": return "Approuvee";
            case "REF": return "Refusee";
            case "DEC": return "Decaissee";
            default: return statut;
        }
    }

    public double getTotal() {
        return pmt * duree;
    }

    public String getMntStr() { return Fmt.mnt(mnt); }
    public String getPmtStr() { return Fmt.mnt(pmt); }
    public String getFraisStr() { return Fmt.mnt(frais); }
    public String getTotalStr() { return Fmt.mnt(getTotal()); }
    public String getTauxStr() { return Fmt.pct(taux); }
    public String getCrtDtStr() { return Fmt.dt(crtDt); }
    public String getSouDtStr() { return Fmt.dt(souDt); }
    public String getDecDtStr() { return Fmt.dt(decDt); }
}
