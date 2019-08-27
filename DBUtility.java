package employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtility {

	public static ResultSet executeQuery(Connection con,String query){

		ResultSet rs = null;
		try {
			Statement stmt = con.createStatement();

			rs = stmt.executeQuery(query);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rs;	
	}

	public static void printEntireRS(ResultSet rs){
		String strB;
		ResultSetMetaData rsmd;
		try {
			rsmd = rs.getMetaData();
			for(int i=1;i<=rsmd.getColumnCount();i++){
				System.out.print(rsmd.getColumnName(i)+" ");
			}
			System.out.println();
			while(rs.next()){
				strB = "";
				for(int i=1;i<=rsmd.getColumnCount();i++){
					strB=strB+" "+(rs.getString(i));
				}
				System.out.println(strB);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public static void useDB(Connection con, String database) {
		try {
			con.setCatalog(database);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean executeUpdate(Connection con, String query) {
		Statement state;
		try {
			state = con.createStatement();
			System.out.println("test");
			System.out.println(state.executeUpdate(query) + "Rows affected: ");
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static String printEntireRS1(ResultSet rs) {
		String str = " ";
		ResultSetMetaData rsmd;
		
		try {
			rsmd = rs.getMetaData();
			while(rs.next()) {
				str = str + "<br/>: ";
				for(int i = 1; i <=rsmd.getColumnCount(); i++) {
					str = str + (rs.getString(i) + " : ");
				}
			}
			return str;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Nothing is here";
	}
}


