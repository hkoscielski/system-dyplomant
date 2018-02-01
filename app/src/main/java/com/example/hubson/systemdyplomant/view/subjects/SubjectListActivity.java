package com.example.hubson.systemdyplomant.view.subjects;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;
import com.example.hubson.systemdyplomant.utils.InjectorUtils;
import com.example.hubson.systemdyplomant.view.declaration.DeclarationActivity;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModel;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectListActivity extends AppCompatActivity implements SubjectListCallback {
    @BindView(R.id.subject_list)
    RecyclerView subjectRecyclerView;

    private SubjectListAdapter subjectListAdapter;
    private SearchView searchView;

    private boolean takenUpHidden = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_list);
        ButterKnife.bind(this);
        subjectListAdapter = new SubjectListAdapter(this);
        subjectRecyclerView.setAdapter(subjectListAdapter);
        subjectRecyclerView.setHasFixedSize(true);

        SubjectListViewModelFactory factory = InjectorUtils.provideSubjectListActivityViewModelFactory(this.getApplicationContext());
        SubjectListViewModel subjectListViewModel = ViewModelProviders.of(this, factory).get(SubjectListViewModel.class);

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

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.item_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                subjectListAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                subjectListAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_search:
                return true;
            case R.id.item_hide_taken:
                if(takenUpHidden) {
                    takenUpHidden = false;
                    subjectListAdapter.showAllSubjects();
                } else {
                    takenUpHidden = true;
                    subjectListAdapter.hideTakenUp();
                }
                item.setChecked(takenUpHidden);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onSubjectClicked(SubjectJoined subjectJoined) {
        showChooseSubjectAlertDialog(subjectJoined);
    }

    private void showChooseSubjectAlertDialog(SubjectJoined subjectJoined) {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Wybór tematu")
                .setMessage("Czy na pewno chcesz wybrać ten temat do realizacji? Wybierasz temat: \"" + subjectJoined.getSubjectPl() + "\"")
                .setCancelable(false)
                .setPositiveButton("Tak", (dialog, which) -> {
                    startActivity(DeclarationActivity.newIntent(this, subjectJoined.getIdSubject(), subjectJoined.getIdSupervisor()));
                })
                .setNegativeButton("Nie", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }
}
