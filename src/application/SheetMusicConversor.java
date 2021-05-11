package application;

import entities.MusicNote;
import enums.Notation;
import enums.Note;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SheetMusicConversor {

    // Convert String to Note
    public static Note toNote(String note){
        Note myNote;
        
        switch (note){
            case "Do":
                myNote = Note.DO;
                break;
                
            case "Re":
                myNote = Note.RE;
                break;
                
            case "Mi":
                myNote = Note.MI;
                break;
                
            case "Fa":
                myNote = Note.FA;
                break;
            
            case "Sol":
                myNote = Note.SOL;
                break;
               
            case "La":
                myNote = Note.LA;
                break;
               
            case "Si":
                myNote = Note.SI;
                break;
               
            default: 
                myNote = null;
        }
        
        return myNote;
    }
    
    // Convert int to Note
    public static Note toNote(int note){
        Note myNote;
        
        switch (note){
            case 1:
                myNote = Note.DO;
                break;
                
            case 2:
                myNote = Note.RE;
                break;
                
            case 3:
                myNote = Note.MI;
                break;
                
            case 4:
                myNote = Note.FA;
                break;
            
            case 5:
                myNote = Note.SOL;
                break;
               
            case 6:
                myNote = Note.LA;
                break;
               
            case 7:
                myNote = Note.SI;
                break;
               
            default: 
                myNote = null;
        }
        
        return myNote;
    }
    
    
    // Convert String to Notation
    public static Notation toNotation(String notation){
        Notation myNotation;
        
        switch (notation){
            case "Semibreve":
                myNotation = Notation.SEMIBREVE;
                break;
                
            case "Minim":
                myNotation = Notation.MINIM;
                break;
                
            case "Crotchet":
                myNotation = Notation.CROTCHET;
                break;
                
            case "Quaver":
                myNotation = Notation.QUAVER;
                break;
            
            case "Semiquaver":
                myNotation = Notation.SEMIQUAVER;
                break;
               
            case "Demisemiquaver":
                myNotation = Notation.DEMISEMIQUAVER;
                break;
               
            default: 
                myNotation = null;
        }
        
        return myNotation;
    }
    
    // Convert String to Notation
    public static Notation toNotation(int notation){
        Notation myNotation = null;
        
        switch (notation){
            case 1:
                myNotation = Notation.SEMIBREVE;
                break;
                
            case 2:
                myNotation = Notation.MINIM;
                break;
                
            case 3:
                myNotation = Notation.CROTCHET;
                break;
                
            case 4:
                myNotation = Notation.QUAVER;
                break;
            
            case 5:
                myNotation = Notation.SEMIQUAVER;
                break;
               
            case 6:
                myNotation = Notation.DEMISEMIQUAVER;
                break;
               
            default: 
                myNotation = null;
        }
        
        return myNotation;
    }
    
    
    // Write the Arduíno code in file
    public static void writeCode(List<MusicNote> sheetMusic, String path, String name){
        List<String> sentences = constructSentences(sheetMusic);
        
        File file = new File(path);
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file.getParent() + "/" + name + ".ino"))) {
            for(String line : sentences){
                bw.write(line);
                bw.newLine();
            }         
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static List<String> constructSentences(List<MusicNote> sheetMusic){
        List<String> sentences = new ArrayList();
        
        sentences.add("#define DO  262");
        sentences.add("#define RE  294");
        sentences.add("#define MI  330");
        sentences.add("#define FA  349");
        sentences.add("#define SOL  392");
        sentences.add("#define LA  440");
        sentences.add("#define SI  494");
        sentences.add("");
        sentences.add("int pinBuzzer = 6;");
        sentences.add("");
        sentences.add("void setup() {");
        sentences.add("     pinMode(pinBuzzer,OUTPUT);");
        sentences.add("}");
        sentences.add("");
        sentences.add("void loop() {");
        
        
        for(MusicNote musicNote : sheetMusic){
            if(musicNote.isPause()){
                // Pause
                sentences.add("    noTone(pinBuzzer);");
                sentences.add(toDelayCode(musicNote.getNotation()));
                sentences.add("    delay(5);");
                sentences.add("");
            } 
            else {
                // Note
                sentences.add(toToneCode(musicNote.getNote()));
                sentences.add(toDelayCode(musicNote.getNotation()));
                sentences.add("    noTone(pinBuzzer);");
                sentences.add("    delay(5);");
                sentences.add("");
            }
        }
        
        sentences.add("    noTone(pinBuzzer);");
        sentences.add("    delay(10000);");
        sentences.add("}");
        
        return sentences;
    }
    
    private static String toToneCode(Note note){
        return "    tone(pinBuzzer, " + note.toString() + ");";
    }
    
    private static String toDelayCode(Notation notation){
        int delay = 0;
        
        if (notation == Notation.SEMIBREVE) {
            delay = 4000 - 5; // 4s
        } 
        else if (notation == Notation.MINIM) {
            delay = 2000 - 5; // 2s
        }
        else if (notation == Notation.CROTCHET) {
            delay = 1000 - 5; // 1s
        } 
        else if (notation == Notation.QUAVER) {
            delay = 500 - 5; // 0.5s
        }
        else if (notation == Notation.SEMIQUAVER) {
            delay = 250 - 5; // 0.25s
        }
        else if (notation == Notation.DEMISEMIQUAVER) {
            delay = 125 - 5; // 0.125s
        }
        
        return "    delay(" + delay + ");";
    }
}
