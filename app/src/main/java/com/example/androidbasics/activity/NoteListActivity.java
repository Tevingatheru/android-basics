package com.example.androidbasics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.androidbasics.R;
import com.example.androidbasics.downloadable.DataManager;
import com.example.androidbasics.downloadable.NoteInfo;

import java.util.List;

public class NoteListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_note_list);
        FloatingActionButton fab = findViewById(R.id.fabNoteList);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NoteListActivity.this, NoteActivity.class));
            }
        });

        initializeDisplayContent();
    }

    private void initializeDisplayContent() {

        final ListView listView = findViewById(R.id.listNote);

        List<NoteInfo> noteInfoSet = DataManager.getInstance().getNotes();
        ArrayAdapter<NoteInfo> arrayAdapterNote = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, noteInfoSet);

        listView.setAdapter(arrayAdapterNote);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoteListActivity.this, NoteActivity.class);
//                NoteInfo noteInfo = (NoteInfo) listView.getItemAtPosition(position);
                intent.putExtra(NoteActivity.NOTE_POSITION, position);

                startActivity(intent);
            }
        });
    }
}