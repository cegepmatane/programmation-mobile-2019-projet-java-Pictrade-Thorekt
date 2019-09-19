package ca.qc.cgmatane.pictrade.donnee;

import java.util.List;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class CommerceDAO {
    private static CommerceDAO instance = null;
    protected BaseDeDonnees accesseurBaseDeDonnees;

    protected List<Commerce> listeCommerces;
}
