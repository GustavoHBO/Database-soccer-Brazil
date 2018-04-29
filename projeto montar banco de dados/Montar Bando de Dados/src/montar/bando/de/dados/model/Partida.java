/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package montar.bando.de.dados.model;

import java.util.Calendar;

/**
 *
 * @author gautstafr
 */
public class Partida {

    private Time timeCasa;
    private Time timeVisitante;
    private int golsTimeCasa;
    private int golsTimeVisitante;
    private int rodada;
    private Stadio stadio;
    private Calendar calendar;

    public Partida(){}
    
    public Partida(Time timeCasa, Time timeVisitante, int golsTimeCasa, int golsTimeVisitante, int rodada, Stadio stadio, Calendar calendar) {
        this.timeCasa = timeCasa;
        this.timeVisitante = timeVisitante;
        this.golsTimeCasa = golsTimeCasa;
        this.golsTimeVisitante = golsTimeVisitante;
        this.rodada = rodada;
        this.stadio = stadio;
        this.calendar = calendar;
    }

    /**
     * @return the timeCasa
     */
    public Time getTimeCasa() {
        return timeCasa;
    }

    /**
     * @param timeCasa the timeCasa to set
     */
    public void setTimeCasa(Time timeCasa) {
        this.timeCasa = timeCasa;
    }

    /**
     * @return the timeVisitante
     */
    public Time getTimeVisitante() {
        return timeVisitante;
    }

    /**
     * @param timeVisitante the timeVisitante to set
     */
    public void setTimeVisitante(Time timeVisitante) {
        this.timeVisitante = timeVisitante;
    }

    /**
     * @return the golTimeCasa
     */
    public int getGolsTimeCasa() {
        return golsTimeCasa;
    }

    /**
     * @param golTimeCasa the golTimeCasa to set
     */
    public void setGolsTimeCasa(int golTimeCasa) {
        this.golsTimeCasa = golTimeCasa;
    }

    /**
     * @return the golTimeVisitante
     */
    public int getGolsTimeVisitante() {
        return golsTimeVisitante;
    }

    /**
     * @param golTimeVisitante the golTimeVisitante to set
     */
    public void setGolsTimeVisitante(int golTimeVisitante) {
        this.golsTimeVisitante = golTimeVisitante;
    }

    /**
     * @return the rodada
     */
    public int getRodada() {
        return rodada;
    }

    /**
     * @param rodada the rodada to set
     */
    public void setRodada(int rodada) {
        this.rodada = rodada;
    }

    /**
     * @return the stadio
     */
    public Stadio getStadio() {
        return stadio;
    }

    /**
     * @param stadio the stadio to set
     */
    public void setStadio(Stadio stadio) {
        this.stadio = stadio;
    }

    /**
     * @return the calendar
     */
    public Calendar getCalendar() {
        return calendar;
    }

    /**
     * @param calendar the calendar to set
     */
    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }
}
