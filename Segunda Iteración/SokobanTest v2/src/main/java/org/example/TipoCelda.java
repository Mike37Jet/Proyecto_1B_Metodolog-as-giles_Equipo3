package org.example;

public enum TipoCelda {
    VACIO(' '),
    PARED('#'),
    OBJETIVO('.'),
    CAJA('$'),
    CAJA_SOBRE_OBJETIVO('&'),
    JUGADOR('*'),
    JUGADOR_SOBRE_OBJETIVO('+');

    private final char simbolo;

    TipoCelda(char simbolo) {
        this.simbolo = simbolo;
    }

    public char getSimbolo() {
        return simbolo;
    }

    public static TipoCelda desdeSimbolo(char simbolo) {
        for (TipoCelda tipo : values()) {
            if (tipo.getSimbolo() == simbolo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Símbolo desconocido: " + simbolo);
    }
}
