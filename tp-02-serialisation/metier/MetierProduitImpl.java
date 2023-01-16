package metier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MetierProduitImpl implements IMetier<Produit> {
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

    @Override
    public void saveAll() throws Exception {
        File file = new File("liste des produits.txt");
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(produits);
        oos.close();
        fos.close();
    }
}
