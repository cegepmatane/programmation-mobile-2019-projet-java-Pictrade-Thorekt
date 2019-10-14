package ca.qc.cgmatane.pictrade.Trieurs;

import java.util.Comparator;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public class TriParNom implements Comparator<Commerce> {
    public int compare(Commerce a, Commerce b){

        return a.getNom().compareTo(b.getNom());
    }
}
