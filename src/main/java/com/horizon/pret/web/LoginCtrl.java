package com.horizon.pret.web;

import com.horizon.pret.dao.UsrDao;
import com.horizon.pret.util.Cst;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Connexion simplifiee (l'authentification reelle est faite par le portail interne).
 */
@Controller
public class LoginCtrl {

    @Autowired
    private UsrDao usrDao;

    @GetMapping("/")
    public String idx() {
        return "redirect:/mbrs";
    }

    @GetMapping("/login")
    public String login(Model m) {
        m.addAttribute("usrs", usrDao.findAll());
        return "login";
    }

    @PostMapping("/login")
    public String doLogin(@RequestParam String code, HttpSession s) {
        s.setAttribute(Cst.SES_USR, usrDao.findByCode(code));
        return "redirect:/mbrs";
    }

    @GetMapping("/logout")
    public String logout(HttpSession s) {
        s.invalidate();
        return "redirect:/login";
    }
}
