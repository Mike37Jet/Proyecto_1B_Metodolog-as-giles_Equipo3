package org.example;

import java.util.Scanner;
class Juego {
    private Mapa tablero;
    private Jugador jugador;
    private Scanner scanner;

    public Juego() {
        tablero = new Mapa();
        jugador = new Jugador(1, 3); // Posición inicial del jugador
        tablero.colocarJugador(jugador);
        scanner = new Scanner(System.in);
    }

    public char[][] getTablero() {
        return tablero.getEstadoMapa(); // Añade este método en la clase main.Tablero para devolver el estado.
    }

    public boolean moverJugador(String direccion) {
        return jugador.mover(direccion, tablero);
    }

    public boolean esVictoria() {
        return tablero.esVictoria();
    }
}
