package com.tamcapp.mobilebook.ch05.mobile.model.dataservices;


import com.tamcapp.mobilebook.ch05.mobile.model.pojo.Attendee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import oracle.adfmf.java.beans.ProviderChangeListener;
import oracle.adfmf.java.beans.ProviderChangeSupport;


public class AttendeesService {


    private static List s_searchResults=null;
    
    private transient ProviderChangeSupport providerChangeSupport = new ProviderChangeSupport(this);
    
  
    public AttendeesService() {
        super();
        if (s_searchResults == null) {
            s_searchResults = new ArrayList();
        }
        
    }
    public Attendee[] getAttendees() {
        
            Attendee[] attendees = null;
            s_searchResults = new ArrayList();
                   
            s_searchResults.add(new Attendee(0, "Bill", "Jones", "USA" ,"bill.jones@somecomp.com","+31652327240", "" )) ;
            s_searchResults.add(new Attendee(1, "Steve", "Muller", "USA" ,"steve.jones@somecomp.com","+31652327240", "") ) ;
            s_searchResults.add(new Attendee(2, "Luc", "Bors", "NL" ,"luc.bors@somecomp.com","+31652327240", "" ) );
            s_searchResults.add(new Attendee(3, "Hendry", "Jamesson", "USA" ,"hendry@somecomp.com","+31652327240", "") ) ;
            s_searchResults.add(new Attendee(4, "Peter", "Arrow", "USA" ,"peter@somecomp.com","+31652327240", "" )) ;
            s_searchResults.add(new Attendee(5, "Joe", "Jones", "USA" ,"joe.jones@somecomp.com","+31652327240", "" )) ;
            s_searchResults.add(new Attendee(6, "Shay", "Stevens", "USA" ,"shay.s@somecomp.com","+31652327240", "" )) ;
                      
            attendees = (Attendee[]) s_searchResults.toArray(new Attendee[s_searchResults.size()]);
            return attendees;
        } 
    
    
    public void sortSearchResults(){
        
        Collections.sort(s_searchResults);
    }
    
    public void clearSearchResults() {
        s_searchResults.clear();
        providerChangeSupport.fireProviderRefresh("attendees");
    }
    
    public void addSearchResult(Attendee l) {
        s_searchResults.add(l);
        providerChangeSupport.fireProviderCreate("attendees", l.getId(), l);
    } 
  
    
    public void addProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.addProviderChangeListener(l);
    }

    public void removeProviderChangeListener(ProviderChangeListener l) {
        providerChangeSupport.removeProviderChangeListener(l);
    }

        public void searchAttendees(String searchString) {
  
        }
}
