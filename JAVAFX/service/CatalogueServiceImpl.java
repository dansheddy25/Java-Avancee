package service;

import dao.CategoryDao;
import dao.ProductDao;
import dao.entities.Category;
import dao.entities.Product;

import java.util.List;

public class CatalogueServiceImpl implements CatalogueService{
    private ProductDao productDao;
    private CategoryDao categoryDao;

    public CatalogueServiceImpl(ProductDao productDao, CategoryDao categoryDao) {
        this.productDao = productDao;
        this.categoryDao = categoryDao;
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    @Override
    public List<Product> searchProductByQuery(String query) {
        return productDao.findByQuery(query);
    }

    @Override
    public void addProduct(Product p) {
        productDao.save(p);
    }

    @Override
    public void updateProduct(Product p) {
        productDao.update(p);
    }

    @Override
    public void deleteProduct(Product p) {
        productDao.delete(p);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryDao.findAll();
    }

    @Override
    public void addCategory(Category c) {
        categoryDao.save(c);
    }

    @Override
    public void deleteCategory(Category c) {
        categoryDao.delete(c);
    }

    @Override
    public void updateCategory(Category c) {
        categoryDao.update(c);
    }
}
