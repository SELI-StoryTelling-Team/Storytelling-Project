package model;

import java.net.InetAddress;
import java.net.NetworkInterface;

/**
 * Program to get System Mac Id of Windows or Linux Machine
 * @author includehelp
 */
public class SystemMacAddress {
    
    /** 
     * Method for get System Mac Address
     * @return  MAC Address
     */
    public static String getSystemMac(){
        try{
            String OSName=  System.getProperty("os.name");
            if(OSName.contains("Windows")){
                return (getMAC4Windows());
            }
            else{
                String mac=getMAC4Linux("eth0");
                if(mac==null){
                    mac=getMAC4Linux("eth1");
                    if(mac==null){
                        mac=getMAC4Linux("eth2");
                        if(mac==null){
                            mac=getMAC4Linux("usb0");
                        }
                    }
                }	
                return mac;
            }
        }
        catch(Exception E){
            System.err.println("System Mac Exp : "+E.getMessage());
            return null;
        }
    }
    
    /**
     * Method for get MAc of Linux Machine
     * @param name
     * @return 
     */
    private static String getMAC4Linux(String name){
        try {
            NetworkInterface network = NetworkInterface.getByName(name);
            byte[] mac = network.getHardwareAddress();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++){
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));        
            }
            return (sb.toString());
        }
        catch (Exception E) {
            System.err.println("System Linux MAC Exp : "+E.getMessage());
            return null;
        } 
    } 
	
    /**
     * Method for get Mac Address of Windows Machine
     * @return 
     */
    private static String getMAC4Windows(){
        try{
            InetAddress      addr     =InetAddress.getLocalHost();
            NetworkInterface network  =NetworkInterface.getByInetAddress(addr);

            byte[] mac = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();
            for(int i=0;i<mac.length;i++){
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
            }
            
            return sb.toString();
        }
        catch(Exception E){
            System.err.println("System Windows MAC Exp : "+E.getMessage());
            return null;
        }
    }
  
    public static void main(String[] args) {
        String macAddress = getSystemMac();
        System.out.println("System Mac Address : "+macAddress);
    }
    
}
