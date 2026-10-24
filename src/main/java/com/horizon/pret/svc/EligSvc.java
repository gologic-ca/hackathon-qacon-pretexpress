package com.horizon.pret.svc;

import com.horizon.pret.model.Mbr;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Admissibilite d'un membre a un pret personnel.
 * Retourne la liste des motifs de refus (vide = admissible).
 */
@Service
public class EligSvc {

    public List<String> verifier(Mbr m, double mnt, int duree) {
        List<String> err = new ArrayList<>();

        if (m.getCote() < 680) {
            err.add("Cote de credit insuffisante");
        }

        double revMens = m.getRevAnn() / 12;
        double ratio = m.getDettesMens() / revMens;
        if (ratio > 0.44) {
            err.add("Ratio d'endettement trop eleve");
        }

        if (mnt > m.getRevAnn() * 0.5) {
            err.add("Montant superieur a 50 % du revenu annuel");
        }

        int anc = moisAnc(m.getDtAdh());
        if (anc < 6 && mnt > 10000) {
            err.add("Membre depuis moins de 6 mois : maximum 10 000 $");
        }

        return err;
    }

    private int moisAnc(Date d) {
        long j = (new Date().getTime() - d.getTime()) / (1000L * 60 * 60 * 24);
        return (int) (j / 30);
    }
}
