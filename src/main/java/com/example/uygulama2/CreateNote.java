package com.example.uygulama2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashSet;

public class CreateNote extends AppCompatActivity {
    private EditText newNote;
    private Button saveBttn;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);
        newNote = findViewById(R.id.newnote_edit);
        saveBttn = findViewById(R.id.save_note_button);
        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        if(noteId != -1) {
            newNote.setText(ViewNotes.notes.get(noteId));
        } else {
            ViewNotes.notes.add("");
            noteId = ViewNotes.notes.size()-1;
            ViewNotes.arrayAdapter.notifyDataSetChanged();
        }
        newNote.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ViewNotes.notes.set(noteId, String.valueOf(charSequence));
                ViewNotes.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.uygulama2",MODE_PRIVATE);

                HashSet<String> set = new HashSet<>(ViewNotes.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        saveBttn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });

    }




}
