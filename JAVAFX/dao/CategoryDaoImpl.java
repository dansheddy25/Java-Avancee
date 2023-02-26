package dao;

import dao.entities.Category;
import dao.entities.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao{
    @Override
    public Category find(long id) {
        Connection connection = ConnexionDBSingleton.getConnection();
        Category category = new Category();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                category.setId(resultSet.getLong("ID"));
                category.setName(resultSet.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public List<Category> findAll() {
        //mapping objet relationnel
        List<Category> categories = new ArrayList<>();
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from category");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Category c = new Category();
                c.setId(resultSet.getLong("ID"));
                c.setName(resultSet.getString("NAME"));
                categories.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }

    @Override
    public void save(Category o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into category(NAME) values (?)");
            preparedStatement.setString(1, o.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("delete from category where id = ?");
            preparedStatement.setLong(1, o.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Category o) {
        Connection connection = ConnexionDBSingleton.getConnection();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement("update from category set name = ? where id = ?");
            preparedStatement.setString(1, o.getName());
            preparedStatement.setLong(2, o.getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}