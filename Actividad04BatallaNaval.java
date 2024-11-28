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
        final String CYAN = "\u001B[36m";  		// Lo voy a utilizar para marcar los barcos no hundidos-
        final String RESET = "\u001B[0m";
        final String ANCLA = "\u2693";

        int[][] tablero = new int[FILAS][COLUMNAS];			// Tablero sobre el que voy a poner los barcos.
        int[][] tableroVisible = new int[FILAS][COLUMNAS];	// Tablero que verá el usuario en el juego.

        Random random = new Random();						// Importo la clase Random para colocar los barcos al azar.
        int barcosRestantes = 3;

        while (barcosRestantes > 0) {
            int filaBarco = random.nextInt(FILAS);
            int columnaBarco = random.nextInt(COLUMNAS);
            if (tablero[filaBarco][columnaBarco] == 0) {
                tablero[filaBarco][columnaBarco] = 1;
                barcosRestantes--;
            }
        }

        int intentos = 10;
        barcosRestantes = 3;						// Determino los intentos y los barcos a hundir
        Scanner entrada = new Scanner(System.in);

        System.out.println("¡Vamos a jugar a " + CYAN + ANCLA + " Batalla Naval! " + ANCLA + RESET);
        System.out.println("\nTienes " + CYAN + intentos + RESET + " intentos para hundir los " + CYAN + barcosRestantes + RESET + " barcos.\n");

        while (intentos > 0 && barcosRestantes > 0) {
            int filaElegida = -1;			
            int valorColumna = -1;
            
         // Meto paso de validación por si el usuario no metiera un número correcto.

            do {
                System.out.println("\n    - Introduce la fila (un número entre el 1 y el 5):");
                
                if (entrada.hasNextInt()) {		
                    filaElegida = entrada.nextInt() - 1;
                    
                    if (filaElegida < 0 || filaElegida >= FILAS) {
                        System.out.println(ROJO  + "   ¡No puedo apuntar ahí!\n" + RESET);
                    } else {
                    	System.out.println(VERDE + "   ¡Perfecto! Apuntando a la fila  " + (filaElegida + 1) + RESET);
                    }
                } else {
                    System.out.println(ROJO  + "\n   ¡Eh!¡Eso no es un número!" + RESET);
                    System.out.println("\n -------------------------------------------------------");
                    entrada.nextLine();
                }
                
            } while (filaElegida < 0 || filaElegida >= FILAS);
            
         // Meto paso de validación por si el usuario no metiera una letra correcta.

            do {
                System.out.println("\n    - Ahora, introduce la columna (Una letra entre la 'a' y la 'e'):");
                String letraElegida = entrada.next().toLowerCase();		// Por si lo introduce en mayúsculas.
                if (letraElegida.length() == 1 && letraElegida.charAt(0) >= 'a' && letraElegida.charAt(0) <= 'e') {
                    valorColumna = letraElegida.charAt(0) - 'a';
                } else {
                    System.out.println(ROJO  + "   ¡Eso no me sirve!" + RESET);
                    System.out.println("\n -----------------------------------------------------------");
                }
                
            } while (valorColumna < 0 || valorColumna >= COLUMNAS);

            if (tableroVisible[filaElegida][valorColumna] != 0) {
                System.out.println(ROJO + "   Elige otra casilla, ¡ya has disparado aquí! \n" + RESET);
            } else if (tablero[filaElegida][valorColumna] == 1) {
                System.out.println("  -->  " + ROJO + "¡Tocado!" + RESET + "  <-- \n");
                tablero[filaElegida][valorColumna] = -1;
                tableroVisible[filaElegida][valorColumna] = 1;
                barcosRestantes--;
            } else {
                System.out.println("  -->  " + CYAN + "Agua" + RESET + "  <--  \n");
                tableroVisible[filaElegida][valorColumna] = -1;
            }

            // Muestro el tablero:
            
            System.out.print("  ");
            for (char letra = 'a'; letra < 'a' + COLUMNAS; letra++) {
                System.out.print(" " + VERDE + letra + RESET + " ");
            }
            System.out.println();

            for (int filas = 0; filas < FILAS; filas++) {
                System.out.print(AMARILLO + (filas + 1) + RESET + " ");
                for (int columnas = 0; columnas < COLUMNAS; columnas++) {
                    if (tableroVisible[filas][columnas] == 1) {
                        System.out.print(ROJO + " X" + RESET + " ");
                    } else if (tableroVisible[filas][columnas] == -1) {
                        System.out.print(AZUL + " ~" + RESET + " ");
                    } else {
                        System.out.print(" 0 ");
                    }
                }
                System.out.println();
            }

            intentos--;
            System.out.println("\n ----------------------------------------------------");
            System.out.printf("  " + CYAN + ANCLA + RESET + "%20s" + barcosRestantes + RESET + "%n", "Barcos restantes: ");
            System.out.printf("  " + CYAN + ANCLA + RESET + "%20s" + intentos + RESET + "%n", "  Intentos restantes: ");
            System.out.println(" ----------------------------------------------------");
        }

        if (barcosRestantes == 0) {
            System.out.println("\n  -->  " + VERDE + "¡Victoria!" + RESET + " Hundiste todos los barcos.  <--  \n");
        } else {
            System.out.println("\n  -->  " + ROJO + "Derrota." + RESET + " Aquí estaban los barcos:\n");
            
         // Muestro el tablero inlcuyendo la posición de los barcos no hundidos:
            
            System.out.println("       - Barcos hundidos:" + ROJO + " X" + RESET);
            System.out.println("       - Barcos no hundidos:" + CYAN + " X\n" + RESET);
            
            System.out.print("  ");
            for (char letra = 'a'; letra < 'a' + COLUMNAS; letra++) {
                System.out.printf(" " + VERDE + letra + RESET + " ");
            }
            System.out.println();
            
            for (int filas = 0; filas < FILAS; filas++) {
                System.out.printf(AMARILLO + (filas + 1) + RESET + " ");
                for (int columnas = 0; columnas < COLUMNAS; columnas++) {
                    if (tablero[filas][columnas] == 1) {
                        System.out.printf(CYAN + " X" + RESET + " ");
                    } else if (tablero[filas][columnas] == -1) {
                        System.out.printf(ROJO + " X" + RESET + " ");
                    } else if (tablero[filas][columnas] == 0) {
                        System.out.printf(AZUL + " ~" + RESET + " ");
                    } else {
                        System.out.printf("0 ");
                    }
                }
                System.out.println();
                entrada.close();
            }
        }
    }
}
