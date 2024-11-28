package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JuegoTest {
    private Juego juego;

    private Thread juegoThread;

    @Before
    public void setUp() {
        juego = new Juego("src/mapas/mapa.txt");
        juegoThread = new Thread(juego);

    }

    @Test
    public void testIniciarJuego() {
        juegoThread.start();
        juego.iniciarJuego();
        assertTrue(juegoThread.isAlive());
    }

    @Test
    public void testReiniciarJuego() {
        juego.reiniciarJuego();
        assertFalse(juegoThread.isAlive());
    }

    @Test
    public void testMoverJugador() {
        boolean moved = juego.moverJugador("S");
        assertTrue(moved);
    }

    @Test
    public void testEsVictoria() {
        boolean victoria = juego.esVictoria();
        assertFalse(victoria);
    }
}