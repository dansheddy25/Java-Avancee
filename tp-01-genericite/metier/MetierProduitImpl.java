package metier;

import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements IMetier<Produit>{
    List<Produit> produits;

    public MetierProduitImpl() {
        this.produits = new ArrayList<>();
    }
    @Override
    public void add(Produit o) {
        produits.add(o);
    }

    @Override
    public List<Produit> getAll() {
        return produits;
    }

    @Override
    public Produit findById(long id) {
        Produit produit = new Produit();
        for (Produit p : produits) {
            if (p.getId() == id) produit = p;
        }
        return produit;
    }

    @Override
    public void delete(long id) {
        produits.remove(findById(id));
    }
}