package com.horizon.pret.svc;

import com.horizon.pret.dao.DmdDao;
import com.horizon.pret.model.Dmd;
import com.horizon.pret.model.Usr;
import com.horizon.pret.util.Cst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Cycle de vie d'une demande.
 */
@Service
public class WkflSvc {

    @Autowired
    private DmdDao dao;

    public void soumettre(Dmd d) {
        if (!Cst.BRO.equals(d.getStatut())) {
            throw new IllegalStateException("Seul un brouillon peut etre soumis");
        }
        dao.soumettre(d.getId());
    }

    public void approuver(Dmd d, Usr u) {
        // les validations sont faites dans l'ecran
        dao.decider(d.getId(), Cst.APP, u.getCode(), null);
    }

    public void refuser(Dmd d, Usr u, String motif) {
        if (motif == null || motif.trim().isEmpty()) {
            throw new IllegalArgumentException("Le motif de refus est obligatoire");
        }
        dao.decider(d.getId(), Cst.REF, u.getCode(), motif);
    }

    public void decaisser(Dmd d, Usr u) {
        // TODO verifier avec Sophie si on doit tracer qui decaisse
        dao.decaisser(d.getId());
    }
}
