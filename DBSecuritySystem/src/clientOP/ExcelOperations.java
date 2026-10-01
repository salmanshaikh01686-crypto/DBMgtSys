
package clientOP;

import db.DBDriver;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import jxl.*;

public class ExcelOperations 
{
    public ArrayList<String> getColumnNames(String filepath)
    {
     ArrayList<String> columnnames=new ArrayList<String>();
    try
    {
        File file=new File(filepath);
        Workbook wb=Workbook.getWorkbook(file);
        Sheet sht=wb.getSheet(0);
        int rows=sht.getRows();
        int cols=sht.getColumns();
        System.out.println("Rows="+rows+" Columns="+cols);
        for (int j = 0; j < cols; j++) 
        {
            Cell cl=sht.getCell(j,0);
            String content=cl.getContents();
            columnnames.add(content);
                        
        }
        wb.close();
        
    }
    catch(Exception ex)
    {
        System.out.println("Exception at class ExcelOperations in method getColumnnames() is "+ex);
    }
    return columnnames;
    
    }
    public boolean isTabledataStored(String filepath,ArrayList<String> columnnames,String tablename)
    {
        boolean flag=true;
        try
        {
            DBDriver dbd=new DBDriver();
            dbd.getStatement();
            Connection con=dbd.con;
            String query1="INSERT INTO "+tablename+"(";
            String query2="";
            String query3=") VALUES(";
            for (int i = 0; i < columnnames.size(); i++) 
            {
                query2=query2+columnnames.get(i)+", ";
                query3=query3+"?, ";
            }
        query2=query2.substring(0,query2.length()-2);
        query3=query3.substring(0,query3.length()-2)+")";
        String finalquery=query1+query2+query3;
        PreparedStatement ps=con.prepareStatement(finalquery);
        File file=new File(filepath);
        Workbook wb=Workbook.getWorkbook(file);
        Sheet sht=wb.getSheet(0);
        int rows=sht.getRows();
        int cols=sht.getColumns();
            for (int i = 1; i < rows; i++) 
            {
                for (int j = 0; j < cols; j++) 
                {
                    Cell cl=sht.getCell(j,i);
                    String cell_contents=cl.getContents();
                    //System.out.print(cell_contents+" ");
                    ps.setString(j+1, cell_contents);
                }
               // System.out.println();
               ps.addBatch();
               
             }
            ps.executeBatch();
            wb.close();
            con.close();
            
        
        }
        catch(Exception ex)
        {
            System.out.println("Exception at class ExcelOperations in method isTabledataStored() is "+ex);
        }
        return flag;
    }
}
