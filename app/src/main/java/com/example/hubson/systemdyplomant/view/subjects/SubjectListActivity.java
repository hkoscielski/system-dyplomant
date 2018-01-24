package com.example.hubson.systemdyplomant.view.subjects;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModel;

public class SubjectListActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        textView = (TextView) findViewById(R.id.text_test);
        SubjectListViewModel subjectListViewModel = ViewModelProviders.of(this).get(SubjectListViewModel.class);
        subjectListViewModel.getSubjects().observe(this, subjects -> {
            if(subjects != null && subjects.data != null)
                textView.setText("Tematów: " + subjects.data.size() + "\n\n" + subjects.data.toString());
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_subject_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_hide_taken:
                //TODO akcja
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
