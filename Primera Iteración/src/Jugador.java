class Jugador {
    private int x, y;

    public Jugador(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean mover(String direccion, Tablero tablero) {
        int nuevoX = x;
        int nuevoY = y;

        switch (direccion) {
            case "W": nuevoX--; break; // Arriba
            case "S": nuevoX++; break; // Abajo
            case "A": nuevoY--; break; // Izquierda
            case "D": nuevoY++; break; // Derecha
            default: return false;
        }

        if (tablero.puedeMover(nuevoX, nuevoY, x, y)) {
            x = nuevoX;
            y = nuevoY;
            return true;
        }
        return false;
    }
}