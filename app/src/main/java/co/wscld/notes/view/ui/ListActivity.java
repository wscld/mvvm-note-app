package co.wscld.notes.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.wscld.notes.R;
import co.wscld.notes.databinding.ActivityListBinding;
import co.wscld.notes.model.Note;
import co.wscld.notes.view.adapter.ListAdapter;
import co.wscld.notes.view.adapter.OnNoteClick;
import co.wscld.notes.viewmodel.ListViewModel;
import io.realm.Realm;

public class ListActivity extends AppCompatActivity {
    ListViewModel listViewModel;
    ListAdapter adapter;
    ActivityListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Realm.init(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        adapter = new ListAdapter();
        listViewModel = new ListViewModel();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        binding.extendedFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListActivity.this,NoteActivity.class);
                startActivity(intent);
            }
        });

        adapter.onNoteClickListener(new OnNoteClick() {
            @Override
            public void onItemClick(int id) {
                Intent intent = new Intent(ListActivity.this,NoteActivity.class);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        listViewModel.isEmpty().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    binding.image.setVisibility(View.VISIBLE);
                }else{
                    binding.image.setVisibility(View.GONE);
                }
            }
        });

        listViewModel.getNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                if(notes != null){
                    adapter.setNotes(notes);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }


    @BindingAdapter({"bind:imgUrl"})
    public static void setImage(ImageView imageView, String imgUrl) {
        System.out.println("yoo "+imgUrl);
        Glide.with(imageView.getContext()).load(imgUrl).into(imageView);
    }


    @Override
    protected void onResume() {
        super.onResume();
        listViewModel.updateNotes();
    }
}
