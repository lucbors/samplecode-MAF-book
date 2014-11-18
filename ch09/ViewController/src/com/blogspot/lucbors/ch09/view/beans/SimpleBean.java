package com.blogspot.lucbors.ch09.view.beans;

import com.sun.util.logging.Level;

import oracle.adfmf.framework.api.AdfmfContainerUtilities;
import oracle.adfmf.java.beans.PropertyChangeListener;
import oracle.adfmf.java.beans.PropertyChangeSupport;
import oracle.adfmf.util.Utility;
import oracle.adfmf.util.logging.Trace;


public class SimpleBean {
    
    private String firstName;
    private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    public void setFirstName(String firstName) {
        Trace.log(Utility.ApplicationLogger, 
                  Level.INFO, 
                  SimpleBean.class, 
                  "setFirstName",
                  "firstName= "+firstName);

        String oldFirstName = this.firstName;
        this.firstName = firstName;
        propertyChangeSupport.firePropertyChange("firstName", oldFirstName, firstName);
              
        setFullName(this.firstName + " "+ this.lastName);
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        String oldLastName = this.lastName;
        this.lastName = lastName;
        propertyChangeSupport.firePropertyChange("lastName", oldLastName, lastName);
        setFullName(this.firstName + " "+ this.lastName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setFullName(String fullName) {
        Trace.log(Utility.ApplicationLogger, 
                  Level.INFO, 
                  SimpleBean.class, 
                  "setFullName",
                  "fullName= "+fullName);
        String oldFullName = this.fullName;
        this.fullName = fullName;
        AdfmfContainerUtilities.invokeContainerJavaScriptFunction("com.blogspot.lucbors.ch09.debugFeature", "showMessage", new Object[] {  });
        
        propertyChangeSupport.firePropertyChange("fullName", oldFullName, fullName);
        if (this.lastName==null){
            Trace.log(Utility.ApplicationLogger, 
                      Level.SEVERE, 
                      SimpleBean.class, 
                      "setFullName",
                      "lastName is empty" );
        }
    }

    public String getFullName() {
        return fullName;
    }
    private String lastName;

    private String fullName;
 
    
    public SimpleBean() {
        super();
    }


    public void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.addPropertyChangeListener(l);
    }

    public void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChangeSupport.removePropertyChangeListener(l);
    }
}
