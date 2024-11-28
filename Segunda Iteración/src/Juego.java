class Juego implements Runnable {
    private Mapa mapa;
    private Jugador jugador;
    private boolean enEjecucion;
    private String rutaArchivo;

    public Juego(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
        cargarJuego();
    }

    private void cargarJuego() {
        mapa = new Mapa(rutaArchivo);
        jugador = new Jugador(1, 3); // Posición inicial del jugador
        mapa.colocarJugador(jugador);
        enEjecucion = false;
    }

    @Override
    public void run() {
        while (enEjecucion) {
            // Lógica del juego aquí (por ejemplo, actualización del estado del juego)
            try {
                Thread.sleep(100); // Pausa para controlar la velocidad del bucle del juego
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void iniciarJuego() {
        if (!enEjecucion) {
            enEjecucion = true;
            new Thread(this).start();
        }
    }

    public void detenerJuego() {
        enEjecucion = false;
    }

    public void reiniciarJuego() {
        detenerJuego();
        cargarJuego();
        iniciarJuego();
    }

    public char[][] getMapa() {
        TipoCelda[][] estado = mapa.getEstadoMapa();
        char[][] mapa = new char[estado.length][estado[0].length];
        for (int i = 0; i < estado.length; i++) {
            for (int j = 0; j < estado[i].length; j++) {
                mapa[i][j] = estado[i][j].getSimbolo();
            }
        }
        return mapa;
    }

    public boolean moverJugador(String direccion) {
        return jugador.mover(direccion, mapa);
    }

    public boolean esVictoria() {
        return mapa.esVictoria();
    }
}