package org.example;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapaTest {
    private Mapa mapa;

    @BeforeEach
    void setUp() {
        mapa = new Mapa();
    }

    @Test
    void testPuedeMover_CeldaVacia() {
        assertTrue(mapa.puedeMover(1, 3, 1, 2)); // Moverse a una celda vacía
    }

    @Test
    void testPuedeMover_CeldaConPared() {
        assertFalse(mapa.puedeMover(0, 0, 1, 1)); // Intentar moverse hacia una pared
    }

    @Test
    void testPuedeMover_CeldaConCajaMovible() {
        assertTrue(mapa.puedeMover(2, 2, 2, 3)); // Caja movible
    }

    @Test
    void testPuedeMover_CeldaConCajaNoMovible() {
        assertFalse(mapa.puedeMover(2, 2, 1, 2)); // Caja bloqueada
    }

    @Test
    void testEsVictoria_Incompleto() {
        assertFalse(mapa.esVictoria()); // Al inicio no debería haber victoria
    }
}
