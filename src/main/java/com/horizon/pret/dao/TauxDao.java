package com.horizon.pret.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Grille des taux de reference (table TAUX_REF).
 * Mise a jour manuelle par le service credit (script SQL envoye par courriel).
 */
@Repository
public class TauxDao {

    @Autowired
    private JdbcTemplate jdbc;

    public double base(int duree) {
        return jdbc.queryForObject(
                "SELECT TAUX FROM TAUX_REF WHERE DUREE_MAX >= ? ORDER BY DUREE_MAX LIMIT 1",
                Double.class, duree);
    }
}
