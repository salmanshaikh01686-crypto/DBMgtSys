/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientOP;

/**
 *
 * @author Administrator
 */
public class DigitValidator 
{
    public boolean isDigit(String str)
    {
        boolean flag=true;
        try
        {
            long x=Long.parseLong(str);
           // flag=true;
            
        }
        catch(Exception e)
        {
            System.out.println("Exception Occured "+e);
            flag=false;
        }
        return flag;
    }
    
}
