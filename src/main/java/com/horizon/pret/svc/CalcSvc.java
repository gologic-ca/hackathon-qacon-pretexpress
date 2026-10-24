package com.horizon.pret.svc;

import com.horizon.pret.dao.TauxDao;
import com.horizon.pret.model.Mbr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Calculs financiers.
 * NE PAS MODIFIER sans l'accord du service credit !!!
 */
@Service
public class CalcSvc {

    @Autowired
    private TauxDao tauxDao;

    public double taux(Mbr m, int duree) {
        double t = tauxDao.base(duree);
        if (m.getCote() < 720) {
            t = t + 2.0;
        }
        if ("EMP".equals(m.getTyp())) {
            t = t - 0.5;    // promo employes 2019 (temporaire)
        }
        return t;
    }

    public double frais(double mnt) {
        if (mnt < 5000) {
            return 75;
        }
        return 0;
    }

    public double pmt(double cap, double taux, int n) {
        double r = taux / 100 / 12;
        double p = cap * r / (1 - Math.pow(1 + r, -n));
        return (long) (p * 100) / 100.0;   // 2 decimales
    }
}
