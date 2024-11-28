package org.example;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class JugadorTest {
    private Jugador jugador;
    private Mapa mapa;

    @Before
    public void setUp() {
        jugador = new Jugador(1, 1);
        mapa = new Mapa("src/mapas/mapa.txt");
    }

    @Test
    public void testMover() {
        boolean moved = jugador.mover("S", mapa);
        assertTrue(moved);
    }

    @Test
    public void testGetX() {
        assertEquals(1, jugador.getX());
    }

    @Test
    public void testGetY() {
        assertEquals(1, jugador.getY());
    }
}
