package entities;

import enums.Notation;
import enums.Note;

public class MusicNote {
    private Note note;
    private Notation notation;
    private boolean pause;
    
    public MusicNote(Note note, Notation notation) {
        this.note = note;
        this.notation = notation;
        this.pause = false;
    }
    
    public MusicNote(Notation notation) {
        this.notation = notation;
        this.pause = true;
    }

    public boolean isPause() {
        return pause;
    }

    public Note getNote() {
        return note;
    }

    public Notation getNotation() {
        return notation;
    }
}
