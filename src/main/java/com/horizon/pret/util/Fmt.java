package com.horizon.pret.util;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Fmt {

    public static String mnt(double d) {
        NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH);
        return nf.format(d);
    }

    public static String dt(Date d) {
        if (d == null) return "";
        return new SimpleDateFormat("yyyy-MM-dd").format(d);
    }

    public static String pct(double d) {
        return String.format(Locale.CANADA_FRENCH, "%.2f %%", d);
    }
}
