package ar.com.playmedia;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

public class Main {

  static ArrayList<String> board = new ArrayList<String>();
  static Integer computerChoice = 0;
  static Integer spot = 0;
  static boolean validMove = false;
  static Scanner keyboard = new Scanner(System.in);
  static Integer counter = 0;
  static boolean boardExist = false;

  private static int getRandomNumber(int min, int max) {
    Random r = new Random();
    return r.nextInt((max - min) + 1) + min;
  }

  public static void createBoard() {
    for (int i = 0; i < 9; i++) {
      board.add("_ ");
      }
    boardExist = true;
  }
  
  public static void clearBoard() {
    for (int i = 0; i < 9; i++) {
      board.set(i, "_ ");
      }
  }

  public static void viewBoard() {
    System.out.println();
    System.out.print(board.get(0));
    System.out.print(board.get(1));
    System.out.println(board.get(2));
    System.out.print(board.get(3));
    System.out.print(board.get(4));
    System.out.println(board.get(5));
    System.out.print(board.get(6));
    System.out.print(board.get(7));
    System.out.println(board.get(8));
    System.out.println();
  }

  public static void computerTurn() {
    computerChoice = getRandomNumber(0, 8);
    if (board.get(computerChoice) == "_ ") {
      board.set(computerChoice, "o ");
      validMove = true;
    } else {
      validMove = false;
    }
  }

  public static void playerTurn() {
    System.out.print("Ingrese un casillero (0-8): ");
    spot = Integer.parseInt(keyboard.nextLine());
    if (spot < 0 || spot > 8) {
      System.out.println("Ingrese un casillero valido");
      validMove = false;
    } else if (board.get(spot) == "_ ") {
      board.set(spot, "x ");
      validMove = true;
    } else {
      System.out.println("Este casillero esta ocupado.");
      validMove = false;
    }
  }

  public static boolean checkWinner() {
    if (board.get(0) == "x " && board.get(1) == "x " && board.get(2) == "x "
        || board.get(3) == "x " && board.get(4) == "x " && board.get(5) == "x "
        || board.get(6) == "x " && board.get(7) == "x " && board.get(8) == "x "
        || board.get(0) == "x " && board.get(3) == "x " && board.get(6) == "x "
        || board.get(1) == "x " && board.get(4) == "x " && board.get(7) == "x "
        || board.get(2) == "x " && board.get(5) == "x " && board.get(8) == "x "
        || board.get(0) == "x " && board.get(4) == "x " && board.get(8) == "x "
        || board.get(6) == "x " && board.get(4) == "x " && board.get(2) == "x ") {
      return true;
    } else if (board.get(0) == "o " && board.get(1) == "o " && board.get(2) == "o "
        || board.get(3) == "o " && board.get(4) == "o " && board.get(5) == "o "
        || board.get(6) == "o " && board.get(7) == "o " && board.get(8) == "o "
        || board.get(0) == "o " && board.get(3) == "o " && board.get(6) == "o "
        || board.get(1) == "o " && board.get(4) == "o " && board.get(7) == "o "
        || board.get(2) == "o " && board.get(5) == "o " && board.get(8) == "o "
        || board.get(0) == "o " && board.get(4) == "o " && board.get(8) == "o "
        || board.get(6) == "o " && board.get(4) == "o " && board.get(2) == "o ") {
      return true;
    } else {
      return false;
    }
  }

  public static void ticTacToe() {
    do {

      do {
        playerTurn();
        viewBoard();
      } while (validMove == false);

      if (checkWinner() == true) {
        System.out.println("Ganaste!");
        break;
      }

      counter += 1;
      if (counter == 9) {
        System.out.println("Es un empate!");
        break;
      }

      System.out.println("Turno de la computadora: ");

      do {
        computerTurn();
      } while (validMove == false);

      viewBoard();
      counter += 1;
      if (counter == 9) {
        System.out.println("Es un empate!");
        break;
      }

      if (checkWinner() == true) {
        System.out.println("Perdiste");
        break;
      }
    } while (checkWinner() == false);
  }

  public static void main(String[] args) {
    
    Integer menu = 0;
    String replay = "";

    System.out.println();
    System.out.println("Bienvenido!");

    do {
      System.out.println();
      System.out.println("1. Jugar Tateti");
      System.out.println("2. Salir");
      System.out.println();
      System.out.print("Que le gustaria hacer? 1/2: ");
      menu = Integer.parseInt(keyboard.nextLine());
    } while (menu != 1 && menu != 2);

    if (menu == 1) {
      do {
        if (boardExist == false) {
          createBoard();
        } else {
          clearBoard();
          counter = 0;
        }

        viewBoard();

        ticTacToe();

        System.out.println("Quieres volver a jugar?");
        replay = keyboard.nextLine();
      } while (replay.equalsIgnoreCase("si") || replay.equalsIgnoreCase("s"));
    } else {
      System.out.println("Adios!");
    }
  }
}