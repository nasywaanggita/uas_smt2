/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import view.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author ASUS
 */
public class Koneksi {
    private static Connection Koneksi;
    public static Connection getKoneksi(){
        if (Koneksi == null){
            try{
                String url ="jdbc:mysql://localhost:3306/moejib_bakery";
                String user ="root";
                String password = "";  
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                Koneksi = DriverManager.getConnection(url,user,password);
                System.out.println("Berhasil");
            }catch (Exception e){
                System.out.println("Error");
            }
        }
        return Koneksi;
    }
    public static void main(String args[]){
        getKoneksi();
    }
}
