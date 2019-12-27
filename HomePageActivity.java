package com.example.note_project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    FirebaseAuth FAuth;
    DatabaseReference DBRNoteBook;
    DatabaseReference DBRNote;
    LinearLayoutManager LMH;
    LinearLayoutManager LMV;

    List<Note> noteList  = new ArrayList<>();
    RecyclerView note_rv;
    NotesAdapter NotesAdapter ;
    List<Notebooks> notebookList = new ArrayList<>();
    RecyclerView notebook_rv;
    notebookAdapter NotebookAdapter ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        initData();
        notebook_rv.setLayoutManager(new LinearLayoutManager(this ,LinearLayoutManager.HORIZONTAL,false));
        notebook_rv.setAdapter(NotebookAdapter);
        String userId = FAuth.getUid();
        DBRNoteBook.child(userId).addValueEventListener(getNotebookFromDB());
       // notebook_rv.setAdapter(NotebookAdapter);
        note_rv.setLayoutManager(new LinearLayoutManager(this ));
        DBRNote.child(userId).addValueEventListener(getNotesFromDB());
        note_rv.setAdapter(NotesAdapter);

        //NotesAdapter = new NotesAdapter(this, noteList );
       // NotebookAdapter = new notebookAdapter(this, notebookList );

    }




    private void initData() {
       /* noteList . add(new Note("1","Trip to San fran" , "San fran was one of the coolest places i have ever been all the beautiful sightseeing and …" , "118/06/2"));
        noteList . add(new Note("2","Shopping list" , "So far, this is what we need for our tirp" , "18/06/12"));
        noteList . add(new Note("3","Trip Diary" , "One of my favorite places of all was the Golden Gate Bridge, it was by far the most …" , "18/06/12"));
        noteList . add(new Note("4","Souvenirs" , "So i should get these for friends:" , "18/06/12"));
        notebookList . add(new Notebooks("1","Weekend","notebook"));
        notebookList . add(new Notebooks("2","To-Do","notebook"));*/


        FAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DBRNoteBook = database.getReference("Category");
        DBRNote = database.getReference("Note");
        notebook_rv = findViewById(R.id.notebook_rv);
        LMH = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        NotebookAdapter = new notebookAdapter(this, new ArrayList<Notebooks>());
        note_rv = findViewById(R.id.notes_rv);
        NotesAdapter = new NotesAdapter(this, new ArrayList<Note>() );
        LMV = new LinearLayoutManager(this);
    }


    private ValueEventListener getNotebookFromDB() {
        ValueEventListener NotebookListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                notebookList.clear();
                NotebookAdapter.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String idNotebook = snapshot.getKey();
                    Notebooks notebooks = snapshot.getValue(Notebooks.class);
                    (notebooks).setId(idNotebook);
                    notebookList.add(notebooks);
                }
                NotebookAdapter.setNotebookList(notebookList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Category failed, log a message
                Log.w("HomePageActivity", "loadPost:onCancelled", databaseError.toException());
            }
        };
        return NotebookListener;
    }

    private ValueEventListener getNotesFromDB() {
        ValueEventListener notesListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                noteList.clear();
                NotesAdapter.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String idNote = snapshot.getKey();
                    Note note = snapshot.getValue(Note.class);
                    (note).setId(idNote);
                    noteList.add(note);
                }
                //showOrHideImgEmptyNotes();
                NotesAdapter.setNoteList(noteList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Category failed, log a message
                Log.w("HomePageActivity", "loadPost:onCancelled", databaseError.toException());
            }
        };
        return notesListener;
    }




    public void goCreateNote(View view) {
        Intent intent = new Intent(HomePageActivity.this , MainActivity.class);
        startActivity(intent);
        Toast.makeText(HomePageActivity.this, "Create Note Page.",
                Toast.LENGTH_SHORT).show();
    }

    public void goCreateNoteBook(View view) {
        Intent intent = new Intent(HomePageActivity.this , MainActivity.class);
        startActivity(intent);
        Toast.makeText(HomePageActivity.this, "Create NoteBook Page.",
                Toast.LENGTH_SHORT).show();
    }


    public void goEditcolor(View view) {
        Intent intent = new Intent(HomePageActivity.this , MainActivity.class);
        startActivity(intent);
        Toast.makeText(HomePageActivity.this, "Edit Color Page.",
                Toast.LENGTH_SHORT).show();
    }


    public void goNotebook(View view) {
        Intent intent = new Intent(HomePageActivity.this , MainActivity.class);
        startActivity(intent);
        Toast.makeText(HomePageActivity.this, "NoteBook Page.",
                Toast.LENGTH_SHORT).show();

    }

    public void goNote(View view) {
        Intent intent = new Intent(HomePageActivity.this , MainActivity.class);
        startActivity(intent);
        Toast.makeText(HomePageActivity.this, "Notes Page.",
                Toast.LENGTH_SHORT).show();

    }
    public void logout(View view) {
        FAuth.signOut();
        startActivity(new Intent(this, SigninActivity.class));
        finish();
        Toast.makeText(HomePageActivity.this, "Logout.",
                Toast.LENGTH_SHORT).show();
    }

}