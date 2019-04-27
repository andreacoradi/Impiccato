/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Andrea Coradi <andreacoradi30@gmail.com>
 */
public class Hangman {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, InterruptedException {
        GestoreParole parole = new GestoreParole();
        Impiccato impiccato = new Impiccato();
        Scanner s;
        boolean end = false;
        boolean canGoHarder = true;
        parole.openFile("italian");
        int vite = 5;
        int diff = 5;
        int min_len = 5;

        s = new Scanner(System.in);

        while (!end) {
            String parola = "";
            while (true) {
                ArrayList<String> zzz = parole.getParole(1, diff, min_len);
                if (!zzz.isEmpty()) {
                    parola = zzz.get(0);
                    break;
                }
                if (diff > 5 && min_len > 5) {
                    diff--;
                    min_len--;
                }

            }

            impiccato.setParola(parola);
            while (!impiccato.getCurrentGuess().equalsIgnoreCase(parola)) {
                Thread.sleep(500);
                clear();
                if (vite == 0) {
                    System.out.println("YOU HAVE LOST");
                    System.out.println("PAROLA: " + parola);
                    return;
                }
                System.out.println("Guess: " + impiccato.getCurrentGuess());
                System.out.println("Vite: " + vite);
                ArrayList<Character> lettere = impiccato.getLettere();
                if (!lettere.isEmpty()) {
                    System.out.println("Lettere già messe: " + lettere.toString());
                }
                System.out.print("Inserire lettera: ");
                String temp = s.next();
                if (temp.length() > 1) {
                    if (temp.equalsIgnoreCase(parola)) {
                        break;
                    } else {
                        vite--;
                        continue;
                    }
                }
                if (!impiccato.addLettera(temp.charAt(0))) {
                    System.out.println("WRONG!");
                    vite--;
                }
            }

            clear();
            System.out.println("YOU HAVE WON");
            System.out.println("PAROLA: " + parola);
            if (canGoHarder) {
                System.out.println("WANT TO CONTINUE AT HARDER DIFFICULTY? (y/n)");
                vite = 5;

                if (s.next().equalsIgnoreCase("y")) {
                    diff += 2;
                    min_len += 2;
                } else {
                    end = true;
                }
            } else {
                System.out.println("WANT TO CONTINUE? (y/n)");
                vite = 5;
                if (s.next().equalsIgnoreCase("n")) {
                    end = true;
                }

            }

        }

    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
