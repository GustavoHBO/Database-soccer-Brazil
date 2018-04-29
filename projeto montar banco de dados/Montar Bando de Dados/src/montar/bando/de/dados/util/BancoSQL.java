/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montar.bando.de.dados.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gautstafr
 */
public class BancoSQL {

    public static String status = "Não conectou...";

    private static Connection conexao = null;

    private String mydatabase = "bdJogos";        //nome do seu banco de dados

//Método Construtor da Classe//
    public BancoSQL() {

    }

//Método de Conexão//
    public static java.sql.Connection getConexaoMySQL() throws SQLException {
        if (conexao == null) {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/bdJogos", "root", "mysql#root");
        }
        return conexao;
    }

    //Método que retorna o status da sua conexão//
    public static String statusConection() {
        return status;
    }

    //Método que fecha sua conexão//
    public static boolean FecharConexao() {
        try {
            BancoSQL.getConexaoMySQL().close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //Método que reinicia sua conexão//
    public static java.sql.Connection ReiniciarConexao() throws SQLException {
        FecharConexao();
        return BancoSQL.getConexaoMySQL();
    }

    //Método para criar o banco de dados
    public static void CriarBanco(String nomeBanco) throws SQLException {
        conexao = DriverManager.getConnection("jdbc:mysql://localhost", "root", "mysql#root");
        try {
            conexao.createStatement().execute("create database bdJogos");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/bdJogos", "root", "mysql#root");
        } catch (SQLException ex) {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/bdJogos", "root", "mysql#root");
        }
    }

    public static void inserirTime(String time) throws SQLException {
        conexao.createStatement().execute("use bdJogos");
        conexao.createStatement().execute("CREATE TABLE `clientes` (\n"
                + "2\n"
                + "  `idCliente` mediumint(8) unsigned NOT NULL auto_increment,\n"
                + "3\n"
                + "  `nomeEmpresa` varchar(255),\n"
                + "4\n"
                + "  `nomeDiretor` varchar(255) default NULL,\n"
                + "5\n"
                + "  `numEmpregados` mediumint default NULL,\n"
                + "6\n"
                + "  PRIMARY KEY (`idCliente`)\n"
                + "7\n"
                + ") AUTO_INCREMENT=1;");
    }
}
