package ar.com.playmedia;

import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

class Main {

  static String[] words = { "auto", "teclado", "departamento", "pelicula" , "helicoptero", "computadora", "persona", "programa", "parlante", "conocimiento" };
  
  static char[] wordFound;
  static Integer maxErrors = 6;
  static Integer errors;
  static ArrayList<String> letters = new ArrayList<String>();
  static Random r = new Random();
  static String wordToFind;
  static Scanner keyboard = new Scanner(System.in);

  public static String nextWord() {
    return words[r.nextInt(words.length)];
  }

  public static void newGame() {
    errors = 0;
    letters.clear();
    wordToFind = nextWord();

    wordFound = new char[wordToFind.length()];

    for (int i = 0; i < wordFound.length; i++) {
      wordFound[i] = '_';
    }
    
  }

  public static boolean correctWord() {
    return wordToFind.contentEquals(new String(wordFound));
  }

  public static void enter (
    String l
    ) {
    if (!letters.contains(l)) {
      if (wordToFind.contains(l + "")) {
        int index = wordToFind.indexOf(l);

        while (index >= 0) {
          wordFound[index] = l.charAt(0);
          index = wordToFind.indexOf(l, index + 1);
        }
      } else {
        errors++;
      }
      letters.add(l);
    }
  }

  public static String wordFoundContent() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < wordFound.length; i++) {
      builder.append(wordFound[i]);

      if (i < wordFound.length - 1) {
        builder.append(" ");
      }
    }
    return builder.toString();
  }

  public static void play() {
    do {
      System.out.print("Escribi una letra: ");
      String letter = keyboard.nextLine();

      if (letter.length() > 1) {
        letter = letter.substring(0, 1);
      }

      enter(letter);

      System.out.println(wordFoundContent());

      if (correctWord()) {
        System.out.println("Ganaste");
        break;
      } else {
        System.out.println("Te quedan " + (maxErrors - errors) + " intentos");
      }
    } while (errors < maxErrors);

    if (errors == maxErrors) {
      System.out.println("Perdiste");
      System.out.println("La palabra a encontrar era " + wordToFind);
    }
  }

  public static void main(String[] args) {

    String replay = "";

    do {
      newGame();
      play();
      System.out.println("Desea volver a jugar? s/n: ");
      replay = keyboard.nextLine();
    } while (replay.equalsIgnoreCase("si") || replay.equalsIgnoreCase("s"));

    System.out.println("Adios!");
  }
}
