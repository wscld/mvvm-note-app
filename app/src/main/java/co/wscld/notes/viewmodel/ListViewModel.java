package co.wscld.notes.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import co.wscld.notes.data.local.DBNoteRepo;
import co.wscld.notes.model.Note;

public class ListViewModel extends ViewModel {
    private DBNoteRepo noteRepo;
    private MutableLiveData<List<Note>> mutableLiveData;
    private MutableLiveData<Boolean> mutableLiveIsEmpty;

    public ListViewModel(){
        mutableLiveData = new MutableLiveData<>();
        mutableLiveIsEmpty = new MutableLiveData<>();
        noteRepo = new DBNoteRepo();
    }

    public LiveData<List<Note>> getNotes(){
        mutableLiveData.setValue(noteRepo.listAllNotes());
        return mutableLiveData;
    }

    public LiveData<Boolean> isEmpty(){
        mutableLiveIsEmpty.setValue(noteRepo.listAllNotes().size() == 0);
        return mutableLiveIsEmpty;
    }

    public void updateNotes(){
        mutableLiveData.setValue(noteRepo.listAllNotes());
        mutableLiveIsEmpty.setValue(noteRepo.listAllNotes().size() == 0);
    }

}
