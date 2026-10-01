
package clientOP;
import db.DBDriver;
import java.sql.*;
import java.util.ArrayList;

public class ClientDBOP 
{
   public boolean isClientregistreted(String name,String dob,String mobile,String email,String username,String pwd,String date_time)
   {
    boolean flag=false;
    //name, dob, mobileno, email, username, pwd, currentTD
    try
    {
        DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        String query="Insert into client_info values('"+username+"','"+name+"','"+dob+"','"+mobile+"','"+email+"','"+pwd+"','"+date_time+"')";
        System.out.println("Query : "+query);
        if(st.executeUpdate(query)>0)
        {
            flag=true;
        }
        st.close();
        dbd.st.close();
        dbd.con.close();

    }
    catch(Exception e)
            {
        
        System.out.println("Exception at ClientDBOP with method isClientregistreted()"+e);
        flag=false;
    }
    return flag;
   }
   public boolean isExisted(String username,String password)
   {
       boolean flag=false;
       try
       {
           DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        String query="Select * from client_info where username='"+username+"' and password='"+password+"'";
           System.out.println("Query is "+query);
           ResultSet rs=st.executeQuery(query);
           if(rs.next())
               flag=true;
           st.close();
           dbd.st.close();
           dbd.con.close();
       }
       catch(Exception e)
       {
           System.out.println("Exception at class ClientDBOP in method isExisted() "+e);
           flag=false;
       }
       return flag;
       
   }
   public ArrayList getClientData(String clientname)
   {
       ArrayList<String> data=new ArrayList<String>();
       try
   {
     DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        String query="select * from client_info where username= '"+clientname+"'";
        System.out.println("Query is ");
        ResultSet rs=st.executeQuery(query);
        while(rs.next())
        {
           
       data.add( rs.getString(1));
       data.add( rs.getString(2));
       data.add( rs.getString(3));
       data.add( rs.getString(4));
       data.add( rs.getString(5));
       data.add( rs.getString(6));
        }
        st.close();
        dbd.st.close();
        dbd.con.close();
        
        
   }
        catch(Exception e)
                {
                    System.out.println("Exception at class ClientDBOP in method getClientData() "+e);
                }
       return data;
   }
   public boolean isEdited(String fname,String fdob,String fmobile,String femail,String fusername,String fpwd)
   {
       boolean flag=false;
       //username, name, dob, mobile, emailid, password, date_time
       try
       {
          DBDriver dbd=new DBDriver();
          Statement st=dbd.getStatement();
          String query="Update client_info set name='"+fname+"',dob='"+fdob+"',mobile='"+fmobile+"',emailid='"+femail+"',password='"+fpwd+"' where username='"+fusername+"'";
          int x=st.executeUpdate(query);
          if(x>0)
              flag=true;
           st.close();
        dbd.st.close();
        dbd.con.close();
          
       }
       catch(Exception e)
       {
           System.out.println("Exception at class ClientDBOP with method isEdited() "+e);
           
       }
       return flag;
   }
    
}
