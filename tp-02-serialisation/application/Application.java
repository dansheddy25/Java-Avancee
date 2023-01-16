package application;

import metier.MetierProduitImpl;
import metier.Produit;

import java.util.List;
import java.util.Scanner;

public class Application {
    public Application() {
    }

    public static void main(String[] args) throws Exception {
        Produit p1 = new Produit(1, "p1", "X", 2500, "produit1", 10);
        Produit p2 = new Produit(2, "p2", "X", 2500, "produit2", 5);
        //Produit p3 = new Produit(3, "p3", "X", 2500, "produit3", 10);
        MetierProduitImpl imp1 = new MetierProduitImpl();
        imp1.add(p1);
        imp1.add(p2);

        int choice = 0;
        Scanner sc=new Scanner(System.in);
        while (choice != 6) {
            System.out.println("1 . Afficher la liste des produits");
            System.out.println("2 . Rechercher un produit par son id");
            System.out.println("3 . Ajouter un nouveau produit dans la liste");
            System.out.println("4 . Supprimer un produit par son id");
            System.out.println("5 . Sauvegarder la liste des produits");
            System.out.println("6 . Quitter le programme");
            choice = sc.nextInt();
            if (choice == 1) {
                List<Produit> list = imp1.getAll();
                System.out.println("Id\tNom\tMarque\tPrix\tQuantite");
                for (Produit p : list) {
                    System.out.println(p.getId() + "\t" + p.getNom() + "\t" + p.getMarque() + "\t\t" + p.getPrix() + "\t" + p.getQuantite());
                };
            }

            if (choice == 2) {
                Scanner sc1 = new Scanner(System.in);
                System.out.print("Donner le id du produit à rechercher : ");
                long id = sc1.nextLong();
                System.out.println(imp1.findById(id).getId() + " " + imp1.findById(id).getNom());
            }

            if (choice == 3) {
                imp1.add(new Produit(3, "p3", "X", 2500, "produit3", 8));
            }

            if (choice == 4) {
                Scanner sc2 = new Scanner(System.in);
                System.out.print("Donner le id du produit à supprimer : ");
                long id = sc.nextLong();
                imp1.delete(id);
            }

            if (choice == 5) {
                imp1.saveAll();
                System.out.println("********** Sauvegarde réussie!!! **********");
            }

        }
    }
}
