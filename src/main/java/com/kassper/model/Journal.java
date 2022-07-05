package com.kassper.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Journal {
    private static String id_journal;
    private static Date date;
    private static int id_person;
    private static int id_predmet;
    private static boolean attendense;
    private static byte assessment;
    private static String lesonTopic;

    private static ResultSet rs;

    private static void allJournalOne (){
        try {
            id_journal = rs.getString(1);
            date = rs.getDate(2);
            id_person = rs.getInt(3);
            id_predmet = rs.getInt(4);
            attendense = rs.getBoolean(5);
            assessment = rs.getByte(6);
            lesonTopic = rs.getString(7);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }


    public static String allPositionJournalView (String nomGroup, String subject){
        String query = "select * from \"JournalDB\".\"Journal\" where id_predmet = "+subject+"";
        rs = bd.conResoultSet(query);
        String text="";
        try {
            int i=0;
            while (rs.next()) {
                allJournalOne();
                if (i>=0)
                    text += "<tr>" +
                            "<th><a>"+id_journal+"<a/></th>" +
                            "<th><a>"+person.allPersonOfJounal(id_person)+"<a/></th>" +
                            "<th><a>"+attendense+"<a/></th></tr>"+
                            "<th><a>"+assessment+"<a/></th>";
                i++;
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return text;
    }
}
