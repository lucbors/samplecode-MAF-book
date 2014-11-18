package com.tamcapp.mobilebook.att.mobile.beans;


import com.tamcapp.mobilebook.att.mobile.pojos.PersonDetail;
import com.tamcapp.mobilebook.att.utils.TamcappUtils;

import javax.el.MethodExpression;
import javax.el.ValueExpression;

import oracle.adf.model.datacontrols.device.Contact;
import oracle.adf.model.datacontrols.device.ContactAddresses;
import oracle.adf.model.datacontrols.device.ContactField;
import oracle.adf.model.datacontrols.device.ContactName;
import oracle.adf.model.datacontrols.device.DeviceManager;
import oracle.adf.model.datacontrols.device.DeviceManagerFactory;

import oracle.adfmf.amx.event.ActionEvent;
import oracle.adfmf.framework.api.AdfmfJavaUtilities;
import oracle.adfmf.framework.model.AdfELContext;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;


public class AttendeesBean {


    private int currentAttendee;
    private boolean me = false;
   private transient PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);


    public AttendeesBean() {
        super();
    }

    public void getFromFile(ActionEvent ae){
        DeviceManager dm = DeviceManagerFactory.getDeviceManager(); 
          String theImage = dm.getPicture(
                             50
                            ,DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL
                            ,DeviceManager.CAMERA_SOURCETYPE_PHOTOLIBRARY
                            , false
                            ,DeviceManager.CAMERA_ENCODINGTYPE_PNG 
                            ,400 
                            ,200);
        
        // Only upload if the user did not cancel the camera.
          if(theImage!=null){
              doUpload(theImage);
        }
        
    }

  public void takePhoto(ActionEvent ae) {  
  
      DeviceManager dm = DeviceManagerFactory.getDeviceManager(); 
      if (dm.hasCamera()){
        String theImage = dm.getPicture(
                           50
                          ,DeviceManager.CAMERA_DESTINATIONTYPE_DATA_URL
                          ,DeviceManager.CAMERA_SOURCETYPE_CAMERA
                          , false
                          ,DeviceManager.CAMERA_ENCODINGTYPE_PNG 
                          ,400 
                          ,200);
      
      // Only upload if the user did not cancel the camera.
        if(theImage!=null){
            doUpload(theImage);
      }    
      }
       
  }
  
  public void doUpload(String theImage){
      AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
      
      ValueExpression veImg =
                  AdfmfJavaUtilities.getValueExpression("${bindings.uploadImage_imageSource}", String.class);
      
      veImg.setValue(adfELContext, theImage);
           
      MethodExpression me = AdfmfJavaUtilities.getMethodExpression("#{bindings.uploadImage.execute}", Object.class, new Class[] {});
      me.invoke(adfELContext, new Object[] {});
          
  }
  
     
  

    public void sendSms(ActionEvent ae) {
        AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
        String body = "Want to have lunch ?";
        ValueExpression veTo = AdfmfJavaUtilities.getValueExpression("#{bindings.email.inputValue}", String.class);
        String to = veTo.getValue(adfELContext).toString();
        DeviceManager dm = DeviceManagerFactory.getDeviceManager();
        dm.sendSMS(to, body);

    }

    public void sendEmail(ActionEvent ae) {

        AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();
        String subject = "Meeting during TAMCAPP event";
        DeviceManager dm = DeviceManagerFactory.getDeviceManager();
        ValueExpression veTo = AdfmfJavaUtilities.getValueExpression("#{bindings.email.inputValue}", String.class);
        dm.sendEmail((String)veTo.getValue(adfELContext), null, subject, null, null, null, null);
    }


    public void addToContacts(ActionEvent ae) {


        //     ValueExpression vex = AdfmfJavaUtilities.getValueExpression("#{bindings.AttendeesDetailIterator}", Object.class);
        //     AmxIteratorBinding iter = (AmxIteratorBinding)vex.getValue(AdfmfJavaUtilities.getAdfELContext());
        //     GenericType row = null;
        //     BasicIterator bIter = iter.getIterator();
        //     iter.getIterator().first();
        //
        PersonDetail prd = new PersonDetail();
        AdfELContext adfELContext = AdfmfJavaUtilities.getAdfELContext();

        ValueExpression veFn = AdfmfJavaUtilities.getValueExpression("#{bindings.firstName.inputValue}", String.class);
        prd.setFirstName(veFn.getValue(adfELContext).toString());

        ValueExpression veLn = AdfmfJavaUtilities.getValueExpression("#{bindings.lastName.inputValue}", String.class);
        prd.setLastName(veLn.getValue(adfELContext).toString());

        ValueExpression vePhone = AdfmfJavaUtilities.getValueExpression("#{bindings.phone.inputValue}", String.class);
        prd.setPhone(vePhone.getValue(adfELContext).toString());

        ValueExpression veEmail = AdfmfJavaUtilities.getValueExpression("#{bindings.email.inputValue}", String.class);
        prd.setEmail(veEmail.getValue(adfELContext).toString());

        ValueExpression veCountry =
            AdfmfJavaUtilities.getValueExpression("#{bindings.countryName.inputValue}", String.class);
        prd.setCountryName(veCountry.getValue(adfELContext).toString());


        ContactField[] phoneNumbers = null;
        ContactField[] emails = null;
        ContactAddresses[] addresses = null;

        /*?* Create a new contact */
        Contact newContact = new Contact();
        ContactName name = new ContactName();

        name.setFamilyName(prd.getLastName());
        name.setGivenName(prd.getFirstName());
        newContact.setName(name);
        ContactField phoneNumber = new ContactField();
        phoneNumber.setType("mobile");
        phoneNumber.setValue(prd.getPhone());
        phoneNumbers = new ContactField[] { phoneNumber };
        ContactField email = new ContactField();
        email.setType("home");
        email.setValue(prd.getEmail());
        emails = new ContactField[] { email };
        ContactAddresses address = new ContactAddresses();
        address.setType("home");
        address.setStreetAddress("");
        address.setLocality("");
        address.setCountry(prd.getCountryName());
        addresses = new ContactAddresses[] { address };
        newContact.setNote("Met this person with TAMCAPP");
        newContact.setPhoneNumbers(phoneNumbers);
        newContact.setEmails(emails);
        newContact.setAddresses(addresses);
        DeviceManager dm = DeviceManagerFactory.getDeviceManager();
        dm.createContact(newContact);

    }


    public void goDetails(ActionEvent actionEvent) {
        TamcappUtils.doNavigation("details");
    }

    public void setCurrentAttendee(int currentAttendee) {
        this.currentAttendee = currentAttendee;
    }

    public int getCurrentAttendee() {
        return currentAttendee;
    }

   

   
    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }

  
    public void setMe(boolean me) {
        boolean oldMe = this.me;
        this.me = me;
        propertyChangeSupport.firePropertyChange("me", oldMe, me);
    }

    public boolean isMe() {
        return me;
    }
}
