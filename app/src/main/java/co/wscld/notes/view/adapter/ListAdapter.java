package co.wscld.notes.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import co.wscld.notes.R;
import co.wscld.notes.databinding.ItemNoteBinding;
import co.wscld.notes.model.Note;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>{
    private List<Note> noteList;
    private OnNoteClick onNoteClick;


    public ListAdapter(){
        noteList = new ArrayList<>();
    }

    public void setNotes(List<Note> list){
        noteList = list;
    }

    public void onNoteClickListener(OnNoteClick onNoteClick){
        this.onNoteClick = onNoteClick;
    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemNoteBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_note,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, final int position) {
        holder.binding.setModel(noteList.get(position));
        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onNoteClick.onItemClick(noteList.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ItemNoteBinding binding;
        public ViewHolder(ItemNoteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
