package com.horizon.pret.dao;

import com.horizon.pret.model.Dmd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class DmdDao {

    @Autowired
    private JdbcTemplate jdbc;

    private static final String SEL =
            "SELECT D.*, M.PRENOM || ' ' || M.NOM AS MBR_NOM FROM DMD D JOIN MBR M ON M.ID = D.MBR_ID ";

    private static final RowMapper<Dmd> MAP = (rs, n) -> {
        Dmd d = new Dmd();
        d.setId(rs.getInt("ID"));
        d.setMbrId(rs.getInt("MBR_ID"));
        d.setMbrNom(rs.getString("MBR_NOM"));
        d.setMnt(rs.getDouble("MNT"));
        d.setDuree(rs.getInt("DUREE"));
        d.setTaux(rs.getDouble("TAUX"));
        d.setPmt(rs.getDouble("PMT"));
        d.setFrais(rs.getDouble("FRAIS"));
        d.setStatut(rs.getString("STATUT"));
        d.setCrtBy(rs.getString("CRT_BY"));
        d.setCrtDt(rs.getTimestamp("CRT_DT"));
        d.setSouDt(rs.getTimestamp("SOU_DT"));
        d.setDecBy(rs.getString("DEC_BY"));
        d.setDecDt(rs.getTimestamp("DEC_DT"));
        d.setMotif(rs.getString("MOTIF"));
        return d;
    };

    public List<Dmd> findByMbr(int mbrId) {
        return jdbc.query(SEL + "WHERE D.MBR_ID = ? ORDER BY D.CRT_DT DESC", MAP, mbrId);
    }

    public List<Dmd> findByStatut(String statut) {
        return jdbc.query(SEL + "WHERE D.STATUT = ? ORDER BY D.SOU_DT", MAP, statut);
    }

    public Dmd findById(int id) {
        return jdbc.queryForObject(SEL + "WHERE D.ID = ?", MAP, id);
    }

    public int insert(Dmd d) {
        jdbc.update("INSERT INTO DMD (MBR_ID, MNT, DUREE, TAUX, PMT, FRAIS, STATUT, CRT_BY, CRT_DT) "
                        + "VALUES (?, ?, ?, ?, ?, ?, 'BRO', ?, ?)",
                d.getMbrId(), d.getMnt(), d.getDuree(), d.getTaux(), d.getPmt(), d.getFrais(),
                d.getCrtBy(), new Timestamp(System.currentTimeMillis()));
        // recuperer l'id genere
        return jdbc.queryForObject("SELECT MAX(ID) FROM DMD", Integer.class);
    }

    public void soumettre(int id) {
        jdbc.update("UPDATE DMD SET STATUT = 'SOU', SOU_DT = ? WHERE ID = ?",
                new Timestamp(System.currentTimeMillis()), id);
    }

    public void decider(int id, String statut, String usr, String motif) {
        jdbc.update("UPDATE DMD SET STATUT = ?, DEC_BY = ?, DEC_DT = ?, MOTIF = ? WHERE ID = ?",
                statut, usr, new Timestamp(System.currentTimeMillis()), motif, id);
    }

    public void decaisser(int id) {
        jdbc.update("UPDATE DMD SET STATUT = 'DEC' WHERE ID = ?", id);
    }
}
