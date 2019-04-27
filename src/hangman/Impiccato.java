/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangman;

import java.util.ArrayList;

/**
 *
 * @author Andrea Coradi <andreacoradi30@gmail.com>
 */
public class Impiccato {

    private String parola;
    private ArrayList<Character> currentGuess;
    private ArrayList<Character> lettere;

    public Impiccato() {
        this.parola = "";
        currentGuess = new ArrayList<>();
        lettere = new ArrayList<>();
    }

    public String getCurrentGuess() {
        String s = "";
        for (char c : currentGuess) {
            s += c;
        }
        return s;
    }

    public void setParola(String parola) {
        this.parola = parola;
        reset();
    }

    private void reset() {
        lettere = new ArrayList<>();
        currentGuess = new ArrayList<>();
        currentGuess.add(parola.charAt(0));
        for (int i = 1; i < parola.length() - 1; i++) {
            currentGuess.add('_');
        }
        currentGuess.add(parola.charAt(parola.length() - 1));
    }

    public int getErrori() {
        return lettere.size();
    }

    public ArrayList<Character> getLettere() {
        return lettere;
    }

    public boolean addLettera(char lettera) {
        // Return true perchè non voglio che tolga una vita
        if (!letteraValida(lettera)) {
            return true;
        }
        if (!lettere.contains(lettera)) {
            lettere.add(lettera);
            if (parola.indexOf(lettera) >= 1 && parola.indexOf(lettera) <= parola.length()) {
                for (int i = 1; i < parola.length() - 1; i++) {
                    if (parola.charAt(i) == lettera) {
                        currentGuess.set(i, lettera);
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private boolean letteraValida(Character c) {
        return c <= 'z' && c >= 'a';
    }

}
