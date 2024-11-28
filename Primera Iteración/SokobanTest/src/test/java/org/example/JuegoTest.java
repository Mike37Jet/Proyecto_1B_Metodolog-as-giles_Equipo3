package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JuegoTest {
    private Juego juego;

    @BeforeEach
    void setUp() {
        juego = new Juego();
    }

    @Test
    void testEstadoInicialTablero() {
        char[][] estado = juego.getTablero();
        assertNotNull(estado); // El tablero no debe ser nulo
        // Pared izquierda
        assertEquals('#', estado[0][0]);
        assertEquals('#', estado[1][0]);
        assertEquals('#', estado[2][0]);
        assertEquals('#', estado[3][0]);
        assertEquals('#', estado[4][0]);
        assertEquals('#', estado[5][0]);
        assertEquals('#', estado[6][0]);
        assertEquals('#', estado[7][0]);
        assertEquals('#', estado[8][0]);

        // Pared inferior
        assertEquals('#', estado[8][1]);
        assertEquals('#', estado[8][2]);
        assertEquals('#', estado[8][3]);
        assertEquals('#', estado[8][4]);
        assertEquals('#', estado[8][5]);
        assertEquals('#', estado[8][6]);
        assertEquals('#', estado[8][7]);

        // Pared superior
        assertEquals('#', estado[0][1]);
        assertEquals('#', estado[0][2]);
        assertEquals('#', estado[0][3]);
        assertEquals('#', estado[0][4]);
        assertEquals('#', estado[0][5]);
        assertEquals('#', estado[0][6]);
        assertEquals('#', estado[0][7]);

        // Pared derecha
        assertEquals('#', estado[1][7]);
        assertEquals('#', estado[2][7]);
        assertEquals('#', estado[3][7]);
        assertEquals('#', estado[4][7]);
        assertEquals('#', estado[5][7]);
        assertEquals('#', estado[6][7]);
        assertEquals('#', estado[7][7]);
        assertEquals('#', estado[8][7]);
    }

    @Test
    void testMoverJugador() {
        assertTrue(juego.moverJugador("S")); // Mover hacia abajo
    }

    @Test
    void testVictoriaInicial() {
        assertFalse(juego.esVictoria()); // No debería haber victoria al inicio
    }
}

