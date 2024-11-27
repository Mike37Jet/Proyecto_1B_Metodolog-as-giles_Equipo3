import java.util.Scanner;
class Juego {
    private Mapa mapa;
    private Jugador jugador;
    private Scanner scanner;

    public Juego() {
        mapa = new Mapa();
        jugador = new Jugador(1, 3); // Posición inicial del jugador
        mapa.colocarJugador(jugador);
        scanner = new Scanner(System.in);
    }

    public char[][] getTablero() {
        return mapa.getEstadoTablero(); // Añade este método en la clase Tablero para devolver el estado.
    }

    public boolean moverJugador(String direccion) {
        return jugador.mover(direccion, mapa);
    }

    public boolean esVictoria() {
        return mapa.esVictoria();
    }
}
