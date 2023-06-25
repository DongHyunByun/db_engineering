package org.de.jdbc.crudexample;
import org.de.jdbc.key.Key;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        Key key = new Key();
        try {
            //here de-jdbc is database name, root is username and password is null. Fix them for your database settings.
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password); //DB와 클라이언트 사이의 물리적 연결
            DatabaseMetaData databaseMetaData = con.getMetaData(); //db의 여러가지 메타정보 제공하는 databasemetadata 객체제공
            Statement stmt = con.createStatement(); //statement 객체생성
            ResultSet rs = stmt.executeQuery("select * from product");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
                        + (rs).getDate(3) + " " + rs.getString(4)
                        + " " + rs.getInt(5));
            }
            Thread.sleep(5*60*1000);
            con.close();
        }    catch (Exception e) {System.out.println(e);}
    }
}
