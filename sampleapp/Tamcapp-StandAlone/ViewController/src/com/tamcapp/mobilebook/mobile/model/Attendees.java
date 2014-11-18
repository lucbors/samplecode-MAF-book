package com.tamcapp.mobilebook.mobile.model;


import com.tamcapp.mobilebook.mobile.pojos.Person;

import java.util.ArrayList;
import java.util.List;

public class Attendees {


    private List s_attendees = null;

    public Attendees() {
        super();
    }

    public Person[] getAttendees() {
            //This Method gets a list of the employees and their emails
            Person[] attendees = null;
            s_attendees = new ArrayList();
                   
            s_attendees.add(new Person(0, "Bill", "Jones", "USA" ,"bill.jones@somecomp.com","+31652327240", "" )) ;
            s_attendees.add(new Person(1, "Steve", "Muller", "USA" ,"steve.jones@somecomp.com","+31652327240", "") ) ;
            s_attendees.add(new Person(2, "Luc", "Bors", "NL" ,"luc.bors@somecomp.com","+31652327240", "" ) );
            s_attendees.add(new Person(3, "Hendry", "Jamesson", "USA" ,"hendry@somecomp.com","+31652327240", "") ) ;
            s_attendees.add(new Person(4, "Peter", "Arrow", "USA" ,"peter@somecomp.com","+31652327240", "" )) ;
            s_attendees.add(new Person(5, "Joe", "Jones", "USA" ,"joe.jones@somecomp.com","+31652327240", "" )) ;
            s_attendees.add(new Person(6, "Shay", "Stevens", "USA" ,"shay.s@somecomp.com","+31652327240", "" )) ;
                      
            attendees = (Person[]) s_attendees.toArray(new Person[s_attendees.size()]);
            return attendees;
        } 
}
