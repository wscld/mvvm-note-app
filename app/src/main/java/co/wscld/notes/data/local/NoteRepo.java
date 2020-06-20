package co.wscld.notes.data.local;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import co.wscld.notes.model.Note;

public interface NoteRepo {
    List<Note> listAllNotes();
    Note showNote(int id);
    boolean createNote(Note note);
    boolean updateNote(Note note);
    boolean deleteNote(int id);
}
