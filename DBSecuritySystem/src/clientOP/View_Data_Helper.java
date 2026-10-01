
package clientOP;

import db.DBDriver;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

    
public class View_Data_Helper 
{
    public String[] getColumnNames(String tablename)
    {
        String columnname[]=null;
        try
        {
          DBDriver dbd=new DBDriver();
         Statement st=dbd.getStatement();
         String query="Select * from "+tablename;
         ResultSet rs=st.executeQuery(query);
         ResultSetMetaData rsmd=rs.getMetaData();
                
         int noc=rsmd.getColumnCount();
         columnname=new String[noc];
            for (int i = 0; i < noc; i++) 
            {
                columnname[i]=rsmd.getColumnName(i+1);
                
            }
            
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class View_Data_Helper in Method  getColumnNames() is "+ex);
        }
        return columnname; 
        
    }
    public String [][] getTableData(String tablename,int columncount)
    {
        String table_data[][]=null;
        try
        {
            DBDriver dbd=new DBDriver();
         Statement st1=dbd.getStatement();
         Statement st2=dbd.getStatement();
         String query="Select *from "+tablename;
         ResultSet rs1=st1.executeQuery(query);
         ResultSet rs2=st2.executeQuery(query);
         int rowcount=0;
         while(rs1.next())
             rowcount++;
        table_data=new String[rowcount][columncount];
        int i=0;
        while(rs2.next())
        {
            for (int j = 0; j < columncount; j++) 
            {
           table_data[i][j]= rs2.getString(j+1);
              
            }
            i++;
        }
         
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class View_Data_Helper in method getTableData() "+ex);
        }
        return table_data;
    }
    
}
