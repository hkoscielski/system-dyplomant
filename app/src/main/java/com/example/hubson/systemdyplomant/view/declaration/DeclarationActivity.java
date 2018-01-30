package com.example.hubson.systemdyplomant.view.declaration;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.utils.InjectorUtils;
import com.example.hubson.systemdyplomant.viewmodel.DeclarationViewModel;
import com.example.hubson.systemdyplomant.viewmodel.DeclarationViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DeclarationActivity extends AppCompatActivity {

    @BindView(R.id.et_graduate_names)
    EditText etGraduateNames;

    @BindView(R.id.et_student_no)
    EditText etStudentNo;

    @BindView(R.id.et_speciality)
    EditText etSpeciality;

    @BindView(R.id.et_form)
    EditText etForm;

    @BindView(R.id.et_year)
    EditText etYear;

    @BindView(R.id.et_subject_pl)
    EditText etSubjectPl;

    @BindView(R.id.et_subject_en)
    EditText etSubjectEn;

    @BindView(R.id.et_language)
    EditText etLanguage;

    @BindView(R.id.et_supervisor_names)
    EditText etSupervisorNames;

    @BindView(R.id.et_purpose_range)
    EditText etPurposeRange;

    @BindView(R.id.et_short_desc)
    EditText etShortDesc;

    private static final String KEY_SUBJECT_ID = "key_subject_id";
    private static final String KEY_SUPERVISOR_ID = "key_supervisor_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);
        ButterKnife.bind(this);

        DeclarationViewModelFactory factory = InjectorUtils.provideDeclarationActivityViewModelFactory(this.getApplicationContext());
        DeclarationViewModel declarationViewModel = ViewModelProviders.of(this, factory).get(DeclarationViewModel.class);

        Intent intent = getIntent();
        int supervisorId = intent.getIntExtra(KEY_SUPERVISOR_ID, 0);
        int subjectId = intent.getIntExtra(KEY_SUBJECT_ID, 0);
        declarationViewModel.setIdSupervisor(supervisorId);
        declarationViewModel.getSupervisor().observe(this, supervisor -> {
            if(supervisor != null && supervisor.data != null) {
                etSupervisorNames.setText(supervisor.data.getAcademicTitle() + " " + supervisor.data.getName() + " " + supervisor.data.getSurname());
            }
        });
        declarationViewModel.setIdSubject(subjectId);
        declarationViewModel.getSubject().observe(this, subject -> {
            if(subject != null && subject.data != null) {
                etSubjectPl.setText(subject.data.getSubjectPl());
                etSubjectEn.setText(subject.data.getSubjectEn());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_declaration, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_send:
                //TODO akcja
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public static Intent newIntent(Context context, int subjectId, int supervisorId) {
        Intent intent = new Intent(context, DeclarationActivity.class);
        intent.putExtra(KEY_SUBJECT_ID, subjectId);
        intent.putExtra(KEY_SUPERVISOR_ID, supervisorId);
        return intent;
    }
}
