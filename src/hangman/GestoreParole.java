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
import java.util.Collections;
import java.util.regex.Pattern;

/**
 *
 * @author Andrea Coradi <andreacoradi30@gmail.com>
 */
public class GestoreParole {

    private ArrayList<String> lista;

    public GestoreParole() {
        this.lista = new ArrayList<>();
    }

    /*
    Importante rimuovere parole con caratteri strani
    Cioè accentate e maiuscole
     */
    public void openFile(String fileName) throws FileNotFoundException, IOException {
        String s = "";
        BufferedReader br;
        br = new BufferedReader(new FileReader(fileName));
        while ((s = br.readLine()) != null) {
            lista.add(s);
        }
    }

    public int size() {
        return lista.size();
    }

    public ArrayList<String> getParole(int n) {
        ArrayList<String> temp;
        String s = "";
        temp = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int pos = (int) (Math.random() * lista.size());
            s = lista.get(pos);
            if (parolaValida(s)) {
                temp.add(lista.get(pos));
                lista.remove(pos);
            } else {
                i--;
            }
        }
        return temp;
    }

    private void mescola() {
        Collections.shuffle(lista);
    }

    public ArrayList<String> getParole(int n, int diff, int min_length) {
        ArrayList<String> temp;
        String s = "";
        temp = new ArrayList<>();
        mescola();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < lista.size(); j++) {
                s = lista.get(j);
                if (caratteriDiversi(s) >= diff && s.length() >= min_length && parolaValida(s)) {
                    temp.add(s);
                }
                lista.remove(j);
            }

        }
        return temp;
    }

    public int caratteriDiversi(String parola) {
        if (!parolaValida(parola)) {
            return -1;
        }
        ArrayList<Character> chars;
        chars = new ArrayList<>();
        for (int i = 0; i < parola.length(); i++) {
            if (!chars.contains(parola.charAt(i))) {
                chars.add(parola.charAt(i));
            }
        }
        return chars.size();
    }

    private boolean parolaValida(String parola) {
        //return !Pattern.matches(".*[àèéìíòóù'A-Z].*", parola);
        return !parola.contains("'") && Pattern.matches(".*[a-z]+", parola);
    }
}
