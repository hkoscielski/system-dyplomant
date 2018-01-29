package com.example.hubson.systemdyplomant.view.subjects;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectListActivity extends AppCompatActivity implements SubjectListCallback {
    @BindView(R.id.subject_list)
    RecyclerView subjectRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        ButterKnife.bind(this);
        subjectRecyclerView.setAdapter(new SubjectListAdapter(this));
        subjectRecyclerView.setHasFixedSize(true);
        SubjectListViewModel subjectListViewModel = ViewModelProviders.of(this).get(SubjectListViewModel.class);

        subjectListViewModel.getGraduates().observe(this, graduates -> {
            if(graduates != null && graduates.data != null) {
                Log.i("SubjectListActivity", "Obserwujemy " + graduates.data.size() + " dyplomantów");
            }
        });
        subjectListViewModel.getSubjectsJoined().observe(this, subjects -> {
            if (subjects != null && subjects.data != null) {
                if (subjectRecyclerView.getAdapter() != null && subjectRecyclerView.getAdapter() instanceof SubjectListAdapter) {
                    ((SubjectListAdapter) subjectRecyclerView.getAdapter()).setData(subjects.data);
                }
            }
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

    @Override
    public void onSubjectClicked(SubjectJoined subjectJoined) {
        showChooseSubjectAlertDialog();
    }

    private void showChooseSubjectAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Wybór tematu")
                .setMessage("Czy na pewno chcesz wybrać ten temat do realizacji")
                .setCancelable(false)
                .setPositiveButton("Tak", (dialog, which) -> {

                })
                .setNegativeButton("Nie", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }
}
