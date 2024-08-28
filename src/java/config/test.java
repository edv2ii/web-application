//package config;
//
//import java.sql.*;  // Using 'Connection', 'Statement' and 'ResultSet' classes in java.sql package
//import model.Fiction;
//
//public class connectDB {
//
//    public boolean insertNewFiction(Fiction fiction) {
//        boolean result = false;
//        Connection connection = null;
//        try {
//            // below two lines are used for connectivity.
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/fiction",
//                    "root", "");
//
//            Statement statement;
//            statement = connection.createStatement();
//
//            // สร้าง String nutrient ที่มีค่าสารอาหารต่าง ๆ คั่นด้วย ;
//            String nutrient = "";
//            int fl = fiction.getFictionType().length;
//            
//            // วนลูปสร้างแค่ fiction.getFictionType().length ลบ 1 เพื่อไม่ให้มี ; ปิดท้าย String
//            for(int i=0; i < fl - 1; i++){
//                nutrient = nutrient + fiction.getFictionType()[i] + ";";
//            }
//            // เพิ่มค่า nutrient ตำแหน่งสุดท้ายเข้าไป
//            nutrient = nutrient + fiction.getFictionType()[fl-1];
//            
//            System.out.println(fiction.getId());
//            
//            String query = "INSERT INTO fiction"
//                    + "( name, fictionLanguage,fictionType , story, price, id) "
//                    + "VALUES('"
//                    
//                    + fiction.getFictionName() + "' ,'"
//                    + fiction.getFictionLanguage() + "','"
//                    + nutrient + "','"
//                    + fiction.getStory() + "','"
//                    + fiction.getFictionPrice() + "'," 
//                    + fiction.getId() + "')" ;
//            System.out.println("........SQL: " + query);
//
//            int i = statement.executeUpdate(query);	// executeUpdate returns row count ****
//            if (i != 0) {
//                result = true;
//            }
//            statement.close();
//            connection.close();
//        } catch (Exception exception) {
//            System.out.println(exception);
//        }
//        return result;
//    }
//
//    /*
//    https://www.geeksforgeeks.org/java-database-connectivity-with-mysql/
//     */
//    public void testRetrieve() {
//        Connection connection = null;
//        try {
//            // below two lines are used for connectivity.
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(
//                    "jdbc:mysql://localhost:3306/fiction",
//                    "root", "");
//
//            Statement statement;
//            statement = connection.createStatement();
//            ResultSet resultSet;
//            resultSet = statement.executeQuery(
//                    "select name from fiction");
//            String name;
//            while (resultSet.next()) {
//                name = resultSet.getString("name").trim();
//                System.out.println(" name : " + name);
//            }
//            resultSet.close();
//            statement.close();
//            connection.close();
//        } catch (Exception exception) {
//            System.out.println(exception);
//        }
//    }
//}
