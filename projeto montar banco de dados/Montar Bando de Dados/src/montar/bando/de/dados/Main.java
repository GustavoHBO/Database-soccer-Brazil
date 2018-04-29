/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montar.bando.de.dados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import montar.bando.de.dados.controller.Controller;

/**
 *
 * @author gautstafr
 */
public class Main {
    
    Controller controller;
    
    public static void main(String[] args) throws SQLException {
        new Main().lerArquivo();
    }
    
    private void lerArquivo() throws SQLException{
        Connection conexao = DriverManager.getConnection("jdbc:mysql://localhost", "root", "mysql#root");
        conexao.createStatement().execute("create database bdJogos");
        System.out.println("Conectado!");
        conexao.close();
    }
}
