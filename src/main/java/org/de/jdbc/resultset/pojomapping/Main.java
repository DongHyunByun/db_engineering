package org.de.jdbc.resultset.pojomapping;

import org.de.jdbc.key.Key;
import org.de.jdbc.mapper.Product;
import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// DB에 있는 데이터를 객체에 저장하는 코드
public class Main {
    public static void main(String[] args) {
        Key key = new Key();
        try {
            //here de-jdbc is database name, root is username and password is null. Fix them for your database settings.
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password); //DB와 클라이언트 사이의 물리적 연결
            DatabaseMetaData databaseMetaData = con.getMetaData(); //db의 여러가지 메타정보 제공하는 databasemetadata 객체제공

            Statement stmt = con.createStatement(); //statement 객체생성
            ResultSet rs = stmt.executeQuery("select * from product");
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                //                System.out.println(ResultSetMapper.create(rs));
                Product product = ResultSetMapper.create(rs);
                products.add(product);
            }

            products.stream().forEach(it -> it.setPrice(it.getPrice() - 1000));
            con.close();
        }  catch (Exception e) {System.out.println(e);}
    }
}
