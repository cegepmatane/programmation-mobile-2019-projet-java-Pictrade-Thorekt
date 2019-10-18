package ca.qc.cgmatane.pictrade.vue;

import android.os.Bundle;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public interface VueAfficherCommerce {
    public Bundle getParametres();
    public void commerceEnAttente();
    public void afficherCommerce();
    public void setCommerce(Commerce commerce);
    public void setFavori(boolean isFavori);
    public void naviguerModifierCommerce(Commerce commerce);
    public void naviguerPartagerCommerce();

    public void toast(boolean f);
}
