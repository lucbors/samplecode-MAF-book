package com.blogspot.lucbors.book.ch11.mobile.vc.pojo;


import com.sun.util.logging.Level;

import java.util.ArrayList;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.exception.AdfInvocationException;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class UploadImage {
    public UploadImage() {
        super();
    }
    
   /** 
    * Wrapper method around the WS Data Control that accesses the Web Service to upload the image. This bean is needed
    * because there exists no JAX-WS proxy client for ADF Mobile v1, which however we need to convert an image URL to
    * a base64 encoded byte array. The image URL is chosen because it allows us to show a preview of the picture to 
    * upload in an af:image tag on an amx page. Without the requirement of a preview image we could have created the 
    * byte array directly (see PictureBean.java) and pass to the WS DC. 
    * 
    * The implementation for this sample is that the PhotoProcessing class (this) is exposed as a DataControl. Its method
    * "uploadImage" is then associated with the upload command button on the UploadPicture.amx page. To access the WS DC 
    * upload functionality - following ADF best practices - the method is exposed on the PageDef file of the UploadPicture.amx 
    * page so we can look it up there in this code. 
    * 
    * @param fileContent The image file wrapper object that contains information about
    *        the file name and type
    *        
     * @param fileType  JPG, PNG entered on the UploadPicture.amx page
     * @param fileName  The name of the file as it should appear on teh server after successful upload
     * @param fileOwner Name of the owner of the file 
     * @param description
     */
    public void uploadImage(String fileType, String fileName, String fileOwner, String description) {
            
       //First, access the binding layer to get the image URL reference as a string. Note that this sample does not
       //use base encoded data strings. However, to make it easier for people to change this sample to use base 64
       //encoded data strings, I am accessing the image URL through the "getPictureSource" method of the PictureBean
       //DataControl taken from the ADF mobile Device demos shipped with the product.
      // String imageSource = (String) AdfmfJavaUtilities.evaluateELExpression("#{bindings.getPictureSource.execute}");
        
       //OperationBinding getImageSourceMethod = (OperationBinding) getImageSourceExpression;       
       Object imageSource = (Object) AdfmfJavaUtilities.evaluateELExpression("#{bindings.Return.inputValue}");        
                
       ArrayList parameterNames = new ArrayList();
       ArrayList parameterValue = new ArrayList();
       ArrayList parameterTypes = new ArrayList();

        //access WS methods
        //arg0 == file content
        //arg1 == file type e.g. jpg
        //arg2 == file name on server
        //arg3 == file owner (authnticated username).
        //arg4 == file description
        parameterNames.add("arg0");
        parameterNames.add("arg1");
        parameterNames.add("arg2");
        parameterNames.add("arg3");
        parameterNames.add("arg4");
        parameterNames.add("arg5");
                
        parameterValue.add(imageSource);
        
        //information input frm UploadPicture.amx
        parameterValue.add(fileType);
        parameterValue.add(fileName);
        parameterValue.add(description);
        parameterValue.add(new Integer(19));
        parameterValue.add(fileOwner);
        
        parameterTypes.add(Object.class);
        parameterTypes.add(String.class);
        parameterTypes.add(String.class);
        parameterTypes.add(String.class);
        parameterTypes.add(int.class);
        parameterTypes.add(String.class);

        try {
            AdfmfJavaUtilities.invokeDataControlMethod("UploadImageDC", null, "saveImage",parameterNames, parameterValue,parameterTypes);
        } catch (AdfInvocationException e) {
            //Launch the PhoneGap alert dialog as there is no option to do the same with an ADF Mobile amx popup
//            AdfmfContainerUtilities.invokeContainerJavaScriptFunction("adf.sample.mobile.PhotoUpload",
 //                                                                     "navigator.notification.alert",
  //                                                                    new Object[] {"Image upload failed. See log for more details", "Ok"});
            //log exception
            logApplicationMessage(Level.SEVERE, "Exception during filr upload ::::\n"+e.getMessage(),"uploadImage");
        }
        
        //Launch the PhoneGap alert dialog as there is no option to do the same with an ADF Mobile amx popup
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction("com.blogspot.lucbors.book.ch11.Images",
                                                                 "navigator.notification.alert",
                                                                  new Object[] {"Image uploaded: \n ------------- \n\n"+
                                                                                "File Name: "+fileName+"\n"+
                                                                               "File Type: "+fileType,"",
                                                                                "Image Uploaded to Server", "Ok"});
    }
       
    /**
     * Logger wrapper method that uses the ADF Mobile logger to print messages
     * @param level  com.sun.util.logging.Level to define severity
     * @param message the log message
     * @param methodName the method in this class that performs logging
     */
    private void logApplicationMessage(Level level,String message, String methodName){
        Trace.log(Utility.FrameworkLogger, level, this.getClass(), methodName, message);
    }
   
    
}
