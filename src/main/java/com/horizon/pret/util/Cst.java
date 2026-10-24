package com.horizon.pret.util;

/**
 * Constantes de l'application.
 * Politique credit 2017 (v3) - voir doc PC-2017-03 sur l'ancien intranet
 */
public final class Cst {

    private Cst() {}

    // statuts des demandes
    public static final String BRO = "BRO";
    public static final String SOU = "SOU";
    public static final String APP = "APP";
    public static final String REF = "REF";
    public static final String DEC = "DEC";

    // roles
    public static final String R_CON = "CON";
    public static final String R_ANA = "ANA";

    // types de membre
    public static final String T_REG = "REG";
    public static final String T_EMP = "EMP";

    // limites
    public static final double MAX_MNT = 50000;     // voir aussi JSP
    public static final double RATIO_MAX = 0.40;
    public static final int DUREE_MIN = 12;
    public static final int DUREE_MAX = 84;

    public static final String SES_USR = "usr";
}
