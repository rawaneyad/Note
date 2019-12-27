package com.example.note_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class notebookAdapter extends RecyclerView.Adapter<notebookAdapter.NotebookVh>  {
    private Context context;
    private List<Notebooks> notebooks ;


    notebookAdapter(Context context, List<Notebooks> notebooks) {
        this.context = context;
        this.notebooks = notebooks;

    }

    
    @NonNull
    @Override
    public notebookAdapter.NotebookVh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_note ,parent , false );
        return new notebookAdapter.NotebookVh(view);
    }


    @Override
    public void onBindViewHolder(@NonNull NotebookVh holder, int position) {
     holder.setData(notebooks.get(position));
     // holder.name.setText(notebooks.get(position).getName());
    }
    void setNotebookList(List<Notebooks> Notebook) {
        this.notebooks.addAll(Notebook);
        notifyDataSetChanged();
    }
    void clear() {
        this.notebooks.clear();
        notifyDataSetChanged();
    }
     @Override
     public int getItemCount() {
         return notebooks.size();
     }

    class NotebookVh extends RecyclerView.ViewHolder {

        TextView name ;
        ImageView imgNotebook;
        NotebookVh(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            imgNotebook = itemView.findViewById(R.id.imgNotebook);


        }

        void setData(final Notebooks notebooks) {
            name.setText(notebooks.getName());
            //imgNotebook.setImageURI(notebooks.getImgNotebook());
        }



    }



    public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

        private int mCurrentPosition;

        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public void onBind(int position) {
            mCurrentPosition = position;
        }

        public int getCurrentPosition() {
            return mCurrentPosition;
        }
    }
}
