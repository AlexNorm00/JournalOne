package com.kassper.model;


import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class bd {

    static final private String DRIVER = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/JournalDB";
    private static final String user = "postgres";
    private static final String password = "1234";
    private static ResultSet rs = null;
    private static String personPassword = null;
    private static String personLogin = null;
    private static String qwerys;
    private static byte typeqwery = 0;

    public static void setQwerys(String qwerys) {
        bd.qwerys = qwerys;
    }

    private  static Connection con = null;
    public static Statement stm;
    public static ResultSet getRs() {
        return rs;
    }

    public static String getPersonLogin() {return personLogin;}
    public static String getPersonPassword() {return personPassword;}

    public static void passLog(String qwerys){
        bd.qwerys = qwerys;
        con();
        try {
            while (rs.next())
            {
                personLogin = rs.getString(6);
                personPassword = rs.getString(7);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void con() {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Не удалось зарегестрировать драйвер");
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(url, user, password);
            stm = con.createStatement();
            if (typeqwery == 1) {
                stm.executeUpdate(qwerys);
            }
            else rs = stm.executeQuery(qwerys);
        } catch (SQLException e) {
            System.out.println(e +"\n"+e.getSQLState()+"\n"+e.getErrorCode());
        }
    }

    public static void allClosed(){
        try {
            rs = null;
            stm = null;
            con.close();
        }
        catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void executeUppMethod (String qwerys){
        bd.qwerys = qwerys;
        con();
        typeqwery = 1;
    }


    public static ResultSet conResoultSet(String qwerys) {
        typeqwery = 0;
        bd.qwerys = qwerys;
        con();
        return rs;
    }
}

