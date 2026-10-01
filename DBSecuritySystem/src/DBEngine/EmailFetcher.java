
package DBEngine;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class EmailFetcher 
{
    public ArrayList<String> getEmailAddress(String clientname)
    {
        ArrayList<String> emailids=new ArrayList<String>();
        
        try
        {
             NewDBDriver dbd=new NewDBDriver();
         Statement st=dbd.getStatement();
         String query1="Select * from admin_info";
         ResultSet rs1=st.executeQuery(query1);
         if(rs1.next())
         {
         String adminemail=rs1.getString(3);
         emailids.add(adminemail);
         }

         
         String query2="Select * from client_info where username='"+clientname+"'";
          ResultSet rs2=st.executeQuery(query2);
          if(rs2.next())
          {
           String clientemail=rs2.getString("emailid");        
           emailids.add(clientemail);
          }
        
        }
        catch(Exception e)
        {
            System.out.println("Exception at class EmailFetcher in method getEmailAddress() "+e);
        }
        
        return emailids;
    }
    
}
