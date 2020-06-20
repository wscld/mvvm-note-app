package co.wscld.notes.data.local;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import java.util.ArrayList;
import java.util.List;

import co.wscld.notes.model.Note;
import co.wscld.notes.util.LiveRealmResults;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DBNoteRepo implements NoteRepo {
    private Realm realm;

    public DBNoteRepo(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        this.realm = Realm.getInstance(config);
    }

    @Override
    public List<Note> listAllNotes() {
        ArrayList list = new ArrayList();
        RealmResults<Note> notes = realm.where(Note.class).findAll();
        list.addAll(realm.copyFromRealm(notes));
        return list;
    }

    @Override
    public Note showNote(int id) {
        Note note = realm.where(Note.class).equalTo("id",id).findFirst();
        if(note != null){
            return realm.copyFromRealm(note);
        }else{
            return null;
        }
    }

    @Override
    public boolean createNote(Note note) {
        try{
            realm.beginTransaction();
            realm.copyToRealm(note);
            realm.commitTransaction();
            return true;
        }catch (Error err){
            return false;
        }
    }

    @Override
    public boolean updateNote(Note note) {
        try{
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(note);
            realm.commitTransaction();
            return true;
        }catch (Error err){
            return false;
        }
    }

    @Override
    public boolean deleteNote(int id) {
        try{
            Note note = realm.where(Note.class).equalTo("id",id).findFirst();
            if(note != null){
                realm.beginTransaction();
                note.deleteFromRealm();
                realm.commitTransaction();
                return true;
            }
            return false;
        }catch (Error err){
            return false;
        }
    }
}
