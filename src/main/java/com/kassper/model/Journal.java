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
    private static int assessment;
    private static String lesonTopic;

    private static ResultSet rs;
    private static ResultSet rs1;

    private static void allJournalOne (){
        try {
            id_journal = rs.getString(1);
            date = rs.getDate(2);
            id_person = rs.getInt(3);
            id_predmet = rs.getInt(4);
            attendense = rs.getBoolean(5);
            assessment = rs.getInt(6);
            lesonTopic = rs.getString(7);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }


    public static String allPositionJournalView (int nomGroup){
        String text="";
        int idPerson = -1;
        String query = "select * from \"JournalDB\".\"person\" where \"nomGroup\" = "+nomGroup;
        rs = bd.conResoultSet(query);
        try {
            while (rs.next()){
                allJournalOne();
                query = "select * from \"JournalDB\".\"Journal\" where id_person = "+rs.getInt(1)+"";
                rs1 = bd.conResoultSet(query);
                System.out.print("2 ");
                int i=0;
                while (rs1.next()) {
                    System.out.println("3 ");
                    text += "<tr>" +
                            "<th>"+id_journal+"</th>" +
                            "<th>"+rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4)+"</th>" +
                            "<th>"+attendense+"</th>"+
                            "<th>"+assessment+"</th>" +
                            "</tr>";
                }
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
    return text;
    }
}
