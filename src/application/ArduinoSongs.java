package application;

import entities.MusicNote;
import enums.Notation;
import enums.Note;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArduinoSongs {
    
    private static Scanner input = new Scanner(System.in);
    private static List<MusicNote> sheetMusic = new ArrayList();
        
    private static void clearScreen(){
        System.out.print("\033[H\033[2J");  
    }
    
    private static void note(){
        Note note = null;
        Notation notation = null;

        System.out.println("Creating NOTE");
        
        //System.out.println("[Do][Re][Mi][Fa][Sol][La][Si]");
        System.out.println();
        System.out.println("[1] Dó");
        System.out.println("[2] Ré");
        System.out.println("[3] Mi");
        System.out.println("[4] Fá");
        System.out.println("[5] Sol");
        System.out.println("[6] Lá");
        System.out.println("[7] Sí");
        System.out.print("Note: ");
        note = SheetMusicConversor.toNote(input.nextInt());
        input.nextLine();
        
        //System.out.println("[Semibreve][Minim][Crotchet][Quaver]\n"+ "[Semiquaver] [Demisemiquaver]");
        System.out.println();
        System.out.println("[1] Semibreve");
        System.out.println("[2] Minim");
        System.out.println("[3] Crontchet");
        System.out.println("[4] Quaver");
        System.out.println("[5] Semiquaver");
        System.out.println("[6] Demisemibreve");
        System.out.print("Notation: ");
        notation = SheetMusicConversor.toNotation(input.nextInt());
        input.nextLine();

        sheetMusic.add(new MusicNote(note, notation));
    }
    
    
    private static void pause(){
        Notation notation = null;

        System.out.println("Creating PAUSE");
        
        //System.out.println("[Semibreve][Minim][Crotchet][Quaver]\n"+ "[Semiquaver] [Demisemiquaver]");
        System.out.println();
        System.out.println("[1] Semibreve");
        System.out.println("[2] Minim");
        System.out.println("[3] Crontchet");
        System.out.println("[4] Quaver");
        System.out.println("[5] Semiquaver");
        System.out.println("[6] Demisemibreve");
        System.out.print("Notation: ");
        notation = SheetMusicConversor.toNotation(input.nextInt());
        input.nextLine();

        sheetMusic.add(new MusicNote(notation));
    }
    
    
    public static void main(String[] args) {
        char option = 'a'; 
        
        System.out.println("ARDUINO SONGS");
        System.out.println("Make songs with Arduino!");
        
        System.out.print("Enter a name to song: ");
        String name = input.nextLine();
        clearScreen();
        
        do {
            System.out.println("Do you want to add note or pause? [n/p]");
            System.out.print(">>> ");
            option = input.nextLine().charAt(0);
            
            clearScreen();
            if(option == 'n'){
                note();
            } 
            else {
                pause();
            }
            
            clearScreen();
            System.out.println("Do you want to add another music element? [y/n]");
            System.out.print(">>> ");
            option = input.nextLine().charAt(0);

            clearScreen();

            if(option != 'y'){
                break;
            }
        } while (true);
        
        System.out.print("Enter a path to save the code: ");
        String path = input.nextLine();
        clearScreen();
        
        System.out.println("Wait a minute...");
        SheetMusicConversor.writeCode(sheetMusic, path, name); 
    }
    
}
