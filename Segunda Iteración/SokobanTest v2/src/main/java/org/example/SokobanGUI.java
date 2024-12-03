package org.example;

import javax.swing.*;
import java.awt.*;

public class SokobanGUI {
    private JFrame ventana;
    private JPanel panelMapa;
    private JButton[][] botones;
    private Juego juego;
    private Thread juegoThread;

    public SokobanGUI() {
        inicializarVentana();
        juego = new Juego("src/mapas/mapa.txt");
        actualizarMapa();
        juegoThread = new Thread(juego);
        juego.iniciarJuego();
    }

    private void inicializarVentana() {
        ventana = new JFrame("Sokoban");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());
        // Definir el tamaño de la ventana
        ventana.setSize(280, 365); // Ancho de 800 píxeles y alto de 600 píxeles

        // Panel del tablero
        panelMapa = new JPanel();
        ventana.add(panelMapa, BorderLayout.CENTER);

        // Panel de controles
        JPanel panelControles = new JPanel();
        agregarControles(panelControles);
        ventana.add(panelControles, BorderLayout.SOUTH);

        // Botón de reinicio
        JButton botonReiniciar = new JButton("R");
        botonReiniciar.addActionListener(e -> {
            juego.reiniciarJuego();
            actualizarMapa();
        });
        panelControles.add(botonReiniciar);

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    public void actualizarMapa() {
        char[][] estadoTablero = juego.getMapa(); // Obtener el estado actual del tablero.
        panelMapa.removeAll();
        int filas = estadoTablero.length;
        int columnas = estadoTablero[0].length;
        panelMapa.setLayout(new GridLayout(filas, columnas));
        botones = new JButton[filas][columnas];

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                botones[i][j] = new JButton();
                //botones[i][j].setEnabled(false); // Los botones no serán interactivos.
                botones[i][j].setEnabled(true);
                // Establecer la imagen correspondiente.
                ImageIcon icono = obtenerIcono(estadoTablero[i][j]);
                botones[i][j].setIcon(icono);
                botones[i][j].setMargin(new Insets(0, 0, 0, 0));
                botones[i][j].setBorderPainted(false);
                botones[i][j].setFocusPainted(false);
                botones[i][j].setContentAreaFilled(false);  // Desactiva el fondo del botón

                panelMapa.add(botones[i][j]);
            }
        }
        panelMapa.revalidate();
        panelMapa.repaint();
    }

    // Método para obtener el icono basado en el carácter del tablero.
    private ImageIcon obtenerIcono(char tipo) {
        String rutaBase = "src/imagenes/";
        String rutaImagen;

        switch (tipo) {
            case '#':
                rutaImagen = rutaBase + "pared.png";
                break;
            case '*':
                rutaImagen = rutaBase + "jugador.png";
                break;
            case '+':
                rutaImagen = rutaBase + "jugador.png";
                break;
            case '$':
                rutaImagen = rutaBase + "caja.png";
                break;
            case '&':
                rutaImagen = rutaBase + "caja_objetivo.png";
                break;
            case '.':
                rutaImagen = rutaBase + "objetivo.png";
                break;
            case ' ':
            default:
                rutaImagen = "";
                break;
        }

        ImageIcon iconoOriginal = new ImageIcon(rutaImagen);
        Image imagenEscalada = iconoOriginal.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
        ImageIcon iconoEscalado = new ImageIcon(imagenEscalada);
        return iconoEscalado;
    }

    private void agregarControles(JPanel panel) {
        panel.setLayout(new GridLayout(1, 5));

        JButton botonArriba = new JButton("↑");
        botonArriba.addActionListener(e -> procesarMovimientoJugador("W"));
        JButton botonAbajo = new JButton("↓");
        botonAbajo.addActionListener(e -> procesarMovimientoJugador("S"));
        JButton botonIzquierda = new JButton("←");
        botonIzquierda.addActionListener(e -> procesarMovimientoJugador("A"));
        JButton botonDerecha = new JButton("→");
        botonDerecha.addActionListener(e -> procesarMovimientoJugador("D"));


        panel.add(botonIzquierda);
        panel.add(botonDerecha);
        panel.add(botonArriba);
        panel.add(botonAbajo);

    }

    private void procesarMovimientoJugador(String direccion) {
        if (!juego.moverJugador(direccion)) {
            JOptionPane.showMessageDialog(ventana, "Movimiento inválido. Intenta nuevamente.");
        }
        actualizarMapa();
        if (juego.esVictoria()) {
            JOptionPane.showMessageDialog(ventana, "¡Felicidades! Has ganado el juego.");
            juego.reiniciarJuego();
            actualizarMapa();
        }
    }

}