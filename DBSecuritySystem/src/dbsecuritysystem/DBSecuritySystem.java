
package dbsecuritysystem;

import DBEngine.EmailFetcher;
import DBEngine.NewDBDriver;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;


public class DBSecuritySystem {

    
    public static void main(String[] args) 
    {
     LoginFrame lf=new LoginFrame();
     Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
     lf.setVisible(true);
     lf.setSize(d);
    
       
        
    }
    
}
