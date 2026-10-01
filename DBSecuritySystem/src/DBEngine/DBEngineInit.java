
package DBEngine;

import adminOP.DBSecurityEngineFrame;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

    
public class DBEngineInit extends Thread
{
    public String clientname=null;
    public String tablename=null;
   
    public void run()
    {
         ArrayList columnnames=new EngineDataProvider().getColumnNames(tablename);
         ArrayList orgdata=new EngineDataProvider().getCompleteTableData(tablename, columnnames.size());
        ArrayList<String> emailid=new EmailFetcher().getEmailAddress("grishant");
        System.out.println("Email Id="+emailid);
        String adminemailid=emailid.get(0);
        String clientemailid=emailid.get(1);        
         System.out.println("Original Data is Fetched");
        while(true)
        {
            try
            {
               Thread.sleep(5000);
               System.out.println("Visited Database Table="+tablename);
               ArrayList dbuserdata=new NewDBDriver().getDBManagerCredentials();
               String dbusername=(String) dbuserdata.get(0);
               if(!dbusername.equals("root"))
               {
                   ArrayList currentdata=new EngineDataProvider().getCompleteTableData(tablename, columnnames.size());  
                   Date dt=new Date();
           SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-YYYY hh:mm:ss");
           String currentTD=sdf.format(dt);
           if(!orgdata.equals(currentdata))
           {
              ArrayList finaldata= new EngineDataProvider().getTamperedFields(orgdata, currentdata, columnnames,tablename);
              ArrayList tamperedfields=(ArrayList) finaldata.get(0);
              String temperedid=(String) finaldata.get(1);
              
               
               
               String message1="ALERT ALERT a table name: "+tablename+" is Tampered on id  "+temperedid;
               String message2= " and on Fields "+tamperedfields+ " by the Database Manager: "+dbusername+" on "+currentTD;
               String message=message1+" "+message2+" and Data is Restored Successfully. ";
               System.out.println("Message: "+message);
               String adminmessage="Dear Admin\n"+message+"\n"+"And this table belogs to client"+clientname;
               String endnote="Thanks and Regards\n From\n Automatic Database Security System ";
               String finaladminmessage=adminmessage+"\n\n"+endnote;
               String subject="ALERT FOR DATA TAMPERING";
               
               
               SendEmail se=new SendEmail();
               se.sendMailNow(finaladminmessage, subject, adminemailid);
               
               
               String clientmessage="Dear Client "+clientname+"\n"+message;
               String finalclientmessage=clientmessage+"\n"+endnote;
               se.sendMailNow(finalclientmessage, subject, clientemailid);
               
               
              String resultstr= DBSecurityEngineFrame.jTextArea1.getText()+"\n"+message;
               DBSecurityEngineFrame.jTextArea1.setText(resultstr);
           }
           System.out.println("Data Fetched at="+currentTD);
            }
               
               
               
               
            }
            catch(Exception ex)
            {
                System.out.println("Exception at class DBEngineInit in method run() "+ex);
            }
        }
    }
    
}
