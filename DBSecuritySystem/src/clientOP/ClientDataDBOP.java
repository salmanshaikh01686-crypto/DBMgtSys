
package clientOP;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class ClientDataDBOP 
{
    public boolean isTableCreated(ArrayList<String> columnnames,String tablename)
    {
       boolean flag=true;
       try
       {
         DBDriver dbd=new   DBDriver();
         Statement st=dbd.getStatement();
         
         String query1="create table IF NOT EXISTS "+tablename+"(";
         String query2="";
           for (int i = 0; i < columnnames.size(); i++) 
           {
               String singlecol=columnnames.get(i);
               query2=query2+singlecol+" VARCHAR(45),";
               
               
           }
           System.out.println("Query 1="+query1);
           System.out.println("Query 2="+query2);
           String finalquery=query1+query2+" PRIMARY KEY ("+columnnames.get(0)+"))";
           System.out.println("Final Query to Create the Table is "+finalquery);
           st.executeUpdate(finalquery);
           
         
       }
       catch(Exception ex)
       {
           System.out.println("Exception at class ClientDataDBOP in method isTableCreated() is"+ex);
           flag=false;
       }
       return flag;
    }
    
    
    
    
    
    
    public boolean isDatainfoStored(String client_name,String filename,String date_time)
   {
    boolean flag=false;
    try
    {
        DBDriver dbd=new DBDriver();
        Statement st=dbd.getStatement();
        //clientname, filename, date_time
        String query="Insert into client_data_info values('"+client_name+"','"+filename+"','"+date_time+"')";
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
        
        System.out.println("Exception at ClientDataDBOP with method isDatainfoStored()"+e);
        flag=false;
    }
    return flag;
   } 
    public ArrayList getStoredFileInfo(String client_name)
    {
        ArrayList data=new ArrayList();
        try
        {
             DBDriver dbd=new DBDriver();
             Statement st=dbd.getStatement();
             String query="Select * from client_data_info where clientname='"+client_name+"'";
             ResultSet rs=st.executeQuery(query);
             while(rs.next())
             {
                 String filename=rs.getString(2);
                 String date_time=rs.getString(3);
                 ArrayList row=new ArrayList();
                 row.add(filename);
                 row.add(date_time);
                 data.add(row);
             }
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class ClientDataDBOP in method getStoredFileInfo() "+ex);
        }
        return data;
    }
}
