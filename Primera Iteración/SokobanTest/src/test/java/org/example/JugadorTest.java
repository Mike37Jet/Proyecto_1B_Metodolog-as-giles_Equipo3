package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JugadorTest {
    private Mapa mapa;
    private Jugador jugador;

    @BeforeEach
    void setUp() {
        mapa = new Mapa();
        jugador = new Jugador(7, 1);
        mapa.colocarJugador(jugador);
    }

    @Test
    void testMover_DosCajasAlMismoTiempo() {
        // Colocamos dos cajas en celdas adyacentes
        mapa.colocarCaja(7, 2); // Caja 1
        mapa.colocarCaja(7, 4); // Caja 2

        // El jugador mueve una caja
        assertTrue(jugador.mover("D", mapa));

        // Luego el jugador mueve la segunda caja
        assertFalse(jugador.mover("D", mapa)); // Mueve las dos cajas

        // Verificamos si la primera caja se movió, pero la segunda no
        assertEquals('$', mapa.getCelda(7, 3));
        assertEquals('$', mapa.getCelda(7, 4));

    }


    @Test
    void testMover_DireccionValida() {
        assertTrue(jugador.mover("D", mapa)); // Mover hacia izquierda
    }

    @Test
    void testMover_DireccionInvalida() {
        assertFalse(jugador.mover("Z", mapa)); // Dirección inválida
    }

    @Test
    void testMover_HaciaPared() {
        assertFalse(jugador.mover("A", mapa)); // Intentar moverse hacia una pared
    }
}

