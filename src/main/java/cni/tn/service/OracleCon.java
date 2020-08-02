package cni.tn.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

class OracleCon {
	public static void main(String args[]) {
		try {
			// step1 load the driver class
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName("org.postgresql.Driver");

			// step2 create the connection object
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:oracle", "system", "oracle");
			Connection con1 = DriverManager.getConnection("jdbc:postgresql://localhost:5434/cnii", "postgres",
					"postgres");

			// step3 create the statement object
			Statement stmt = con.createStatement();
			Statement stmt1 = con1.createStatement();

			// step4 execute query

			ResultSet rs = stmt.executeQuery("select * from cnii");
			PreparedStatement pstmt = con1
					.prepareStatement("INSERT INTO cnii (id,nom,prenom,daate) VALUES (?, ?, ?, ?)");

			while (rs.next()) {
				pstmt.setInt(1, rs.getInt(1));
				pstmt.setString(2, rs.getString(2));
				pstmt.setString(3, rs.getString(3));
				pstmt.setDate(4, rs.getDate(4));
				pstmt.executeUpdate();
				System.out
						.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3) + "  " + rs.getDate(4));
			}

			// step5 close the connection object
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}
