package ca.qc.cgmatane.pictrade.vue;

import java.util.HashMap;

import ca.qc.cgmatane.pictrade.modele.Commerce;

public interface VueModifierCommerce {
    public HashMap<String, String> getCommerceHashMap();
    public void preRemplirChamps();
    public void naviguerCommerce();
}
