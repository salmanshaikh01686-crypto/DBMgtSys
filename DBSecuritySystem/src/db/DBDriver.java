
package db;
import java.sql.*;
public class DBDriver 
{
  public Statement st=null;
  public Connection con=null;

    public Statement getStatement()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_security","root","root");
           st=con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("Exception at class DBDriver and function getStatement()"+e);
        }
        return st;
    }
}
