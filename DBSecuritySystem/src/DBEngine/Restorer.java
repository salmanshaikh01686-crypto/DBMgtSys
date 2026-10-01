
package DBEngine;

import java.sql.Statement;
import java.util.ArrayList;


public class Restorer 
{
    public boolean isDataStored(String pkey,String pvalue,ArrayList<String> tamperedcolumnnames,ArrayList<String> replacedata,String tablename)
    {
//          String query="Update client_info set name='"+fname+"',dob='"+fdob+"',mobile='"+fmobile+"',emailid='"+femail+"',password='"+fpwd+"' where username='"+fusername+"'";
//          int x=st.executeUpdate(query);
        boolean flag=false;
        try
        {
           NewDBDriver dbd=new NewDBDriver();
           Statement st=dbd.getStatement(); 
           String query1="Update "+tablename+" set ";
           String query2="";
            for (int i = 0; i < tamperedcolumnnames.size(); i++) 
            {
                String Colname=tamperedcolumnnames.get(i);
                String value=replacedata.get(i);
                query2=query2+Colname+"='"+value+"',";
            }
            query2=query2.substring(0,query2.length()-1);
            String query=query1+query2+" where "+pkey+"='"+pvalue+"'";
            System.out.println("Query is "+query);
            int x=st.executeUpdate(query);
            if(x>0)
             flag=true;
            
            st.close();
            dbd.con.close();
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class Restorer in method isDataStored() "+ex);
        }
        return flag;
    }
    
}
