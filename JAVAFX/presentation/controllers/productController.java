package presentation.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import dao.CategoryDaoImpl;
import dao.ProductDaoImpl;
import dao.entities.Category;
import dao.entities.Product;
import service.CatalogueService;
import service.CatalogueServiceImpl;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class productController implements Initializable {
    @FXML private TextField textNom;
    @FXML private TextField textRef;
    @FXML private TextField textPrix;
    @FXML private TextField textSearch;
    @FXML private ComboBox<Category> comboCategory;
    @FXML private TableView<Product> tableViewProduct;
    @FXML private TableColumn<Long, Product> columnId;
    @FXML private TableColumn<String, Product> columnNom;
    @FXML private TableColumn<String, Product> columnRef;
    @FXML private TableColumn<Float, Product> columnPrix;
    @FXML private TableColumn<Category, Product> columnCategorie;
    private CatalogueService catalogueService;
    ObservableList<Product> data = FXCollections.observableArrayList();
    private Product selectedProduct;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableViewProduct.setItems(data);
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnNom.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnRef.setCellValueFactory(new PropertyValueFactory<>("reference"));
        columnPrix.setCellValueFactory(new PropertyValueFactory<>("price"));
        columnCategorie.setCellValueFactory(new PropertyValueFactory<>("category"));
        catalogueService = new CatalogueServiceImpl(new ProductDaoImpl(), new CategoryDaoImpl());
        comboCategory.getItems().addAll(catalogueService.getAllCategories());
        loadData();
        textSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            data.clear();
            data.addAll(catalogueService.searchProductByQuery(newValue));
        });
    }

    private void loadData(){
        data.clear();
        data.addAll(catalogueService.getAllProducts());
    }

    public void addProduct(){
        Product product = new Product();
        product.setName(textNom.getText());
        product.setReference(textRef.getText());
        product.setPrice(Float.parseFloat(textPrix.getText()));
        product.setCategory(comboCategory.getSelectionModel().getSelectedItem());
        catalogueService.addProduct(product);
        loadData();
    }

    public void deleteProduct(){
        Product product = tableViewProduct.getSelectionModel().getSelectedItem();
        CatalogueService.deleteProduct(product);
        loadData();
    }

    public void updateProduct(){
        selectedProduct.setName(textNom.getText());
        selectedProduct.setReference(textRef.getText());
        selectedProduct.setPrice(Float.parseFloat(textPrix.getText()));
        selectedProduct.setCategory(comboCategory.getSelectionModel().getSelectedItem());
        catalogueService.updateProduct(selectedProduct);
        loadData();
    }
    public void editProduct(){
        selectedProduct = tableViewProduct.getSelectionModel().getSelectedItem();
        textNom.setText(selectedProduct.getName());
        textRef.setText(selectedProduct.getReference());
        textPrix.setText(String.valueOf(selectedProduct.getPrice()));
    }
}
