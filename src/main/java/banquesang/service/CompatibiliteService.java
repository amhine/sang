package banquesang.service;

import banquesang.enums.GroupeSang;

public class CompatibiliteService {

    public static boolean isCompatible(GroupeSang donneur, GroupeSang receveur) {
        if (donneur == null || receveur == null) return false;
        if (donneur == GroupeSang.O_MOINS) return true;
        if (receveur == GroupeSang.AB_PLUS) return true;

        switch (donneur) {
            case O_PLUS:
                return receveur == GroupeSang.O_PLUS || receveur == GroupeSang.A_PLUS
                        || receveur == GroupeSang.B_PLUS || receveur == GroupeSang.AB_PLUS;

            case A_MOINS:
                return receveur == GroupeSang.A_MOINS || receveur == GroupeSang.A_PLUS
                        || receveur == GroupeSang.AB_MOINS || receveur == GroupeSang.AB_PLUS;

            case A_PLUS:
                return receveur == GroupeSang.A_PLUS || receveur == GroupeSang.AB_PLUS;

            case B_MOINS:
                return receveur == GroupeSang.B_MOINS || receveur == GroupeSang.B_PLUS
                        || receveur == GroupeSang.AB_MOINS || receveur == GroupeSang.AB_PLUS;

            case B_PLUS:
                return receveur == GroupeSang.B_PLUS || receveur == GroupeSang.AB_PLUS;

            case AB_MOINS:
                return receveur == GroupeSang.AB_MOINS || receveur == GroupeSang.AB_PLUS;

            case AB_PLUS:
                return receveur == GroupeSang.AB_PLUS;

            default:
                return false;
        }
    }
}
