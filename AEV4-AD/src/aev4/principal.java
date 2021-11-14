package aev4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class principal {
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/aev4", "root", "");
			Statement stmt = con.createStatement();

			int registres;

			ResultSet registrats = stmt.executeQuery("SELECT COUNT(id) FROM llibre");

			while (registrats.next()) {
				registres = registrats.getInt(1);
				if (registres < 1) {
					File file = new File("AE04_T1_4_JDBC_Dades.csv");
					FileReader fr = new FileReader(file);
					BufferedReader br = new BufferedReader(fr);
					String linea = br.readLine();
					
					
					while((linea=br.readLine()) !=null) {
						String[] llibre = linea.split(";");
						PreparedStatement psInsertar = con.prepareStatement("INSERT INTO llibre (titol,autor,any_Naixement,any_Publicacio,"
										+ "editorial,nombrePagines) VALUES (?,?,?,?,?,?)");
						for (int i = 0; i < llibre.length; i++) {
							psInsertar.setString(i + 1, llibre[i]);
						}
						
					}
					br.close();
				}

			}
			
			System.out.println("Llibres d'autors nascuts abans de 1950");
			ResultSet rs = stmt.executeQuery("SELECT titol,autor,any_Publicacio FROM llibre WHERE any_Naixement < 1950");
			while(rs.next()) {
					System.out.println(rs.getString(1)+" ,"+rs.getString(2)+" ,"+rs.getString(3));
			}
			System.out.println("\nEditorials que han publicat en el segle XXI");
			ResultSet rset = stmt.executeQuery("SELECT editorial FROM llibre WHERE any_Publicacio > 2000");
			while(rset.next()) {
				System.out.println(rset.getString(1));
			}
			
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
