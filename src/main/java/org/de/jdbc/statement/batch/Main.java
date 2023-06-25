package org.de.jdbc.statement.batch;

import org.de.jdbc.key.Key;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// batch : 명령문들을 쌓아서 저장해 놓고(addBatch) 한번에 실행(executeBatch),select를 제외한 DML을 batch로 쓸 수 있다.
// 리턴값 : batch 순서대로 결과값이 리턴
public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);
        Statement stmt  = con.createStatement();
//        stmt.addBatch("select id,name,updated_at,contents,price from product where id between 1 and 5"); //select는 사용할 수 없다

        stmt.addBatch("UPDATE product SET price=price-1000 where id = 1");
        stmt.addBatch("UPDATE product SET price=price-1000 where id = 2");
        stmt.addBatch("UPDATE product SET price=price-1000 where id between 3 and 5");

        int[] results = stmt.executeBatch();
        for (int result : results) {
            if (result>=0) {
                System.out.println("result of update "+ result);
            }
        }

        con.close();
    }
}
