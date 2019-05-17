package core.supplementary;

/*
 * Class Implementation to generate random String for authentic code
 * @author Pravin Garad
 */

public class RandomString { 
	  
     
    public String getAlphaNumericString(int n) 
    { 
  
         
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        
        StringBuilder sb = new StringBuilder(n); 
  
        for (int i = 0; i < n; i++) { 
  
           int index = (int)(AlphaNumericString.length() * Math.random()); 
           sb.append(AlphaNumericString.charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
    
}