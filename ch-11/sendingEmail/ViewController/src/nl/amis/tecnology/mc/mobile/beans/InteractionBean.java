package nl.amis.tecnology.mc.mobile.beans;

import oracle.adf.model.datacontrols.device.DeviceManager;
import oracle.adf.model.datacontrols.device.DeviceManagerFactory;
import oracle.adf.model.datacontrols.device.Location;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;


public class InteractionBean {
    public InteractionBean() {
    }

    public void executeLogic(ActionEvent actionEvent) {
        // Add event code here...
        String att  = getPicture();
     
        Location whereAmI = getPosition();
        
        sendEmail(att, whereAmI);
    }
    
    
    public Location getPosition(){
        Location currentPosition = DeviceManagerFactory.getDeviceManager().getCurrentPosition(60000, true); 
        return currentPosition;
    }
    
    public String getPicture(){
        
        
        //destinationType = 1 so that the image is returned as a filename
        
        String theImage = DeviceManagerFactory.getDeviceManager().getPicture(100,
                                     DeviceManager.CAMERA_DESTINATIONTYPE_FILE_URI,
                                     DeviceManager.CAMERA_SOURCETYPE_CAMERA, false,
                                     DeviceManager.CAMERA_ENCODINGTYPE_PNG, 
                                     0, 
                                     0);

        return theImage;
    }
    
    
    public void sendEmail(String attachment, Location here){
        String directoryPathRoot = AdfmfJavaUtilities.getDirectoryPathRoot(0); //TemporaryDirectory
        String attachment2 = directoryPathRoot+"/Pic.png";
        String attachment3 = directoryPathRoot+"/"+attachment;
        String attachment4 = directoryPathRoot+attachment;
        
        String allAtt = "attachment,attachment2, attachment3,attachment4";
           
        //System.out.println("---------");
        //System.out.println(directoryPathRoot);
            
        //String bcc = "lucbors@gmail.com, luc.bors@amis.nl";
        
        String bcc = constructBcc();    
        //System.out.println("---------");
        
        String content = "The picture was taken at the following coordinates: latitude=" + here.getLatitude() +
        ", longitude=" + here.getLongitude();
           
        DeviceManagerFactory.getDeviceManager().sendEmail("John.Doe@home.org"
                                                          , null
                                                          , "This is an email with attached image and GPS coordinates" 
                                                          , content
                                                          , bcc
                                                          , attachment2
                                                          , "image/jpg");

    }
    
    public String constructBcc(){
        String bcc=".....";
       
        return bcc;
    }
    }
/*
void sendEmail(java.lang.String to,
               java.lang.String cc,
               java.lang.String subject,
               java.lang.String body,
               java.lang.String bcc,
               java.lang.String attachments,
               java.lang.String mimeTypes)
*/