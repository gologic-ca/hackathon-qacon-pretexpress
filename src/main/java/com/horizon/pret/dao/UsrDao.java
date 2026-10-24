package com.horizon.pret.dao;

import com.horizon.pret.model.Usr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsrDao {

    @Autowired
    private JdbcTemplate jdbc;

    private static final RowMapper<Usr> MAP = (rs, n) -> {
        Usr u = new Usr();
        u.setCode(rs.getString("CODE"));
        u.setNom(rs.getString("NOM"));
        u.setRole(rs.getString("ROLE"));
        return u;
    };

    public List<Usr> findAll() {
        return jdbc.query("SELECT * FROM USR ORDER BY ROLE, NOM", MAP);
    }

    public Usr findByCode(String code) {
        return jdbc.queryForObject("SELECT * FROM USR WHERE CODE = ?", MAP, code);
    }
}
