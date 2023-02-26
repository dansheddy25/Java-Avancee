package dao;

import dao.entities.Category;
import dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao{
    @Override
    public Product find(long id) {
        Connection connection = ConnexionDBSingleton.getConnection();
        Product product = new Product();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product.setId(resultSet.getLong("ID"));
                product.setName(resultSet.getString("NAME"));
                product.setReference(resultSet.getString("REFERENCE"));
                product.setPrice(resultSet.getFloat("PRICE"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public List<Product> findAll() {
        //mapping objet relationnel
        List<Product> products = new ArrayList<>();
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product p = new Product();
                p.setId(resultSet.getLong("ID"));
                p.setName(resultSet.getString("NAME"));
                p.setReference(resultSet.getString("REFERENCE"));
                p.setPrice(resultSet.getFloat("PRICE"));

                PreparedStatement preparedStatement1 = connection.prepareStatement("select * from category where id = ?");
                preparedStatement1.setLong(1, resultSet.getLong("id_cat"));
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                Category category = new Category();
                if (resultSet1.next()){
                    category.setName(resultSet1.getString("name"));
                }
                p.setCategory(category);
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void save(Product o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into products(NAME, REFERENCE, PRICE, ID_CAT) values (?,?,?,?)");
            preparedStatement.setString(1, o.getName());
            preparedStatement.setString(2, o.getReference());
            preparedStatement.setFloat(3, o.getPrice());
            preparedStatement.setLong(4, o.getCategory().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(Product o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("delete from products where id = ?");
            preparedStatement.setLong(1, o.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Product o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update products set name = ?, reference = ?, price = ?, id_cat = ? where id = ?");
            preparedStatement.setString(1, o.getName());
            preparedStatement.setString(2, o.getReference());
            preparedStatement.setFloat(3, o.getPrice());
            preparedStatement.setLong(4, o.getCategory().getId());
            preparedStatement.setLong(5, o.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findByQuery(String query) {
        //mapping objet relationnel
        List<Product> products = new ArrayList<>();
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where name like ? or reference like ? or price like ?");
            preparedStatement.setString(1, "%" + query + "%");
            preparedStatement.setString(2, "%" + query + "%");
            preparedStatement.setString(3, "%" + query + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Product p = new Product();
                p.setId(resultSet.getLong("ID"));
                p.setName(resultSet.getString("NAME"));
                p.setReference(resultSet.getString("REFERENCE"));
                p.setPrice(resultSet.getFloat("PRICE"));
                products.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}