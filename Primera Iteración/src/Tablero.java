class Tablero {
    private char[][] tablero;
    private final char VACIO = ' ';
    private final char PARED = '#';
    private final char OBJETIVO = '.';
    private final char CAJA = '$';
    private final char CAJA_SOBRE_OBJETIVO = '&';
    private final char JUGADOR = '*';
    private final char JUGADOR_SOBRE_OBJETIVO = '+';

    public Tablero() {
        tablero = new char[][]{
                {' ',' ','#','#','#','#','#',' '},
                {'#','#','#',' ',' ',' ','#',' '},
                {'#','.','$',' ',' ','$','#',' '},
                {'#','#','#',' ','$',' ','#',' '},
                {'#','.','#','#','$',' ','#',' '},
                {'#',' ','#',' ','.',' ','#','#'},
                {'#','$',' ','.','$',' ','.','#'},
                {'#',' ',' ',' ','.',' ',' ','#'},
                {'#','#','#','#','#','#','#','#'}
        };
    }

    public void mostrarTablero() {
        System.out.println("Estado del tablero:");
        for (char[] fila : tablero) {
            for (char celda : fila) {
                System.out.print(celda);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean puedeMover(int nuevoX, int nuevoY, int actualX, int actualY) {
        if (tablero[nuevoX][nuevoY] == PARED) return false; // Si el movimiento choca con una pared

        // Si el jugador intenta mover una caja
        if (tablero[nuevoX][nuevoY] == CAJA || tablero[nuevoX][nuevoY] == CAJA_SOBRE_OBJETIVO) {
            int direccionX = nuevoX - actualX;
            int direccionY = nuevoY - actualY;
            int cajaNuevaX = nuevoX + direccionX;
            int cajaNuevaY = nuevoY + direccionY;

            // Si la caja puede moverse
            if (tablero[cajaNuevaX][cajaNuevaY] == VACIO || tablero[cajaNuevaX][cajaNuevaY] == OBJETIVO) {
                // Actualiza la posición de la caja después del movimiento
                tablero[cajaNuevaX][cajaNuevaY] = (tablero[cajaNuevaX][cajaNuevaY] == OBJETIVO) ? CAJA_SOBRE_OBJETIVO : CAJA;

                // Si la caja estaba sobre un objetivo, el jugador ocupa ese objetivo; si no, queda vacío
                tablero[nuevoX][nuevoY] = (tablero[nuevoX][nuevoY] == CAJA_SOBRE_OBJETIVO) ? JUGADOR_SOBRE_OBJETIVO : JUGADOR;

                // Si el jugador estaba sobre un objetivo, restaura el objetivo; si no, queda vacío
                tablero[actualX][actualY] = (tablero[actualX][actualY] == JUGADOR_SOBRE_OBJETIVO) ? OBJETIVO : VACIO;

                return true;
            }
            return false;
        }

        // Si el jugador se mueve a una celda vacía o un objetivo
        if (tablero[nuevoX][nuevoY] == VACIO || tablero[nuevoX][nuevoY] == OBJETIVO) {
            tablero[nuevoX][nuevoY] = (tablero[nuevoX][nuevoY] == OBJETIVO) ? JUGADOR_SOBRE_OBJETIVO : JUGADOR;

            // Restaura el objetivo si el jugador estaba sobre él
            tablero[actualX][actualY] = (tablero[actualX][actualY] == JUGADOR_SOBRE_OBJETIVO) ? OBJETIVO : VACIO;

            return true;
        }

        return false;
    }




    public void colocarJugador(Jugador jugador) {
        System.out.println("Colocando jugador en: (" + jugador.getX() + ", " + jugador.getY() + ")");
            tablero[jugador.getX()][jugador.getY()] = JUGADOR;
    }

    public boolean esVictoria() {
        for (char[] fila : tablero) {
            for (char celda : fila) {
                if (celda == '$') return false; // Si queda una caja sin mover, no ganaste
            }
        }
        return true;
    }
}