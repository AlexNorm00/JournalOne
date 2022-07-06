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

    public static String allPositionJournalView (int nomGroup, String dateYers, String predmet){
        String text="";
        String query="";
        int idPerson = -1;
        rs = allPersonOneGrope(nomGroup);
        try {
            while (rs.next()){
                allJournalOne();
                query = "select * from \"JournalDB\".\"Journal\" " +
                        "where id_person = "+rs.getInt(1)+" and date='"+dateYers+"'" +
                        ""+ (!predmet.equals("all subject") ? " and id_predmet = "+predmet : "");
                text = formTableResult(bd.conResoultSet(query));
            }
        }
        catch (SQLException e){
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

    public static String formTableResult(ResultSet rs) {
        String text = "";
        try {
            int i = 0;

            while (rs.next()) {
                text += "<tr>" +
                        "<th>" + id_journal + "</th>" +
                        "<th>" + rs.getString(2) + " " + rs.getString(3) + " " + rs.getString(4) + "</th>" +
                        "<th>" + attendense + "</th>" +
                        "<th>" + assessment + "</th>" +
                        "</tr>";
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return text;
    }


    public static String[][] createList(int nomGroup, String data) {
        rs = allPersonOneGrope(nomGroup);
        String[][] masListJournal ;
        try {
            masListJournal = new String[rs.getRow()][8];
            int lastNumber = allJournal().getRow();
            int i = 0;
            while (rs.next()) {
                masListJournal[i][0] = Integer.toString(lastNumber);
                masListJournal[i][1] = data;
                masListJournal[i][2] = rs.getString(1);
                masListJournal[i][3] = "false";
                masListJournal[i][4] = "";
                masListJournal[i][5] = "";
                lastNumber++;
            }
            return masListJournal;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return masListJournal = new String[1][1];
    }

}
