package com.ejercicios.entregas;

import java.util.Random;
import java.util.Scanner;

public class Actividad04BatallaNaval {

    public static void main(String[] args) {
    	
        final int FILAS = 5;
        final int COLUMNAS = 5;
        
        final String VERDE = "\u001B[32m";		// Color del índice de letras del tablero.
        final String AMARILLO = "\u001B[33m";	// Color del índice de números del tablero.
        final String ROJO = "\u001B[31m";		// Color de los barcos hundidos.
        final String AZUL = "\u001B[34m";		// Color de los disparos al agua.
        final String CYAN = "\u001B[36m";  		// Lo voy a utilizar para marcar los barcos no hundidos.
        final String RESET = "\u001B[0m";

        int[][] tablero = new int[FILAS][COLUMNAS];			// Tablero sobre el que voy a poner los barcos.
        int[][] tableroVisible = new int[FILAS][COLUMNAS];	// Tablero que verá el usuario en el juego.

        Random random = new Random();						// Importo la clase Random para colocar los barcos al azar.
        int barcosRestantes = 3;

        while (barcosRestantes > 0) {
            int fila = random.nextInt(FILAS);
            int columna = random.nextInt(COLUMNAS);
            if (tablero[fila][columna] == 0) {
                tablero[fila][columna] = 1;
                barcosRestantes--;
            }
        }

        int intentos = 10;
        barcosRestantes = 3;
        Scanner entrada = new Scanner(System.in);

        System.out.println("¡Bienvenido a Batalla Naval!");
        System.out.println("Tienes " + intentos + " intentos para hundir los " + barcosRestantes + " barcos.");

        while (intentos > 0 && barcosRestantes > 0) {
            int filaElegida = -1;
            int valorColumna = -1;
            
         // Meto paso de validación por si el usuario no metiera un número correcto.

            do {
                System.out.println("Introduce la fila (un número entre el 1 y el 5):");
                if (entrada.hasNextInt()) {		
                    filaElegida = entrada.nextInt() - 1;
                    if (filaElegida < 0 || filaElegida >= FILAS) {
                        System.out.println("Por favor, elige un número entre 1 y 5.");
                    }
                } else {
                    System.out.println("Introduce un número entre el 1 y el 5.");
                    System.out.println("-------------------------------------------------------");
                    entrada.nextLine();
                }
            } while (filaElegida < 0 || filaElegida >= FILAS);
            
         // Meto paso de validación por si el usuario no metiera una letra correcta.

            do {
                System.out.println("Introduce la columna (Una letra entre la 'a' y la 'e'):");
                String input = entrada.next().toLowerCase();		// Por si lo introduce en mayúsuculas.
                if (input.length() == 1 && input.charAt(0) >= 'a' && input.charAt(0) <= 'e') {
                    valorColumna = input.charAt(0) - 'a';
                } else {
                    System.out.println("Introduce una letra entre la 'a' y la 'e'.");
                    System.out.println("-----------------------------------------------------------");
                }
            } while (valorColumna < 0 || valorColumna >= COLUMNAS);

            if (tableroVisible[filaElegida][valorColumna] != 0) {
                System.out.println("Elige otra casilla, ¡ya has disparado aquí!");
            } else if (tablero[filaElegida][valorColumna] == 1) {
                System.out.println("¡Tocado!");
                tablero[filaElegida][valorColumna] = -1;
                tableroVisible[filaElegida][valorColumna] = 1;
                barcosRestantes--;
            } else {
                System.out.println("Agua");
                tableroVisible[filaElegida][valorColumna] = -1;
            }

            // Muestro el tablero:
            
            System.out.print("  ");
            for (char letra = 'a'; letra < 'a' + COLUMNAS; letra++) {
                System.out.print(VERDE + letra + RESET + " ");
            }
            System.out.println();

            for (int filas = 0; filas < FILAS; filas++) {
                System.out.print(AMARILLO + (filas + 1) + RESET + " ");
                for (int columnas = 0; columnas < COLUMNAS; columnas++) {
                    if (tableroVisible[filas][columnas] == 1) {
                        System.out.print(ROJO + "X" + RESET + " ");
                    } else if (tableroVisible[filas][columnas] == -1) {
                        System.out.print(AZUL + "~" + RESET + " ");
                    } else {
                        System.out.print("0 ");
                    }
                }
                System.out.println();
            }

            intentos--;
            System.out.println("\n- Intentos restantes: " + intentos);
            System.out.println("- Barcos restantes: " + barcosRestantes);
            System.out.println("-------------------------------------");
        }

        if (barcosRestantes == 0) {
            System.out.println("¡Victoria! Hundiste todos los barcos.");
        } else {
            System.out.println("Derrota. Aquí estaban los barcos:");
            
         // Muestro el tablero inlcuyendo la posición de los barcos no hundidos:
            System.out.println("Barcos hundidos:" + ROJO + "X" + RESET);
            System.out.println("Barcos no hundidos:" + CYAN + "X" + RESET);
            
            System.out.print("  ");
            for (char letra = 'a'; letra < 'a' + COLUMNAS; letra++) {
                System.out.print(VERDE + letra + RESET + " ");
            }
            System.out.println();
            
            for (int filas = 0; filas < FILAS; filas++) {
                System.out.print(AMARILLO + (filas + 1) + RESET + " ");
                for (int columnas = 0; columnas < COLUMNAS; columnas++) {
                    if (tablero[filas][columnas] == 1) {
                        System.out.print(CYAN + "X" + RESET + " ");
                    } else if (tablero[filas][columnas] == -1) {
                        System.out.print(ROJO + "X" + RESET + " ");
                    } else if (tablero[filas][columnas] == 0) {
                        System.out.print(AZUL + "~" + RESET + " ");
                    } else {
                        System.out.print("0 ");
                    }
                }
                System.out.println();
                entrada.close();
            }
        }
    }
}
