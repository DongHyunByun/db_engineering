package org.de.jdbc.preparedstatment;

import org.de.jdbc.key.Key;
import org.de.jdbc.mapper.Product;
import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// make query with empty params(using questionmark) and put variables using setInt func
public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password); //DB와 클라이언트 사이의 물리적 연결

        System.out.println("---------------update--------------");
        PreparedStatement psUpdate = con.prepareStatement("UPDATE product SET price = price + ? where id = ?");
        psUpdate.setInt(1,10000);
        psUpdate.setInt(2,1);
        int updateResult = psUpdate.executeUpdate();
        System.out.println("result of update: "+updateResult);

        System.out.println("---------------psSelect 1--------------");
        PreparedStatement psSelect = con.prepareStatement("select id, name, updated_at, contents, price from product where id between ? and ?");
        psSelect.setInt(1, 1);
        psSelect.setInt(2, 5);
        ResultSet rs = psSelect.executeQuery();
        while(rs.next()) {
            ResultSetMapper.printRs(rs);
        }

        System.out.println("---------------psSelect 2--------------");
        psSelect.setInt(1,6);
        psSelect.setInt(2,10);
        ResultSet rs2 = psSelect.executeQuery();
        List<Product> productList = new ArrayList<>();
        while(rs2.next()) {
            productList.add(ResultSetMapper.create(rs2));
        }

        PreparedStatement psUpdateProduct = con.prepareStatement("UPDATE product SET id = ?, name=?, updated_at=?, contents=?,price=? where id=?");
        for(Product product : productList) {
            product.setPrice(product.getPrice()-1000);
            product.setUpdatedAt(LocalDateTime.now());

            psUpdateProduct.setInt(1, product.getId());
            psUpdateProduct.setString(2, product.getName());
            psUpdateProduct.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            psUpdateProduct.setString(4, product.getContents());
            psUpdateProduct.setInt(5, product.getPrice());
            psUpdateProduct.setInt(6, product.getId());

            int result = psUpdateProduct.executeUpdate();
            System.out.println("result of query : "+result);
        }
    }
}
