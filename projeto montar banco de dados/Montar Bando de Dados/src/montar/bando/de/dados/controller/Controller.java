/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montar.bando.de.dados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import montar.bando.de.dados.model.Partida;
import montar.bando.de.dados.model.Time;
import montar.bando.de.dados.model.Stadio;

/**
 *
 * @author gautstafr
 */
public class Controller {
    
    private final String CARACTERESEPARADOR = "\t";
    
    /* Singleton */
    private static Controller controller; // Declaração do Controller.
    
    /*Atributos*/
    private static int idTimes = 0;
    private static int idStadios = 0;
    
    private ArrayList<Time> listaTimes;
    private ArrayList<Stadio> listaStadios;
    private ArrayList<Partida> listaPartidas;
    
                                                /* Design Pattern Singleton */
    /**
     * The constructor is private for use the singleton
     */
    private Controller() {
        listaTimes = new ArrayList<>();
        listaStadios = new ArrayList<>();
        listaPartidas = new ArrayList<>();
    }

    /**
     * Return the instance of controller.
     *
     * @return controller - An instance.
     */
    public static Controller getInstance() {
        if (controller == null) {
            controller = new Controller();
        }
        return controller;
    }

    /**
     * Reset the controller.
     */
    public static void resetController() {
        controller = null;
    }

                                                        /* End Singleton */
    
                                                            /* Métodos Privados */
    private Time buscarTime(Time time){
        Iterator<Time> it = listaTimes.iterator();
        while(it.hasNext()){
            Time timeAux = it.next();
            if(timeAux.getNome().equals(time.getNome())||timeAux.getId() == time.getId()){
                return timeAux;
            }
        }
        return null;
    }
    
    private Stadio buscarStadio(Stadio stadio){
        Iterator<Stadio> it = listaStadios.iterator();
        while (it.hasNext()) {
            Stadio stadioAux = it.next();
            if(stadioAux.getNome().equals(stadio.getNome().toUpperCase())||stadio.getId() == stadioAux.getId()){
                return stadioAux;
            }
        }
        return null;
    }
    
    private Partida buscarPartida(Partida partida){
        Iterator<Partida> it = listaPartidas.iterator();
        while(it.hasNext()){
            Partida partidaAux = it.next();
            if((partida.getTimeCasa().getId() == partidaAux.getTimeCasa().getId() && partida.getTimeVisitante().getId() == partidaAux.getTimeVisitante().getId())){
                if(partida.getCalendar().compareTo(partidaAux.getCalendar()) == 0){
                    return partidaAux;
                }
            }
        }
        return null;
    }
    
                                                                /* Métodos Carragamento Dados */
    
    public boolean carregarDados(String url) throws FileNotFoundException, IOException{
        File file = new File(url);
        if(file.exists()){
            String linha;
            String[] linhaDividida;
            Time timeCasa, timeVisitante;
            Calendar calendar;
            Partida partida;
            Stadio stadio;
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            while(bufferReader.ready()){
                linha = bufferReader.readLine();
                linhaDividida = linha.split(CARACTERESEPARADOR);
                System.out.println(linha);
                
                timeCasa = cadastrarTime(linhaDividida[3]);
                timeVisitante = cadastrarTime(linhaDividida[7]);
                
                stadio = cadastrarStadio(linhaDividida[8]);
                
                partida = new Partida();
                partida.setRodada(Integer.parseInt(linhaDividida[0].substring(0, linhaDividida[0].indexOf("ª"))));
                if(!linhaDividida[2].trim().equals("")){
                    partida.setCalendar(new GregorianCalendar(Integer.parseInt(linhaDividida[1].split("/")[2]), Integer.parseInt(linhaDividida[1].split("/")[1]), Integer.parseInt(linhaDividida[1].split("/")[0]), Integer.parseInt(linhaDividida[2].substring(0, linhaDividida[2].indexOf("h"))), Integer.parseInt(linhaDividida[2].substring(linhaDividida[2].indexOf("h")+1, linhaDividida[2].indexOf("m")))));
                } else {
                    partida.setCalendar(new GregorianCalendar(Integer.parseInt(linhaDividida[1].split("/")[2]), Integer.parseInt(linhaDividida[1].split("/")[1]), Integer.parseInt(linhaDividida[1].split("/")[0])));
                }
                partida.setTimeCasa(timeCasa);
                partida.setTimeVisitante(timeVisitante);
                partida.setGolsTimeVisitante(Integer.parseInt(linhaDividida[6]));
                partida.setGolsTimeCasa(Integer.parseInt(linhaDividida[4]));
                partida.setStadio(stadio);
                cadastrarPartida(partida);
            }
            return true;
        } else {
            return false;
        }
    }
    
                                                            /* Métodos Públicos */
    public Time cadastrarTime(String nome){
        Time time = new Time(nome.toUpperCase(), idTimes);
        if(buscarTime(time) == null){
            idTimes++;
            listaTimes.add(time);
            return time;
        } else {
            return time;
        }
    }
    
    public Stadio cadastrarStadio(String nome){
        Stadio stadio = new Stadio (nome, idStadios);
        if(buscarStadio(stadio) == null){
            idStadios++;
            listaStadios.add(stadio);
            return stadio;
        } else{
            return stadio;
        }
    }
    
    public Partida cadastrarPartida(Partida partida) {
        if (buscarPartida(partida) == null) {
            listaPartidas.add(partida);
            return partida;
        } else {
            return partida;
        }
    }
}
