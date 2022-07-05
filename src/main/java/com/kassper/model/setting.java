package com.kassper.model;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

public class setting {

    private static int id = -1;
    private static String name = null;

    private static ResultSet rs =null;

    private static void onePosition(){
        try {
            id = rs.getInt(1);
            name = rs.getString(2);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static String allPosition (String identity){
        String query = "select * from \"JournalDB\".\""+identity+"\"";
        rs = bd.conResoultSet(query);
        String text="";
        try {
            while (rs.next()) {
                onePosition();
                text += "<tr><th><a>"+id+"<a/></th><th><a>"+name+"<a/></th></tr>";
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return text;
    }

    public static String allPositionOnPersonView (String identity){
        String query = "select * from \"JournalDB\".\""+identity+"\"";
        rs = bd.conResoultSet(query);
        String text="";
        try {
            int i=0;
            while (rs.next()) {
                onePosition();
                if (i>=0)
                    if (i>3)
                text += "<option value = \""+i+"\" name = \"select\">"+name+"</option>";
                i++;
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return text;
    }

    public static void addSeiing(String id, String name, String identity) {
        if (id !=null && name !=null){
            String qwery = "select * from \"JournalDB\".\""+identity+"\" where id_"+identity+"  = "+id+" or \"name"+identity+"\" ='"+name+"'";
            rs = bd.conResoultSet(qwery);
            try {
                while (rs.next()){ onePosition();}
                if (!rs.wasNull()){
                    qwery = "insert into \"JournalDB\".\""+identity+"\" values ("+id+",'"+name+"')";
                    bd.executeUppMethod(qwery);
                }
                else
                {
                    System.out.println("Запись существует");
                }
            }
            catch (SQLException e){
                System.out.println(e);
            }
        }
    }

    public static void dellSetting (String id, String name, String identity){
        if (id !=null && name !=null && !id.equals("0")){
            String qwery = "Delete from \"JournalDB\".\""+identity+"\" where id_"+identity+"="+id+" and name"+identity+"='"+name+"'";
            bd.executeUppMethod(qwery);
        }
    }
}
