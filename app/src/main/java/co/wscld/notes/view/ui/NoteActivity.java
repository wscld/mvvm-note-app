package co.wscld.notes.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import co.wscld.notes.BR;
import co.wscld.notes.R;
import co.wscld.notes.databinding.ActivityNoteBinding;
import co.wscld.notes.model.Note;
import co.wscld.notes.viewmodel.NoteViewModel;

public class NoteActivity extends AppCompatActivity {
    ActivityNoteBinding binding;
    Note note;
    NoteViewModel noteViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int id = getIntent().getIntExtra("id",0);
        if(id == 0){
           note = new Note();
        }
        binding = DataBindingUtil.setContentView(this,R.layout.activity_note);
        binding.setModel(note);
        noteViewModel = new NoteViewModel();

        binding.topAppBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.topAppBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if(noteViewModel.handleAction(item.getItemId(),note.getId())){
                    finish();
                }else{
                    Snackbar.make(binding.getRoot(),"Algo deu errado...", BaseTransientBottomBar.LENGTH_LONG).show();
                }
                return true;
            }
        });

        binding.extendedFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(noteViewModel.createOrSaveNote(note)){
                    finish();
                }else{
                    Snackbar.make(binding.getRoot(),"Algo deu errado...", BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }
        });

        noteViewModel.getNote(id).observe(this, new Observer<Note>() {
            @Override
            public void onChanged(Note _note) {
                if(_note != null){
                    note = _note;
                    binding.setModel(note);
                }
            }
        });
    }

    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.w("tag", "onTextChanged " + s);
    }
}
