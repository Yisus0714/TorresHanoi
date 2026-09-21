package org.itson;

import java.util.Stack;

public class iterativo {


    public static long hanoiIterativo(int n, char origen, char auxiliar, char destino) {
        long totalMovimientos = (1L << n) - 1; // Fórmula 2^n - 1
        
        //Pilas
        Stack<Integer> src = new Stack<>();
        Stack<Integer> aux = new Stack<>();
        Stack<Integer> dest = new Stack<>();

        // Si n es par, intercambiamos las etiquetas de los postes auxiliar y destino
        char tempAux = auxiliar;
        char tempDest = destino;
        if (n % 2 == 0) {
            auxiliar = tempDest;
            destino = tempAux;
        }

        // Cargar los discos en el poste de origen (del más grande al más pequeño)
        for (int i = n; i >= 1; i--) {
            src.push(i);
        }

        // Ejecución de movimientos cíclicos
        for (long i = 1; i <= totalMovimientos; i++) {
            if (i % 3 == 1) {
                moverEntreDosPostes(src, dest, origen, destino);
            } else if (i % 3 == 2) {
                moverEntreDosPostes(src, aux, origen, auxiliar);
            } else if (i % 3 == 0) {
                moverEntreDosPostes(aux, dest, auxiliar, destino);
            }
        }

        return totalMovimientos;
    }

    private static void moverEntreDosPostes(Stack<Integer> a, Stack<Integer> b, char charA, char charB) {
        if (a.isEmpty()) {
            a.push(b.pop());
        } else if (b.isEmpty()) {
            b.push(a.pop());
        } else if (a.peek() > b.peek()) {
            a.push(b.pop());
        } else {
            b.push(a.pop());
        }
    }
    // ==========================================
    // MÉTODO PRUEBA (n = 1 a 30)
    // ==========================================
    public static void main(String[] args) {
        //Formateo de la tabla de los resultados
        System.out.printf("%-5s | %-15s | %-18s%n", "n", "Movimientos", "Tiempo Iter. (ms)");
        System.out.println("--------------------------------------------------");

        for (int n = 1; n <= 30; n++) {
            long inicioIter = System.nanoTime();
            long movsIter = hanoiIterativo(n, 'A', 'B', 'C');
            long finIter = System.nanoTime();
            double tiempoIterMs = (finIter - inicioIter) / 1e6;

            System.out.printf("%-5d | %-15d | %-18.4f%n", n, movsIter, tiempoIterMs);
        }
    }
}
