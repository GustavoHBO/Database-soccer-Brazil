/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montar.bando.de.dados;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import montar.bando.de.dados.controller.Controller;
import montar.bando.de.dados.util.BancoSQL;

/**
 *
 * @author gautstafr
 */
public class MontarBanco extends Application {

    private static Controller controller;

    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Ler Arquivos");
        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                lerArquivo();
            }
        });

        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Montar Banco de dados");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        controller = Controller.getInstance();
        launch(args);
    }

    private void lerArquivo() {
        try {
            BancoSQL.CriarBanco("bdJogos");
            BancoSQL.inserirTime("oi");
        } catch (SQLException ex) {
            System.out.println(BancoSQL.statusConection());
            mostrarErro("Error", "Ocorreu um erro!", "O banco de dados não pode ser criado!");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Abrir arquivo(s)");
        fileChooser.getExtensionFilters().addAll(
                new ExtensionFilter("Arquivo de Texto", "*.txt"));
        File selectedFile = fileChooser.showOpenDialog(null);
        if (selectedFile != null) {
            try {
                if (controller.carregarDados(selectedFile.getAbsolutePath())) {
                    mostrarInformacao("Arquivo Carregado", "Sucesso!", "O arquivo foi carregado com sucesso!");
                }
            } catch (IOException ex) {
                System.out.println("Execption!");
            }
        }
    }

    private void mostrarInformacao(String titulo, String cabecario, String mensagem) {
        Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
        dialogoInfo.setTitle(titulo);
        dialogoInfo.setHeaderText(cabecario);
        dialogoInfo.setContentText(mensagem);
        dialogoInfo.showAndWait();
    }

    private void mostrarErro(String titulo, String cabecario, String mensagem) {
        Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
        dialogoInfo.setTitle(titulo);
        dialogoInfo.setHeaderText(cabecario);
        dialogoInfo.setContentText(mensagem);
        dialogoInfo.showAndWait();
    }
}
