package org.de.jdbc.statement.execute;

import org.de.jdbc.key.Key;

import java.sql.*;

// excute : 여러개의 결과를 얻는 sql
// 리턴값 : 결과가 있으면 True(getResultSet을 이용해서 출력값을 얻는다), 없거나 update인 경우 False리턴(getUpdateCount를 통해서 수행된 갯수를 얻는다)
public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);
        Statement stmt  = con.createStatement();

        boolean selectResult = stmt.execute("select id,name,updated_at,contents,price from product where id between 1 and 5"); //True리턴
        if (selectResult) {
            ResultSet resultSet = stmt.getResultSet();
            while(resultSet.next()) {
                printRs(resultSet);
            }
        }

        boolean updateResult = stmt.execute("UPDATE product SET price = price+1000 where id = 1"); //결과를 받아오지않는 Update문이므로 False
        if (!updateResult) {
            System.out.println("result of updateResult : "+stmt.getUpdateCount());
        }

        con.close();
    }

    private static void printRs(ResultSet rs) throws SQLException {
        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
                + (rs).getDate(3) + " " + rs.getString(4)
                + " " + rs.getInt(5));
    }
}
