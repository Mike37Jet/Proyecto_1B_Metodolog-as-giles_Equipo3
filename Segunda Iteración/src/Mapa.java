class Mapa {
    private char[][] mapa;
    private final char VACIO = ' ';
    private final char PARED = '#';
    private final char OBJETIVO = '.';
    private final char CAJA = '$';
    private final char CAJA_SOBRE_OBJETIVO = '&';
    private final char JUGADOR = '*';
    private final char JUGADOR_SOBRE_OBJETIVO = '+';

    public Mapa() {
        mapa = new char[][]{
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



    public boolean puedeMover(int nuevoX, int nuevoY, int actualX, int actualY) {
        if (mapa[nuevoX][nuevoY] == PARED) return false; // Si el movimiento choca con una pared

        // Si el jugador intenta mover una caja
        if (mapa[nuevoX][nuevoY] == CAJA || mapa[nuevoX][nuevoY] == CAJA_SOBRE_OBJETIVO) {
            int direccionX = nuevoX - actualX;
            int direccionY = nuevoY - actualY;
            int cajaNuevaX = nuevoX + direccionX;
            int cajaNuevaY = nuevoY + direccionY;

            // Si la caja puede moverse
            if (mapa[cajaNuevaX][cajaNuevaY] == VACIO || mapa[cajaNuevaX][cajaNuevaY] == OBJETIVO) {
                // Actualiza la posición de la caja después del movimiento
                mapa[cajaNuevaX][cajaNuevaY] = (mapa[cajaNuevaX][cajaNuevaY] == OBJETIVO) ? CAJA_SOBRE_OBJETIVO : CAJA;

                // Si la caja estaba sobre un objetivo, el jugador ocupa ese objetivo; si no, queda vacío
                mapa[nuevoX][nuevoY] = (mapa[nuevoX][nuevoY] == CAJA_SOBRE_OBJETIVO) ? JUGADOR_SOBRE_OBJETIVO : JUGADOR;

                // Si el jugador estaba sobre un objetivo, restaura el objetivo; si no, queda vacío
                mapa[actualX][actualY] = (mapa[actualX][actualY] == JUGADOR_SOBRE_OBJETIVO) ? OBJETIVO : VACIO;

                return true;
            }
            return false;
        }

        // Si el jugador se mueve a una celda vacía o un objetivo
        if (mapa[nuevoX][nuevoY] == VACIO || mapa[nuevoX][nuevoY] == OBJETIVO) {
            mapa[nuevoX][nuevoY] = (mapa[nuevoX][nuevoY] == OBJETIVO) ? JUGADOR_SOBRE_OBJETIVO : JUGADOR;

            // Restaura el objetivo si el jugador estaba sobre él
            mapa[actualX][actualY] = (mapa[actualX][actualY] == JUGADOR_SOBRE_OBJETIVO) ? OBJETIVO : VACIO;

            return true;
        }

        return false;
    }




    public void colocarJugador(Jugador jugador) {
        System.out.println("Colocando jugador en: (" + jugador.getX() + ", " + jugador.getY() + ")");
            mapa[jugador.getX()][jugador.getY()] = JUGADOR;
    }

    public boolean esVictoria() {
        for (char[] fila : mapa) {
            for (char celda : fila) {
                if (celda == '$') return false; // Si queda una caja sin mover, no ganaste
            }
        }
        return true;
    }

    public char[][] getEstadoTablero() {
        return mapa;
    }
}