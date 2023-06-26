package org.de.jdbc.transaction.isolation;

import org.de.jdbc.key.Key;
import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);
        con.setAutoCommit(false); //이후부터 명시적인 commit이 필요하다

        Statement stmt = con.createStatement();
        stmt.executeUpdate("UPDATE product SET id = 109 where id = 9");
        ResultSet rs = stmt.executeQuery("SELECT id, name, updated_at, contents, price from product where id=109 ");
        while (rs.next()) {
            ResultSetMapper.printRs(rs);
        }

        con.close();

        Connection con2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);
        Statement stmt2 = con2.createStatement();
        ResultSet rs2 = stmt2.executeQuery("SELECT id, name, updated_at, contents, price from product where id=9");
        while (rs2.next()) {
            ResultSetMapper.printRs(rs2); //cond을 커밋하지 않았 기 때문에 id가 아직 109로 바뀌지않아서 값이 나온다
        }

    }
}
