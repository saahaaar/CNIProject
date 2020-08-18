package cni.tn.service;

import java.io.UnsupportedEncodingException;
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
			Connection con1 = DriverManager.getConnection("jdbc:postgresql://localhost:5434/type_frais", "postgres",
					"postgres");

			// step3 create the statement object
			Statement stmt = con.createStatement();
			Statement stmt1 = con1.createStatement();

			// step4 execute query
			ResultSet rs = stmt.executeQuery("select * from TYP_FRAIS");
			PreparedStatement pstmt = con1
					.prepareStatement("INSERT INTO type_frais (code,lib_lang1,lib_lang2) VALUES (?, ?, ?)");

			while (rs.next()) {
				pstmt.setString(1, conv(rs.getString(1)));
				pstmt.setString(2, conv(rs.getString(2)));
				pstmt.setString(3, conv(rs.getString(3)));
				pstmt.executeUpdate();
				System.out
						.println(rs.getString(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			}

			// step5 close the connection object
			con.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	static String conv(String s) throws UnsupportedEncodingException {
		String data = "";
		try {
			byte[] sourceBytes = s.getBytes("iso-8859-1");
			data = new String(sourceBytes, "windows-1256");
			System.out.println(data);

		} catch (UnsupportedOperationException e) {

		}
		return data;
	}
}
