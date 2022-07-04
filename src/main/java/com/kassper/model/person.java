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
    private static Date yearOfBerth;
    private static String login = null;
    private static String password = null;
    private static int status;

    private static ResultSet rs;
    //Setters
    public static void setLogin(String login) {person.login = login;}
    public static void setPassword(String password) {person.password = password;}
    //Getters
    public static int getId() {return id;}
    public static String getSurname() {return surname;}
    public static String getName() {return name;}
    public static String getMiddleName() {return middleName;}
    public static Date getYearOfBerth() {return yearOfBerth;}
    public static String getLogin() {return login;}
    public static String getPassword() {return password;}
    public static int getStatus() {return status;}

    private static void allPersonOne(){
        try {
            person.id = rs.getInt(1);
            person.surname = rs.getString(2);
            person.name = rs.getString(3);
            person.middleName = rs.getString(4);
            person.yearOfBerth = rs.getDate(5);
            person.login = rs.getString(6);
            person.password = rs.getString(7);
            person.status = rs.getInt(8);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void passLog(String login, String password){
        String query = "select * from \"JournalDB\".\"person\"  where login = '"+login+"' and password = '"+password+"'";
        rs = bd.conResoultSet(query);
        try {
            while (rs.next())
            {
                allPersonOne();
                if (!person.login.equals(login) && !person.password.equals(password))
                {
                    person.login=null;
                    person.password=null;
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void addPerson(String surname, String name,
                                 String middleName, String login,
                                 String password, Date yersBerth) {
        int status = 1;
        if (surname != null && name != null && middleName != null &&
                login != null && password != null && yersBerth !=null &&
                status >= 0) {
            System.out.println("2");
            String qwery = "select * " +
                    "from \"JournalDB\".\"person\" " +
                    "where login  = '" + login + "' and not password ='" + password + "'";
            rs = bd.conResoultSet(qwery);
            System.out.println("3");
            try {
                while (rs.next()) {
                    allPersonOne();
                }
                System.out.println("4");
                if (!rs.wasNull()) {
                    qwery = "select * from \"JournalDB\".\"person\"";
                    bd.executeUppMethod(qwery);
                    int last = 0;
                    while (rs.next()) {
                        last = rs.getInt(1);
                        System.out.println("5");
                    }

                    qwery = "insert into \"JournalDB\".\"person\" values " +
                            "("+last+1+",'"+surname+"','"+name+"','"+middleName+"'," +
                            "'"+yersBerth+"','"+login+"','"+password+"',"+status+")";
                    bd.executeUppMethod(qwery);
                    System.out.println("7");
                } else {
                    System.out.println("Запись существует");
                }
                rs.close();
                rs = null;
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
