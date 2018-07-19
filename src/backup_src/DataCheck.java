package backup_src;

import java.sql.*;

public class DataCheck 
{
	public static void main(String[] args) 
	{
		 
        Connection conn = null;
 
        try {
 
        	String dbURL = "jdbc:sqlserver://localhost";
            String user = "furkan";
            String pass = "123456";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) 
            {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }
 
        } 
        catch (SQLException ex) 
        {
            ex.printStackTrace();
        } 
        finally 
        {
            try 
            {
                if (conn != null && !conn.isClosed())
                {
                    conn.close();
                }
            } catch (SQLException ex) 
            {
                ex.printStackTrace();
            }
        }
    }
	
}
