package com.horizon.pret.dao;

import com.horizon.pret.model.Mbr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MbrDao {

    @Autowired
    private JdbcTemplate jdbc;

    // ELIG : pastille verte/rouge dans la liste des membres
    private static final String SEL =
            "SELECT M.*, CASE WHEN M.COTE >= 680 THEN TRUE ELSE FALSE END AS ELIG FROM MBR M ";

    private static final RowMapper<Mbr> MAP = (rs, n) -> {
        Mbr m = new Mbr();
        m.setId(rs.getInt("ID"));
        m.setNoMbr(rs.getString("NO_MBR"));
        m.setNom(rs.getString("NOM"));
        m.setPrenom(rs.getString("PRENOM"));
        m.setDtAdh(rs.getDate("DT_ADH"));
        m.setRevAnn(rs.getDouble("REV_ANN"));
        m.setDettesMens(rs.getDouble("DETTES_MENS"));
        m.setCote(rs.getInt("COTE"));
        m.setTyp(rs.getString("TYP"));
        m.setElig(rs.getBoolean("ELIG"));
        return m;
    };

    public List<Mbr> find(String q) {
        if (q == null) q = "";
        return jdbc.query(SEL + "WHERE UPPER(M.NOM) LIKE ? OR M.NO_MBR LIKE ? ORDER BY M.NOM",
                MAP, "%" + q.toUpperCase() + "%", "%" + q + "%");
    }

    public Mbr findById(int id) {
        return jdbc.queryForObject(SEL + "WHERE M.ID = ?", MAP, id);
    }
}
