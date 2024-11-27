import javax.swing.*;
import java.awt.*;

public class SokobanGUI {
    private JFrame ventana;
    private JPanel panelTablero;
    private JButton[][] botones;
    private Juego juego;

    public SokobanGUI() {
        juego = new Juego();
        inicializarVentana();
    }

    private void inicializarVentana() {
        ventana = new JFrame("Sokoban");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setLayout(new BorderLayout());

        panelTablero = new JPanel();
        actualizarTablero();
        ventana.add(panelTablero, BorderLayout.CENTER);

        JPanel panelControles = new JPanel();
        agregarControles(panelControles);
        ventana.add(panelControles, BorderLayout.SOUTH);

        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }

    private void actualizarTablero() {

        char[][] estadoTablero = juego.getTablero(); // Obtener el estado actual del tablero.
        panelTablero.removeAll();
        int filas = estadoTablero.length;
        int columnas = estadoTablero[0].length;
        panelTablero.setLayout(new GridLayout(filas, columnas));
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

                panelTablero.add(botones[i][j]);
            }
        }
        panelTablero.revalidate();
        panelTablero.repaint();
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
        JButton botonReiniciar = new JButton("Reiniciar");
        botonReiniciar.addActionListener(e -> reiniciarJuego());

        panel.add(botonIzquierda);
        panel.add(botonDerecha);
        panel.add(botonArriba);
        panel.add(botonAbajo);
        panel.add(botonReiniciar);
    }

    private void procesarMovimientoJugador(String direccion) {
        if (!juego.moverJugador(direccion)) {
            JOptionPane.showMessageDialog(ventana, "Movimiento inválido. Intenta nuevamente.");
        }
        actualizarTablero();
        if (juego.esVictoria()) {
            JOptionPane.showMessageDialog(ventana, "¡Felicidades! Has ganado el juego.");
            reiniciarJuego();
        }
    }

    private void reiniciarJuego() {
        juego = new Juego(); // Crear un nuevo juego
        actualizarTablero();
    }
}

