import java.util.Scanner;
class Juego {
    private Tablero tablero;
    private Jugador jugador;
    private Scanner scanner;

    public Juego() {
        tablero = new Tablero();
        jugador = new Jugador(1, 3); // Posición inicial del jugador
        tablero.colocarJugador(jugador);
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("¡Bienvenido a Sokoban!");
        while (!tablero.esVictoria()) {
            tablero.mostrarTablero();
            System.out.print("Mueve al jugador (W/A/S/D): ");
            String movimiento = scanner.nextLine().toUpperCase();

            if (!jugador.mover(movimiento, tablero)) {
                System.out.println("Movimiento inválido. Intenta nuevamente.");
            }
        }
        tablero.mostrarTablero();
        System.out.println("¡Felicidades! Has ganado el juego.");
    }
}
