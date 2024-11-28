package org.example;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class MapaTest {
    private Mapa mapa;

    @Before
    public void setUp() {
        mapa = new Mapa("src/mapas/mapa.txt");
    }

    @Test
    public void testCargarMapaDesdeArchivo() {
        assertNotNull(mapa.getEstadoMapa());
    }

    @Test
    public void testPuedeMover() {
        boolean puedeMover = mapa.puedeMover(1, 1, 0, 0);
        assertFalse(puedeMover);
    }

    @Test
    public void testEsVictoria() {
        boolean victoria = mapa.esVictoria();
        assertFalse(victoria);
    }
}