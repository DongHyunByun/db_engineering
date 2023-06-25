package org.de.jdbc.resultset.methods;

import org.de.jdbc.key.Key;

import javax.xml.transform.Result;
import java.sql.*;

//cursur is start from before first value
public class main {
    public static void main(String[] args) throws SQLException {
        Key key = new Key();
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/de-jdbc", "root", key.password);
        Statement stmt  = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

        ResultSet rs = stmt.executeQuery(
                "select id, name, updated_at, contents, price from product where id between 1 and 5");

        if (rs.next()) {
            // cursor 1
            printRs(rs);
        }
        if (rs.last()) {
            // cursor 5
            printRs(rs);
        }
        if (!rs.next()) {
            // if reach end ?
            try {
                printRs(rs);
            } catch(SQLException sqlException) {
                System.out.println(sqlException.getErrorCode()+", "+sqlException.getMessage());
            }
        }
        rs.last();
        if (rs.previous()) {
            printRs(rs);
        }
        if (rs.absolute(2)) {
            // aboslute path
            // cursur : 2
            printRs(rs);
        }
        if (rs.relative(4)) {
            // cursur : 4
            printRs(rs);
        }
        if (rs.relative(-2)) {
            // cursur : 2
            printRs(rs);
        }
        if (rs.first()) {
            // cursur : 1
            printRs(rs);
        }
        if (!rs.previous()) {
            // if before first?
            printRs(rs);
        }
    }

    private static void printRs(ResultSet rs ) throws SQLException {
        System.out.println(rs.getInt(1) + " " + rs.getString(2) + " "
                + (rs).getDate(3) + " " + rs.getString(4)
                + " " + rs.getInt(5));
    }
}
