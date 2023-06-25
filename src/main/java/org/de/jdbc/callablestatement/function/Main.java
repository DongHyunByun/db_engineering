package org.de.jdbc.callablestatement.function;

import org.de.jdbc.key.Key;
import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password); //DB와 클라이언트 사이의 물리적 연결
        CallableStatement stmtFunctionCall = con.prepareCall("{? = call add_event_prefix(?)}");
        String originalContent = "original";
        System.out.println("original content : "+originalContent);
        stmtFunctionCall.setString(2, originalContent);
        stmtFunctionCall.registerOutParameter(1, Types.VARCHAR);
        boolean result2 = stmtFunctionCall.execute();
        System.out.println(result2);
        System.out.println("AFTER Prefix : "+stmtFunctionCall.getString(1));

        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "SELECT id, add_event_prefix(name) as name,updated_at,contents,price from product where id=1");
        while (rs.next()) {
            ResultSetMapper.printRs(rs);
        }


    }
}
