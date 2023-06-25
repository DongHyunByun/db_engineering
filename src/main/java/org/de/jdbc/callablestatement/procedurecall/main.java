package org.de.jdbc.callablestatement.procedurecall;

import org.de.jdbc.key.Key;

import java.sql.*;

public class main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password); //DB와 클라이언트 사이의 물리적 연결

        CallableStatement stmtProcedureCall = con.prepareCall("call discount price(?,?,?)");
        stmtProcedureCall.setInt(1,1);
        stmtProcedureCall.setInt(2,20);
        stmtProcedureCall.registerOutParameter(3, Types.INTEGER);
        boolean result = stmtProcedureCall.execute();
        System.out.println("result: "+result);
        System.out.println("param: " + stmtProcedureCall.getInt(3));

        if(!result) {
            System.out.println("updated count : " +stmtProcedureCall.getUpdateCount());
        }

    }
}
