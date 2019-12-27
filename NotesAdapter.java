package com.example.note_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteVh> {


    private Context context ;
    private List<Note> notes ;

    NotesAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public NoteVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_home ,parent , false );
        return new NoteVh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteVh holder, int position) {
        holder.setData(notes.get(position));
    }
    void setNoteList(List<Note> Note) {
        this.notes.addAll(Note);
        notifyDataSetChanged();
    }
    void clear() {
        this.notes.clear();
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }




    class NoteVh extends RecyclerView.ViewHolder{

        TextView note_title , note_desc  , note_date;
        NoteVh(@NonNull View itemView) {
            super(itemView);
            note_title = itemView.findViewById(R.id.note_title);
            note_desc = itemView.findViewById(R.id.note_desc);
            note_date = itemView.findViewById(R.id.note_date);

        }

        void setData(final Note note) {
            note_title.setText(note.getTitle());
            note_desc.setText(note.getNote());
            note_date.setText(note.getDate());


        }
    }
}
