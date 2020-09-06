package com.example.androidbasics.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.androidbasics.R;
import com.example.androidbasics.downloadable.CourseInfo;
import com.example.androidbasics.downloadable.DataManager;
import com.example.androidbasics.downloadable.NoteInfo;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_POSITION = "NOTE_INFO.com.example.androidbasics.NOTE_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private NoteInfo mNoteInfo;
    private boolean mIsDisplayNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get spinner
        Spinner spinner = findViewById(R.id.spinner);

        // get courses
        List<CourseInfo> courseInfoList = DataManager.getInstance().getCourses();

        // set spinner item to adapter array
        ArrayAdapter<CourseInfo> courseInfoArrayAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, courseInfoList);

        // set DropDownViewResource to courseInfoArrayAdapter
        courseInfoArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // set adapter to spinner
        spinner.setAdapter(courseInfoArrayAdapter);
        
        readDisplayStateValue();

        EditText noteTitle = findViewById(R.id.noteTitle);
        EditText noteText = findViewById(R.id.noteText);

        if(!mIsDisplayNote) {
            displayStateValue(spinner, noteTitle, noteText);
        }
    }

    private void displayStateValue(Spinner spinner, EditText noteTitle, EditText noteText) {
        List<CourseInfo> courseInfos = DataManager.getInstance().getCourses();
        int courseIndex = courseInfos.indexOf(mNoteInfo.getCourse());

        spinner.setSelection(courseIndex);
        noteTitle.setText(mNoteInfo.getTitle());
        noteText.setText(mNoteInfo.getText());

    }


    private void readDisplayStateValue() {
        Intent intent = getIntent();
        int position  = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET);

        mIsDisplayNote = position == POSITION_NOT_SET;

        if(!mIsDisplayNote){
            mNoteInfo = DataManager.getInstance().getNotes().get(position);
        }
    }
}