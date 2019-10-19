package ca.qc.cgmatane.pictrade.modele;

import java.util.ArrayList;
import java.util.Objects;

public class Favori {

    private int id_favori;
    private int id_commerce;
    private boolean favori;

    public Favori(int id_favori, int id_commerce, boolean favori) {
        this.id_favori = id_favori;
        this.id_commerce = id_commerce;
        this.favori = favori;
    }

    public Favori(int id_commerce) {
        this.id_commerce = id_commerce;
    }

    public Favori(int id_commerce, boolean favori) {
        this.id_commerce = id_commerce;
        this.favori = favori;
    }

    public int getId_favori() {
        return id_favori;
    }

    public void setId_favori(int id_favori) {
        this.id_favori = id_favori;
    }

    public int getId_commerce() {
        return id_commerce;
    }

    public void setId_commerce(int id_commerce) {
        this.id_commerce = id_commerce;
    }

    public boolean isFavori() {
        return favori;
    }

    public void setFavori(boolean favori) {
        this.favori = favori;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favori favori = (Favori) o;
        return id_commerce == favori.id_commerce;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_favori);
    }
}
