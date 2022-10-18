/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apoio;

import java.util.Calendar;

/**
 *
 * @author Douglas
 */
public class Hora {

    private int hora;
    private int minutos;
    private int segundos;

    public int getHora() {
        return hora;
    }

    public Hora(int hora, int minutos, int segundos) {
        this.hora = hora;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public Hora() {
        Calendar c = Calendar.getInstance();
        setHora(c.get(Calendar.HOUR_OF_DAY));
        setMinutos(c.get(Calendar.MINUTE));
        setSegundos(c.get(Calendar.SECOND));
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public int getMinutos() {
        return minutos;
    }

    public void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    public int getSegundos() {
        return segundos;
    }

    public void setSegundos(int segundos) {
        if (segundos >= 0) {
            this.segundos = segundos;
        }

    }

    public void incrementarSegundos(int valor) {
        incrementarMinutos(valor / 60);
        valor = segundos + valor % 60;
        if (valor < 0) {
            valor = 0;
        }
        setSegundos(valor);
    }

    public void incrementarMinutos(int valor) {
        incrementarHora(valor / 60);
        valor = minutos + valor % 60;
        if (valor < 0) {
            valor = 0;
        }
        setMinutos(valor);
    }

    public void incrementarHora(int valor) {
        valor = hora + valor;
        if (valor < 0) {
            valor = 0;
        }
        setHora(valor);
    }

    public static boolean validar(String h) {
        try {
            String[] horario = h.split(":");
            int hora = Integer.parseInt(horario[0]);
            int minutos = Integer.parseInt(horario[1]);
            int segundos = 0;
            if (horario.length == 3) {
                segundos = Integer.parseInt(horario[2]);
            }
            if (hora < 0 || minutos < 0 || segundos < 0 || hora > 23 || minutos > 59 || segundos > 59) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Hora criarHora(String h) {
        Hora nova = null;
        try {
            String[] horario = h.split(":");
            int hora = Integer.parseInt(horario[0]);
            int minutos = Integer.parseInt(horario[1]);
            int segundos = 0;
            if (horario.length == 3) {
                segundos = Integer.parseInt(horario[2]);
            }
            nova = new Hora(hora, minutos, segundos);

        } catch (Exception e) {

        }
        return nova;
    }

    public String horaFormatada() {
        String hora = this.hora + ":" + this.minutos;
        return hora;
    }

}
