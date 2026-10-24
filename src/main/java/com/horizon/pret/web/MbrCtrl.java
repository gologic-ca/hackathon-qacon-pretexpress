package com.horizon.pret.web;

import com.horizon.pret.dao.DmdDao;
import com.horizon.pret.dao.MbrDao;
import com.horizon.pret.model.Usr;
import com.horizon.pret.util.Cst;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MbrCtrl {

    @Autowired
    private MbrDao mbrDao;

    @Autowired
    private DmdDao dmdDao;

    @GetMapping("/mbrs")
    public String list(@RequestParam(required = false) String q, HttpSession s, Model m) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";
        m.addAttribute("usr", u);
        m.addAttribute("q", q);
        m.addAttribute("mbrs", mbrDao.find(q));
        return "mbrs";
    }

    @GetMapping("/mbr")
    public String fiche(@RequestParam int id, HttpSession s, Model m) {
        Usr u = (Usr) s.getAttribute(Cst.SES_USR);
        if (u == null) return "redirect:/login";
        m.addAttribute("usr", u);
        m.addAttribute("mbr", mbrDao.findById(id));
        m.addAttribute("dmds", dmdDao.findByMbr(id));
        return "mbr";
    }
}
