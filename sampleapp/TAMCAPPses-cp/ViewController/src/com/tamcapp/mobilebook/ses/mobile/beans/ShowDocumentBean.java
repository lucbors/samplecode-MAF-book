package com.tamcapp.mobilebook.ses.mobile.beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.el.ValueExpression;

import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class ShowDocumentBean
{
  private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

  public ShowDocumentBean()
  {
    super();
  }
  
  private String beanFilePath;
  private String ddcFilePath;
  private String previewHeader;
  
  public void beanPreview(ActionEvent e)
  {
    try
    {
      URL fileUrl = new URL("file", "localhost", beanFilePath);
      DeviceManagerFactory.getDeviceManager().displayFile(fileUrl.toString(), previewHeader);
    }
    catch (MalformedURLException f)
    {
      
    }
  }
  
  public void remotePreview(ActionEvent e)
  {
    InputStream is;
    FileOutputStream fos;
    try
    {
      // open connection to remote PDF file
      URL remoteFileUrl = new URL("http://www.oracle.com/technetwork/testcontent/dev-blackberry-129130.pdf");
      
 
      ValueExpression ve = AdfmfJavaUtilities.getValueExpression("#{bindings.fileURL.inputValue}", String.class);
      String inputpath = (String) ve.getValue(AdfmfJavaUtilities.getAdfELContext());  
      if (inputpath != null && !"".equals(inputpath)) {
          
            System.out.println("Using input path :" + inputpath);
            remoteFileUrl =  new URL(inputpath);
      }
   
      URLConnection connection = remoteFileUrl.openConnection();
      is = connection.getInputStream();
      // we write the file to the application directory
      File localFile = new File(AdfmfJavaUtilities.getDirectoryPathRoot(AdfmfJavaUtilities.ApplicationDirectory) + "/downloadedPDF.pdf");
      if (localFile.exists())
      {
        localFile.delete();
      }
      fos = new FileOutputStream(localFile);
      int x;
      int read = 0;
      while ((x = is.read()) != -1)
      {
        ++read;
        fos.write(x);
      }
      is.close();
      fos.close();

      // displayFile takes a URL string which has to be encoded.  
      // iOS does not handle "+" as an encoding for space (" ") but 
      // expects "%20" instead.  Also, the leading slash MUST NOT be 
      // encoded to "%2F".  We will revert it to a slash after the
      // URLEncoder converts it to "%2F".
      StringBuffer buffer = new StringBuffer();
      String path = URLEncoder.encode(localFile.getPath(), "UTF-8");
      
      // replace "+" with "%20"
      String replacedString = "+";
      String replacement = "%20";
      int index = 0, previousIndex = 0;
      index = path.indexOf(replacedString, index);
      while (index != -1)
      {
        buffer.append(path.substring(previousIndex, index)).append(replacement);
        previousIndex = index + 1;
        index = path.indexOf(replacedString, index + replacedString.length());
      }
      buffer.append(path.substring(previousIndex, path.length()));
      
      // revert the leading encoded slash ("%2F") to a literal slash ("/")
      if (buffer.indexOf("%2F") == 0)
      {
        buffer.replace(0, 3, "/");
      }
      
      
        ValueExpression ve1 = AdfmfJavaUtilities.getValueExpression("#{bindings.headerText.inputValue}", String.class);
        String headerText = (String) ve1.getValue(AdfmfJavaUtilities.getAdfELContext()); 
        if (headerText == null || "".equals(headerText)) {
            headerText = "Remote File";
              
        }
      // create URL and invoke displayFile with its String representation
      URL localURL = new URL("file", "localhost", buffer.toString());
      DeviceManagerFactory.getDeviceManager().displayFile(localURL.toString(), headerText);
    }
    catch (Exception f)
    {
      System.out.println("MDO - exception caught: " + f.toString());
    }
  }
  
  public void setBeanFilePath(String beanFilePath)
  {
    String oldBeanFilePath = this.beanFilePath;
    this.beanFilePath = beanFilePath;
    propertyChangeSupport.firePropertyChange("beanFilePath", oldBeanFilePath, beanFilePath);
  }

  public void addPropertyChangeListener(PropertyChangeListener l)
  {
    propertyChangeSupport.addPropertyChangeListener(l);
  }

  public void removePropertyChangeListener(PropertyChangeListener l)
  {
    propertyChangeSupport.removePropertyChangeListener(l);
  }

  public String getBeanFilePath()
  {
    return beanFilePath;
  }

  public void setDdcFilePath(String ddcFilePath)
  {
    String oldDdcFilePath = this.ddcFilePath;
    this.ddcFilePath = ddcFilePath;
    propertyChangeSupport.firePropertyChange("ddcFilePath", oldDdcFilePath, ddcFilePath);
  }

  public String getDdcFilePath()
  {
    return ddcFilePath;
  }

  public void setPreviewHeader(String previewHeader)
  {
    String oldPreviewHeader = this.previewHeader;
    this.previewHeader = previewHeader;
    propertyChangeSupport.firePropertyChange("previewHeader", oldPreviewHeader, previewHeader);
  }

  public String getPreviewHeader()
  {
    return previewHeader;
  }
}