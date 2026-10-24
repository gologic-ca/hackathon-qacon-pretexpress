package com.horizon.pret.web;

import com.horizon.pret.dao.DmdDao;
import com.horizon.pret.dao.MbrDao;
import com.horizon.pret.model.Dmd;
import com.horizon.pret.model.Mbr;
import com.horizon.pret.model.Usr;
import com.horizon.pret.svc.CalcSvc;
import com.horizon.pret.svc.EligSvc;
import com.horizon.pret.svc.WkflSvc;
import com.horizon.pret.util.Cst;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Demandes de pret : creation, consultation, decisions.
 */
@Controller
public class DmdCtrl {

    @Autowired
    private DmdDao dmdDao;

    @Autowired
    private MbrDao mbrDao;

    @Autowired
    private CalcSvc calc;

    @Autowired
    private EligSvc elig;

    @Autowired
    private WkflSvc wkfl;

    // file d'attente des demandes soumises
    @GetMapping("/dmds")
    public String file(HttpSession s, Model m) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";
        m.addAttribute("usr", u);
        m.addAttribute("dmds", dmdDao.findByStatut(Cst.SOU));
        return "dmds";
    }

    @GetMapping("/dmd/new")
    public String nw(@RequestParam int mbrId, HttpSession s, Model m) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";
        m.addAttribute("usr", u);
        m.addAttribute("mbr", mbrDao.findById(mbrId));
        return "dmd_form";
    }

    @PostMapping("/dmd/new")
    public String create(@RequestParam int mbrId,
                         @RequestParam double mnt,
                         @RequestParam int duree,
                         HttpSession s, Model m) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";

        Mbr mbr = mbrDao.findById(mbrId);
        List<String> errs = elig.verifier(mbr, mnt, duree);
        if (!errs.isEmpty()) {
            m.addAttribute("usr", u);
            m.addAttribute("mbr", mbr);
            m.addAttribute("errs", errs);
            m.addAttribute("mnt", mnt);
            m.addAttribute("duree", duree);
            return "dmd_form";
        }

        Dmd d = new Dmd();
        d.setMbrId(mbrId);
        d.setMnt(mnt);
        d.setDuree(duree);
        d.setTaux(calc.taux(mbr, duree));
        d.setFrais(calc.frais(mnt));
        d.setPmt(calc.pmt(mnt + d.getFrais(), d.getTaux(), duree));
        d.setCrtBy(u.getCode());
        int id = dmdDao.insert(d);
        return "redirect:/dmd?id=" + id;
    }

    @GetMapping("/dmd")
    public String detail(@RequestParam int id, HttpSession s, Model m) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";
        Dmd d = dmdDao.findById(id);
        m.addAttribute("usr", u);
        m.addAttribute("d", d);
        m.addAttribute("mbr", mbrDao.findById(d.getMbrId()));
        return "dmd";
    }

    @PostMapping("/dmd/act")
    public String action(@RequestParam int id,
                         @RequestParam String act,
                         @RequestParam(required = false) String motif,
                         HttpSession s) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";
        Dmd d = dmdDao.findById(id);
        if ("SOU".equals(act)) {
            wkfl.soumettre(d);
        } else if ("APP".equals(act)) {
            wkfl.approuver(d, u);
        } else if ("REF".equals(act)) {
            wkfl.refuser(d, u, motif);
        } else if ("DEC".equals(act)) {
            wkfl.decaisser(d, u);
        }
        return "redirect:/dmd?id=" + id;
    }
}
