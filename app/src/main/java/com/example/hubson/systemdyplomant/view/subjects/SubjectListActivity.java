package com.example.hubson.systemdyplomant.view.subjects;

import android.app.SearchManager;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.remote.response_model.SubjectJoined;
import com.example.hubson.systemdyplomant.utils.InjectorUtils;
import com.example.hubson.systemdyplomant.utils.SessionManager;
import com.example.hubson.systemdyplomant.view.declaration.DeclarationActivity;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModel;
import com.example.hubson.systemdyplomant.viewmodel.SubjectListViewModelFactory;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SubjectListActivity extends AppCompatActivity implements SubjectListCallback {
    @BindView(R.id.subject_list)
    RecyclerView subjectRecyclerView;

    @BindView(R.id.pb_subject_list_loading)
    ProgressBar pbLoading;

    private SubjectListAdapter subjectListAdapter;
    private SearchView searchView;
    private Graduate graduate;
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

        subjectListViewModel.getGraduates().observe(this, graduates -> {});
        subjectListViewModel.setIdGraduate(SessionManager.getInstance(this.getApplicationContext()).getUserId());
        subjectListViewModel.getGraduate().observe(this, graduate -> {
            if(graduate != null && graduate.data != null) {
                this.graduate = graduate.data;
            }
        });
        subjectListViewModel.getSubjectsJoined().observe(this, this::processResource);
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

            case R.id.item_sort_subject_name:
                subjectListAdapter.sortBySubjectName();
                break;

            case R.id.item_sort_supervisor_surname:
                subjectListAdapter.sortBySupervisorSurname();
                break;

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
                    if(graduate.getIdSubject() == null) {
                        startActivity(DeclarationActivity.newIntent(this, subjectJoined.getIdSubject(), subjectJoined.getIdSupervisor()));
                    } else {
                        showToast(R.string.choose_subject_failed);
                    }
                })
                .setNegativeButton("Nie", (dialog, which) -> {})
                .create();
        alertDialog.show();
    }

    private void processResource(Resource<List<SubjectJoined>> resource) {
        switch (resource.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState(resource.data);
                break;

            case ERROR:
                renderErrorState();
                break;
        }
    }

    private void renderLoadingState() {
        subjectRecyclerView.setVisibility(View.GONE);
        pbLoading.setVisibility(View.VISIBLE);
    }

    private void renderDataState(List<SubjectJoined> subjectsJoined) {
        subjectRecyclerView.setVisibility(View.VISIBLE);
        pbLoading.setVisibility(View.GONE);
        subjectListAdapter.setData(subjectsJoined);
    }

    private void renderErrorState() {
        subjectRecyclerView.setVisibility(View.GONE);
        pbLoading.setVisibility(View.GONE);
        showToast(R.string.load_subject_list_failed_text);
    }

    private void showToast(int stringResId) {
        Toast.makeText(this, stringResId, Toast.LENGTH_SHORT).show();
    }
}
