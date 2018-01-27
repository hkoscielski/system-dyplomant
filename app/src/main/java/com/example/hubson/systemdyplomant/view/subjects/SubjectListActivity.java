package com.example.hubson.systemdyplomant.view.subjects;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModel;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectListActivity extends AppCompatActivity {
    @BindView(R.id.subject_list)
    RecyclerView subjectRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        ButterKnife.bind(this);
        subjectRecyclerView.setHasFixedSize(true);
        SubjectListViewModel subjectListViewModel = ViewModelProviders.of(this).get(SubjectListViewModel.class);
        subjectListViewModel.getSubjects().observe(this, subjects -> {
            if(subjects != null && subjects.data != null)
                subjectRecyclerView.setAdapter(new SubjectListAdapter(subjects.data));
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
