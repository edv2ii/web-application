package controller;

import config.*;
import java.sql.*;
import model.Fiction;

import java.util.*;

public class FictionController {

    public boolean insertFiction(Fiction fiction) {
        boolean result = false;
        Connection connection = null;
        try {
            // ใช้ instance เดียวของ DatabaseConnection ในการเชื่อมต่อฐานข้อมูล
            connection = DatabaseConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();

            String nutrient = "";
            int fl = fiction.getFictionType().length;

            for (int i = 0; i < fl - 1; i++) {
                nutrient = nutrient + fiction.getFictionType()[i] + ";";
            }
            nutrient = nutrient + fiction.getFictionType()[fl - 1];

            String query = "INSERT INTO fiction"
                    + "(name, fictionLanguage, fictionType, story, price, id) "
                    + "VALUES('"
                    + fiction.getFictionName() + "','"
                    + fiction.getFictionLanguage() + "','"
                    + nutrient + "','"
                    + fiction.getStory() + "','"
                    + fiction.getFictionPrice() + "','"
                    + fiction.getId() + "')";
            System.out.println("........SQL: " + query);

            int i = statement.executeUpdate(query);
            if (i != 0) {
                result = true;
            }
            statement.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return result;
    }

    public Fiction getFictionById(String id) {
        Fiction fiction = null;
        Connection connection = null;
        try {
            // ใช้ instance เดียวของ DatabaseConnection ในการเชื่อมต่อฐานข้อมูล
            connection = DatabaseConnection.getInstance().getConnection();

            String query = "SELECT * FROM fiction WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                fiction = new Fiction();
                fiction.setId(resultSet.getString("id"));
                fiction.setFictionName(resultSet.getString("name"));
                fiction.setFictionLanguage(resultSet.getString("fictionLanguage"));
                fiction.setFictionType(resultSet.getString("fictionType").split(";"));
                fiction.setStory(resultSet.getString("story"));
                fiction.setFictionPrice(resultSet.getDouble("price"));
                
            }

            resultSet.close();
            preparedStatement.close();
            
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return fiction;
    }
    
public boolean updateFiction(Fiction fiction) {
        boolean result = false;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();

            if (isFictionExists(fiction.getId())) {
                String nutrient = String.join(";", fiction.getFictionType());

                String query = "UPDATE fiction SET "
                        + "name = ?, fictionLanguage = ?, fictionType = ?, "
                        + "story = ?, price = ? "
                        + "WHERE id = ?";

                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, fiction.getFictionName());
                preparedStatement.setString(2, fiction.getFictionLanguage());
                preparedStatement.setString(3, nutrient);
                preparedStatement.setString(4, fiction.getStory());
                preparedStatement.setDouble(5, fiction.getFictionPrice());
                preparedStatement.setString(6, fiction.getId());

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    result = true;
                }

                preparedStatement.close();
            } else {
                System.out.println("Fiction with id " + fiction.getId() + " does not exist.");
            }
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return result;
    }

    // Method to check if a fiction record exists by id
    public boolean isFictionExists(String id) {
        boolean exists = false;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();

            String query = "SELECT id FROM fiction WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }

            resultSet.close();
            preparedStatement.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
        return exists;
    }

    public void testRetrieve() {
        Connection connection = null;
        try {
            // ใช้ instance เดียวของ DatabaseConnection ในการเชื่อมต่อฐานข้อมูล
            connection = DatabaseConnection.getInstance().getConnection();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(
                    "select name from fiction");
            String name;
            while (resultSet.next()) {
                name = resultSet.getString("name").trim();
                System.out.println(" name : " + name);
            }
            resultSet.close();
            statement.close();
        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
    public List<Fiction> selectAllFictions() {
        List<Fiction> fictions = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DatabaseConnection.getInstance().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(
                    "select * from fiction"); 

            while (rs.next()) {
                // Get data from ResultSet
                String id = rs.getString("id");
                String fictionName = rs.getString("name");
                String fictionLanguage = rs.getString("fictionLanguage");
                String fictionType = rs.getString("fictionType");
                String story = rs.getString("story");
                double fictionPrice = rs.getDouble("price");

                // Create a Fiction object and set its properties
                Fiction fiction = new Fiction();
                fiction.setId(id);
                fiction.setFictionName(fictionName);
                fiction.setFictionLanguage(fictionLanguage);
                fiction.setFictionType(fictionType.split(";")); // Split fictionType string into array
                fiction.setStory(story);
                fiction.setFictionPrice(fictionPrice);

                // Add the Fiction object to the list
                fictions.add(fiction);
            }
            
            // Close ResultSet and PreparedStatement
            rs.close();
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fictions;
    }
}
