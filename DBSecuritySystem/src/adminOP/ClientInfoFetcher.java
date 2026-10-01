
package adminOP;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ClientInfoFetcher 
{
    public ArrayList getClientInfo()
    {
        ArrayList clientdata=new ArrayList();
        try
        {
            DBDriver dbd=new DBDriver();
             Statement st=dbd.getStatement();
             String query="Select * from client_data_info ";
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
             {
                 String clientname=rs.getString(1);
                 String tablename=rs.getString(2);
                 ArrayList row=new ArrayList();
                 row.add(clientname);
                 row.add(tablename);
                 clientdata.add(row);
             }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class ClientInfoFetcher in method getClientInfo() "+ex);
            
        }
        return clientdata;
    }
    
}
