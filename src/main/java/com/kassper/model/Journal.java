package com.kassper.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Journal {
    private static String id_journal;
    private static Date date;
    private static int id_person;
    private static int id_predmet;
    private static int attendense;
    private static int assessment;
    private static String lesonTopic;

    private static ResultSet rs;

    private static void allJournalOne (){
        try {
            id_journal = rs.getString(1);
            date = rs.getDate(2);
            id_person = rs.getInt(3);
            id_predmet = rs.getInt(4);
            attendense = rs.getInt(5);
            assessment = rs.getInt(6);
            lesonTopic = rs.getString(7);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }

    private static String formTableResult(ResultSet rs) {
        String text = "";
        try {
            while (rs.next()) {
                text = "<tr>" +
                        "<th>" + rs.getInt(1) + "</th>" +
                        "<th>" + Journal.rs.getString(2) + " " + Journal.rs.getString(3) + " " + Journal.rs.getString(4) + "</th>" +
                        "<th>" + rs.getBoolean(5) + "</th>" +
                        "<th>" + rs.getString(6) + "</th>" +
                        "</tr>";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return text;
    }
    private static String formTableResoultCtrate(ResultSet rs){
        String text = "";
        try {
            int lastRou = allJournal().getRow();
            while (rs.next()) {
                lastRou++;
                text += "<tr>" +
                        "<th>" + lastRou + "</th>" +
                        "<th>"+rs.getString(2)+" "+rs.getString(2)+" "+rs.getString(3)+"</th>" +
                        "<th>" +
                        "<select  class=\"select1\" size=\"1\" name=\"select1\">" +
                        //"<option selected></option>" +
                        "<option value = \"true\" name = \"select1\">true</option>" +
                        "<option value = \"false\" name = \"select\">false</option>" +
                        "</select>" +
                        "</th>" +
                        "<th>" +
                        "<input type=\"number\" placeholder=\"0\" name=\"Assesment\" min=\"0\" max=\"10\" >" +
                        "</th>" +
                        "</tr>";

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return text;
    }
    public static ResultSet allPersonOneGrope(int nomGroup) {
        String query = "select * from \"JournalDB\".\"person\" where \"nomGroup\" = " + nomGroup;
        rs = bd.conResoultSet(query);
        return rs;
    }
    public static ResultSet allJournal(){
        String query = "select * from \"JournalDB\".\"Journal\"";
        rs = bd.conResoultSet(query);
        return rs;
    }

    public static String createJournal(int nomGroup,String date,String predmet){
        String s = formTableResoultCtrate(allPersonOneGrope(nomGroup));
        createList(nomGroup,date,predmet);
        System.out.println(s);
        return s;
    }
    public static String[][] createList(int nomGroup, String data, String predmet) {
        ResultSet rs = allPersonOneGrope(nomGroup);
        String[][] masListJournal ;
        try {
            masListJournal = new String[rs.getRow()][8];
            int lastNumber = allJournal().getRow();
            int i = 0;
            while (rs.next()) {
                System.out.println("11111111111"+i);
                masListJournal[i][0] = Integer.toString(lastNumber);
                System.out.println("11111111111");
                masListJournal[i][1] = data;
                System.out.println("11111111111");
                masListJournal[i][2] = predmet;
                System.out.println("11111111111");
                masListJournal[i][3] = "false";
                System.out.println("11111111111");
                masListJournal[i][4] = "";
                System.out.println("11111111111");
                masListJournal[i][5] = "";
                System.out.println("11111111111");
                lastNumber++;
                i++;
            }
            return masListJournal;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return masListJournal = new String[1][1];
    }

    public static String allPositionJournalView (int nomGroup, String dateYers, String predmet){
        String text="";
        String query="";
        ResultSet rs = allPersonOneGrope(nomGroup);
        try {
            while (rs.next()){
                query = "select * from \"JournalDB\".\"Journal\" " +
                        "where id_person = "+rs.getInt(1)+" and date='"+dateYers+"'" +
                        ""+ (!predmet.equals("all subject") ? " and id_predmet = "+predmet : "");
                System.out.println(query+" "+rs.getInt(1));
                text += formTableResult(bd.conResoultSet(query));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return text;
    }


}
