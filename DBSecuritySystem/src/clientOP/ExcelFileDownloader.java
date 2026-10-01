
package clientOP;

import jxl.write.Label;
import java.io.File;
import java.util.ArrayList;
import jxl.*;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.WritableCell;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;


public class ExcelFileDownloader 
{
 public boolean isDownloaded(ArrayList completedata, String finalpath)
 {
   
 
boolean flag=false;
try
{
     File file=new File(finalpath);
     WritableWorkbook workbook = Workbook.createWorkbook(file);
     WritableSheet sheet = workbook. createSheet ("Sheet 1", 0);
     WritableFont cellFont = new WritableFont(WritableFont.TIMES, 11) ;
     WritableCellFormat cellFormat = new WritableCellFormat(cellFont);
     Label lb=null;
     for (int i = 0; i < completedata.size();i++)
    {
        ArrayList row=(ArrayList)completedata.get(i);

        for (int j= 0; j <row.size();j++)
        {
        String content=(String)row.get(j);
        lb=new Label(j,i,content,cellFormat);
        sheet.addCell((WritableCell) lb);

       } 
   }
     workbook.write();
     workbook.close();
flag=true;
}
catch(Exception ex)
{
    System.out.println("Exception at class ExcelFileDownloader in method ExcelFileDownloader() "+ex);
}
return flag;
 
}
}
