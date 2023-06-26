package org.de.jdbc.transaction.rollback.savepoint;

import org.de.jdbc.key.Key;
import org.de.jdbc.mapper.ResultSetMapper;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);

        try {
            con.setAutoCommit(false); //이후부터 명시적인 commit이 필요하다
            PreparedStatement updateStmt = con.prepareStatement("UPDATE product SET price = price+10000 where id = ?");
            PreparedStatement selectStmt = con.prepareStatement("SELECT id, name, updated_at, contents, price from product where id =?");

            System.out.println("======before start update======");
            selectAndPrintRow(selectStmt, 2);
            selectAndPrintRow(selectStmt, 3);
            selectAndPrintRow(selectStmt, 4);

            // update
            updateStmt.setInt(1,2);
            updateStmt.executeUpdate();
            Savepoint sp1 = con.setSavepoint();
            updateStmt.setInt(1,3);
            updateStmt.executeUpdate();
            Savepoint sp2 = con.setSavepoint();
            updateStmt.setInt(1,4);
            updateStmt.executeUpdate();

            System.out.println("============after update===========");
            selectAndPrintRow(selectStmt, 2);
            System.out.println("save point 1 is here");
            selectAndPrintRow(selectStmt, 3);
            System.out.println("save point 2 is here");
            selectAndPrintRow(selectStmt, 4);

            con.rollback(sp2);
            System.out.println("============after savepoint2 rollback===========");
            selectAndPrintRow(selectStmt, 2);
            selectAndPrintRow(selectStmt, 3);
            selectAndPrintRow(selectStmt, 4);

            con.rollback(sp1);
            System.out.println("============after savepoint1 rollback===========");
            selectAndPrintRow(selectStmt, 2);
            selectAndPrintRow(selectStmt, 3);
            selectAndPrintRow(selectStmt, 4);

        } catch (SQLException sqlException) {
            System.out.println(sqlException.getErrorCode()+","+sqlException.getMessage());
        } finally {
            con.close();
        }
    }

    private static void selectAndPrintRow(PreparedStatement stmt, int id) throws SQLException {
        stmt.setInt(1,id);
        ResultSet rs = stmt.executeQuery();
        while(rs.next()) {
            ResultSetMapper.printRs(rs);
        }
    }
}
