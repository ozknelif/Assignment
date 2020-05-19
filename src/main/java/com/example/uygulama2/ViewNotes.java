package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.HashSet;

public class ViewNotes extends AppCompatActivity {
    static ArrayList<String> notes = new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Button createButton = findViewById(R.id.create_note_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            Intent createNoteIntent = new Intent(ViewNotes.this, CreateNote.class);
            startActivity(createNoteIntent);
            }
        });

        ListView listView = findViewById(R.id.listView_note);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.uygulama2",MODE_PRIVATE);

        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notes",null);
        if (set != null){
            notes = new ArrayList(set);
        }

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,notes);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent editorIntent = new Intent(ViewNotes.this, CreateNote.class);
                editorIntent.putExtra("noteId", i);
                startActivity(editorIntent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int delete, long id) {

                new AlertDialog.Builder(ViewNotes.this)
                        .setMessage("Delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                notes.remove(delete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.uygulama2",MODE_PRIVATE);

                                HashSet<String> set = new HashSet<>(ViewNotes.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }
}
