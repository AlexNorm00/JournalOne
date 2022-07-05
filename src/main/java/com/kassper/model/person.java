package com.kassper.model;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class person {
    private static int id;
    private static String surname;
    private static String name;
    private static String middleName;
    private static String yearOfBerth;
    private static String login = null;
    private static String password = null;
    private static int status;
    private static int nomGroup;

    private static ResultSet rs;

    //Setters
    public static void setLogin(String login) {
        person.login = login;
    }

    public static void setPassword(String password) {
        person.password = password;
    }

    //Getters
    public static int getId() {
        return id;
    }

    public static String getSurname() {
        return surname;
    }

    public static String getName() {
        return name;
    }

    public static String getMiddleName() {
        return middleName;
    }

    public static String getYearOfBerth() {
        return yearOfBerth;
    }

    public static String getLogin() {
        return login;
    }

    public static String getPassword() {
        return password;
    }

    public static int getStatus() {
        return status;
    }

    public static int getNomGroup() {
        return nomGroup;
    }

    private static void allPersonOne() {
        try {
            person.id = rs.getInt(1);
            person.surname = rs.getString(2);
            person.name = rs.getString(3);
            person.middleName = rs.getString(4);
            person.yearOfBerth = rs.getString(5);
            person.login = rs.getString(6);
            person.password = rs.getString(7);
            person.status = rs.getInt(8);
            person.nomGroup = rs.getInt(9);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void passLog(String login, String password) {
        String query = "select * from \"JournalDB\".\"person\"  where login = '" + login + "' and password = '" + password + "'";
        rs = bd.conResoultSet(query);
        try {
            while (rs.next()) {
                allPersonOne();
                if (!person.login.equals(login) && !person.password.equals(password)) {
                    person.login = null;
                    person.password = null;
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void addPerson(String surname, String name, String middleName, String login,
                                 String password, String yersBerth, String status, int nomGroup) {
        String qwery = "select login from \"JournalDB\".\"person\" where login  = '" + login + "'";
        rs = bd.conResoultSet(qwery);
        try {
            String logs=null;
            while (rs.next()) {
                logs = rs.getString(1);
            }
            System.out.println("+++++");
            if (logs==null) {
                System.out.println("+++++");
                qwery = "select * from \"JournalDB\".\"person\"";
                rs = bd.conResoultSet(qwery);
                System.out.println("+++++");
                int lastID = 0;
                while (rs.next()) {
                    System.out.println("+++++");
                    allPersonOne();
                    lastID++;
                }
                qwery = "insert into \"JournalDB\".\"person\" values ("+lastID+",'"+surname+"','"+name+"','"+middleName+
                                                                    "','"+yersBerth+"','"+login+"','"+password+"',"+status+","+nomGroup+")";
                System.out.println("+++++");
                rs = bd.conResoultSet(qwery);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static String allPersonOfJounal(int id) {
        String retStr = "Non";
        if (id != 0) {
            String qwery = "\"select * from \"JournalDB\".\"Person\" where id_person = " + id;
            rs = bd.conResoultSet(qwery);
            try {
                while (rs.next()) {
                    allPersonOne();
                    retStr = surname + " " + name + " " + middleName;
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return retStr;
    }


}
