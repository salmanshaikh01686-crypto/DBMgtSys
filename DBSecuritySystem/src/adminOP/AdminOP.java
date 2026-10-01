
package adminOP;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AdminOP 
{
    
    public boolean isAdminExisted(String user_name,String pass_word)
    {
        boolean flag=false;
try
{
   DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        String query="Select * from admin_info where username='"+user_name+"' and password='"+pass_word+"'";
           System.out.println("Query is "+query);
           ResultSet rs=st.executeQuery(query);
           if(rs.next())
               flag=true;
           st.close();
           dbd.st.close();
           dbd.con.close(); 
}
catch(Exception ex)
{
    System.out.println("Exception in class AdminOP with method isAdminExisted() "+ex);
    flag=false;
}
return flag;
    }
    public ArrayList getAdminCredential()
    {
        ArrayList<String> admindata=new ArrayList<String>();
        try
        {
           String adminname="admin";
            DBDriver dbd=new DBDriver();
           Statement st=dbd.getStatement(); 
           String query="select * from admin_info where username= '"+adminname+"'";
        System.out.println("Query is "+query);
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
           
       admindata.add( rs.getString(1));
       admindata.add( rs.getString(3));
       admindata.add( rs.getString(2));
       
        }
        st.close();
        dbd.st.close();
        dbd.con.close();
        
        }
        catch(Exception ex)
        {
            System.out.println("Exception in class AdminOP in method getAdminCredential() "+ex);
        }
        return admindata;
        
    }
    public boolean isAdminEdited(String username,String emailid,String password)
    {
        boolean flag=false;
        try
        {
            DBDriver dbd=new DBDriver();
          Statement st=dbd.getStatement();
          String query="Update admin_info set emailid='"+emailid+"',password='"+password+"' where username='"+username+"'";
          
          int x=st.executeUpdate(query);
          if(x>0)
              flag=true;
           st.close();
        dbd.st.close();
        dbd.con.close();
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception in class AdminOP in method isAdminEdited() "+ex);
        }
        return flag;
    }
    
}
