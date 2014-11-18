package com.blogspot.lucbors.ch5.mobile.dc;


import com.blogspot.lucbors.ch5.mobile.pojo.Attendee;
import com.blogspot.lucbors.ch5.mobile.pojo.Distribution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AttendeeService {
    
    private List s_attendees=null;
    private List s_distribution = null;
    public AttendeeService() {
        super();
        if (s_attendees == null) {
   
            s_attendees = new ArrayList();
                   
         
//            s_attendees.add(new Attendee(0, "Peter",  "Arrow",     "Granville street", "Vancouver ","postal","state", "CAN" ,"peter@somecomp.com","+31652327240" )) ;
//            s_attendees.add(new Attendee(1, "Joe",    "Jones",     "Centre street", "Calgary ","postal","state", "CAN" ,"joe.jones@somecomp.com","+31652327240" )) ;
//            s_attendees.add(new Attendee(2, "Steve",  "Muller",    "Canal street", "New Orleans","postal","state",  "USA" ,"steve.jones@somecomp.com","+31652327240") ) ;
//            s_attendees.add(new Attendee(3, "Hendry", "Jamesson",  "State street", "Boston","postal","state", "USA" ,"hendry@somecomp.com","+31652327240") ) ;
//            s_attendees.add(new Attendee(4, "Bill",   "Jones",     "Wall street", "New York","postal","state", "USA" ,"bill.jones@somecomp.com","+31652327240" )) ;
//            s_attendees.add(new Attendee(5, "Shay",   "Stevens",   "Market street", "San Francisco","postal","state", "USA" ,"shay.s@somecomp.com","+31652327240" )) ;                                
//            s_attendees.add(new Attendee(6, "Luc",    "Bors",      "Edisonbaan 15", "Nieuwegein","3439MN","Utrecht", "NL" ,"luc.bors@somecomp.com","+31652327240" ) );
//
//


            s_attendees.add(new Attendee(0, "Peter",  "Arrow",     "Granville street", "Vancouver ","postal","state", "CAN" ,"peter@somecomp.com","+31652327240" )) ;
            s_attendees.add(new Attendee(1, "Luc",    "Bors",      "Edisonbaan 15", "Nieuwegein","3439MN","Utrecht", "NL" ,"luc.bors@somecomp.com","+31652327240" ) );
            s_attendees.add(new Attendee(2, "Hendry", "Jamesson",  "State street", "Boston","postal","state", "USA" ,"hendry@somecomp.com","+31652327240") ) ;
            s_attendees.add(new Attendee(3, "Joe",    "Jones",     "Centre street", "Calgary ","postal","state", "CAN" ,"joe.jones@somecomp.com","+31652327240" )) ;
            s_attendees.add(new Attendee(4, "Bill",   "Jones",     "Wall street", "New York","postal","state", "USA" ,"bill.jones@somecomp.com","+31652327240" )) ;
            s_attendees.add(new Attendee(4, "Steve",  "Muller",    "Canal street", "New Orleans","postal","state",  "USA" ,"steve.jones@somecomp.com","+31652327240") ) ;
            s_attendees.add(new Attendee(6, "Shay",   "Stevens",   "Market street", "San Francisco","postal","state", "USA" ,"shay.s@somecomp.com","+31652327240" )) ;                                


        } 
        if (s_distribution == null){
            determineDistribution();
        }
    }

    public Attendee[] getAttendees() {
    //This Method gets a list of the attendees
    
  
        Attendee[] attendees = null;

        attendees = (Attendee[])s_attendees.toArray(new Attendee[s_attendees.size()]);

        return attendees;
    }
   
   
    public Distribution[] getDistribution(){
    
        Distribution[] distribution = null;

        distribution = (Distribution[])s_distribution.toArray(new Distribution[s_distribution.size()]);

        return distribution;

    }
    
   public void determineDistribution(){

       s_distribution = new ArrayList();

       ArrayList tempatts = new ArrayList();
       tempatts.addAll(s_attendees);

       Collections.sort(tempatts);
 
       Attendee[] sortedattendees = null;

       sortedattendees = (Attendee[])tempatts.toArray(new Attendee[tempatts.size()]);

       for (int q = 0; q<sortedattendees.length; q++)
       
       {   Attendee e = sortedattendees[q];
           
           System.out.println(e.getCountry() + "\t" + e.getFirstName() + "\t" + e.getLastName()); 
           }
       
       
       int len = sortedattendees.length;
       String country = "x";
       String prevcountry ="x";
       int counter=0;
       for (int x = 0; x < len; x++) {
           country = sortedattendees[x].getCountry();
                 
           if (country.equalsIgnoreCase(prevcountry)){
             counter++;  
           }
           else{
             // always when switching to new country
               if (!prevcountry.equalsIgnoreCase("x")) { 
                   s_distribution.add(new Distribution(prevcountry,counter));  
               }
                   counter=1;  
                   prevcountry=country; 
           }
                    
       }   
       // and also the final country; do this outside the loop 
       s_distribution.add(new Distribution(prevcountry,counter));  
       
               
   }

}
