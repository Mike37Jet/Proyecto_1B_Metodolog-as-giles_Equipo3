import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Mapa {
    private TipoCelda[][] mapa;

    public Mapa(String rutaArchivo) {
        cargarMapaDesdeArchivo(rutaArchivo);
    }

    private void cargarMapaDesdeArchivo(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            int filas = 0;
            int columnas = 0;

            // Primero, contar el número de filas y columnas
            while ((linea = br.readLine()) != null) {
                columnas = linea.length();
                filas++;
            }

            mapa = new TipoCelda[filas][columnas];

            // Reiniciar el BufferedReader
            br.close();
            try (BufferedReader br2 = new BufferedReader(new FileReader(rutaArchivo))) {
                int filaActual = 0;
                while ((linea = br2.readLine()) != null) {
                    for (int col = 0; col < columnas; col++) {
                        mapa[filaActual][col] = TipoCelda.desdeSimbolo(linea.charAt(col));
                    }
                    filaActual++;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Error al cargar el mapa desde el archivo: " + e.getMessage());
        }
    }

    public boolean puedeMover(int nuevoX, int nuevoY, int actualX, int actualY) {
        if (mapa[nuevoX][nuevoY] == TipoCelda.PARED) return false; // Si el movimiento choca con una pared

        // Si el jugador intenta mover una caja
        if (mapa[nuevoX][nuevoY] == TipoCelda.CAJA || mapa[nuevoX][nuevoY] == TipoCelda.CAJA_SOBRE_OBJETIVO) {
            int direccionX = nuevoX - actualX;
            int direccionY = nuevoY - actualY;
            int cajaNuevaX = nuevoX + direccionX;
            int cajaNuevaY = nuevoY + direccionY;

            // Si la caja puede moverse
            if (mapa[cajaNuevaX][cajaNuevaY] == TipoCelda.VACIO || mapa[cajaNuevaX][cajaNuevaY] == TipoCelda.OBJETIVO) {
                // Actualiza la posición de la caja después del movimiento
                mapa[cajaNuevaX][cajaNuevaY] = (mapa[cajaNuevaX][cajaNuevaY] == TipoCelda.OBJETIVO) ? TipoCelda.CAJA_SOBRE_OBJETIVO : TipoCelda.CAJA;

                // Si la caja estaba sobre un objetivo, el jugador ocupa ese objetivo; si no, queda vacío
                mapa[nuevoX][nuevoY] = (mapa[nuevoX][nuevoY] == TipoCelda.CAJA_SOBRE_OBJETIVO) ? TipoCelda.JUGADOR_SOBRE_OBJETIVO : TipoCelda.JUGADOR;

                // Si el jugador estaba sobre un objetivo, restaura el objetivo; si no, queda vacío
                mapa[actualX][actualY] = (mapa[actualX][actualY] == TipoCelda.JUGADOR_SOBRE_OBJETIVO) ? TipoCelda.OBJETIVO : TipoCelda.VACIO;

                return true;
            }
            return false;
        }

        // Si el jugador se mueve a una celda vacía o un objetivo
        if (mapa[nuevoX][nuevoY] == TipoCelda.VACIO || mapa[nuevoX][nuevoY] == TipoCelda.OBJETIVO) {
            mapa[nuevoX][nuevoY] = (mapa[nuevoX][nuevoY] == TipoCelda.OBJETIVO) ? TipoCelda.JUGADOR_SOBRE_OBJETIVO : TipoCelda.JUGADOR;

            // Restaura el objetivo si el jugador estaba sobre él
            mapa[actualX][actualY] = (mapa[actualX][actualY] == TipoCelda.JUGADOR_SOBRE_OBJETIVO) ? TipoCelda.OBJETIVO : TipoCelda.VACIO;

            return true;
        }

        return false;
    }

    public void colocarJugador(Jugador jugador) {
        System.out.println("Colocando jugador en: (" + jugador.getX() + ", " + jugador.getY() + ")");
        mapa[jugador.getX()][jugador.getY()] = TipoCelda.JUGADOR;
    }

    public boolean esVictoria() {
        for (TipoCelda[] fila : mapa) {
            for (TipoCelda celda : fila) {
                if (celda == TipoCelda.CAJA) return false; // Si queda una caja sin mover, no ganaste
            }
        }
        return true;
    }

    public TipoCelda[][] getEstadoMapa() {
        return mapa;
    }
}