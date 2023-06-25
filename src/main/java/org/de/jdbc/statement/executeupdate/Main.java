package org.de.jdbc.statement.executeupdate;
import org.de.jdbc.key.Key;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// excuteUpdate : insert, update delete 등 결과를 받아오지 않는 명령을 실행하는 함수. return값이 변경사항에 적용된 row의 수
public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);
        Statement stmt  = con.createStatement();

        int result = stmt.executeUpdate("UPDATE product SET price = price+1000 where id = 1");
        System.out.println("result of update " + result);

        int result2 = stmt.executeUpdate("UPDATE product SET price=price-1000 where name like 'shoes%'");
        System.out.println("result of update " + result2);

        con.close();
    }
}
