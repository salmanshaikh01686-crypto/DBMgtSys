
package DBEngine;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;


public class NewDBDriver
{
     public Statement st=null;
  public Connection con=null;

    public Statement getStatement()
            
    {
        ArrayList data=getDBManagerCredentials();
        String dbusername=(String) data.get(0);
        String dbpassword=(String) data.get(1);
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
           con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_security",dbusername,dbpassword);
           st=con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println("Exception at class NewDBDriver and function getStatement()"+e);
        }
        return st;
    }
    public ArrayList getDBManagerCredentials()
    {
        ArrayList data=new ArrayList();
        
        try
        {
        String bdlogpath="C:\\Users\\Administrator\\AppData\\Roaming\\MySQL\\Workbench\\log\\wb.log";

        FileReader fr=new FileReader(bdlogpath);
        BufferedReader reader=new BufferedReader(fr);
        String line=null;
         while((line=reader.readLine())!=null)
         {
             if(line.contains("Opened connection"))
             {
               String str1[]=line.split("connection");
               
               String second=str1[1];
               if(second.contains("_"))
               {
                   String str2[]=second.split("_");
                   String dbname=str2[0];
                   dbname=dbname.substring(2,dbname.length());
                   System.out.println("DB Name="+dbname);
                   String firstcharacter=dbname.substring(0, 1);
                   String password=firstcharacter.toUpperCase()+dbname.substring(1,dbname.length())+"@123";
                   System.out.println("Password="+password);
                   data.add(dbname);
                   data.add(password);
               }
               else
               {
                       data.add("root");
                       data.add("root"); 
                       System.out.println("DB Name="+"root");
               }
             }
         }
        } 
        catch(Exception ex)
        {
            System.out.println("Exception at class NewDBDriver in method getDBManagerCredentials() "+ex);  
        }
   
        return data;
    }
    
}
