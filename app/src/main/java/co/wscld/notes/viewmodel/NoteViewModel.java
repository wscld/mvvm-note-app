package co.wscld.notes.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import co.wscld.notes.R;
import co.wscld.notes.data.local.DBNoteRepo;
import co.wscld.notes.model.Note;

public class NoteViewModel extends ViewModel {
    private DBNoteRepo noteRepo;
    private MutableLiveData<Note> mutableLiveData;

    public NoteViewModel(){
        mutableLiveData = new MutableLiveData<>();
        noteRepo = new DBNoteRepo();
    }

    public MutableLiveData<Note> getNote(int id){
        mutableLiveData.setValue(noteRepo.showNote(id));
        return mutableLiveData;
    }

    public boolean createOrSaveNote(Note _note){
        if(_note.getId() == 0 ){
            return noteRepo.createNote(new Note(_note.getTitle(),_note.getText()));
        }else{
            return noteRepo.updateNote(_note);
        }
    }

    public boolean handleAction(int menuId, int noteId){
        if(menuId == R.id.action_delete){
            return noteRepo.deleteNote(noteId);
        }
        return false;
    }
}
