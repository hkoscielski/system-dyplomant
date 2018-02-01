package com.example.hubson.systemdyplomant.view.declaration;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.repository.local.entity.Subject;
import com.example.hubson.systemdyplomant.repository.local.entity.SubjectStatus;
import com.example.hubson.systemdyplomant.utils.InjectorUtils;
import com.example.hubson.systemdyplomant.view.main.MainActivity;
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

    private DeclarationViewModel declarationViewModel;
    private Subject subject;
    private SubjectStatus subjectStatus;
    private Graduate graduate;

    private int idDeclarationStatus;

    private static final String KEY_SUBJECT_ID = "key_subject_id";
    private static final String KEY_SUPERVISOR_ID = "key_supervisor_id";
    private static final int TEMP_ID_GRADUATE = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_declaration);
        ButterKnife.bind(this);
        setUpActionBar();

        DeclarationViewModelFactory factory = InjectorUtils.provideDeclarationActivityViewModelFactory(this.getApplicationContext());
        this.declarationViewModel = ViewModelProviders.of(this, factory).get(DeclarationViewModel.class);

        Intent intent = getIntent();
        int supervisorId = intent.getIntExtra(KEY_SUPERVISOR_ID, 0);
        int subjectId = intent.getIntExtra(KEY_SUBJECT_ID, 0);
        declarationViewModel.setIdSupervisor(supervisorId);
        declarationViewModel.getSupervisor().observe(this, supervisor -> {
            if(supervisor != null && supervisor.data != null) {
                etSupervisorNames.setText(String.format("%s %s %s", supervisor.data.getAcademicTitle(), supervisor.data.getName(), supervisor.data.getSurname()));
            }
        });
        declarationViewModel.setIdSubject(subjectId);
        declarationViewModel.getSubject().observe(this, subject -> {
            if(subject != null && subject.data != null) {
                this.subject = subject.data;
                etSubjectPl.setText(subject.data.getSubjectPl());
                etSubjectEn.setText(subject.data.getSubjectEn());
            }
        });
        declarationViewModel.setSubjectStatusName("Zajęty");
        declarationViewModel.getSubjectStatus().observe(this, status -> {
            if(status != null && status.data != null) {
                this.subjectStatus = status.data;
                Log.d("SubjectStatus", subjectStatus.getStatusName());
            }
        });
        declarationViewModel.setDeclarationStatusName("Złożona");
        declarationViewModel.getDeclarationStatus().observe(this, status -> {
            if(status != null && status.data != null) {
                this.idDeclarationStatus = status.data.getIdDeclarationStatus();
                Log.d("DeclarationStatus_name", String.valueOf(status.data.getStatusName()));
            }
        });
        declarationViewModel.setIdGraduate(TEMP_ID_GRADUATE);
        declarationViewModel.getGraduate().observe(this, graduate -> {
            if(graduate != null && graduate.data != null) {
                this.graduate = graduate.data;
                Log.d("Graduate_name_surname", String.format("%s %s", graduate.data.getName(), graduate.data.getSurname()));
                //Log.d("Graduate_speciality", String.format("%s", graduate.data.getSpeciality()));
                Log.d("Graduate_year", String.format("%s", graduate.data.getYearOfStudies()));
                etGraduateNames.setText(String.format("%s %s", graduate.data.getName(), graduate.data.getSurname()));
                etStudentNo.setText(graduate.data.getStudentNo());
                etSpeciality.setText(graduate.data.getSpeciality());
                etYear.setText(graduate.data.getYearOfStudies().toString());
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
            case R.id.home:
                showBackAlertDialog();
                break;
            case R.id.item_send:
                showSendDeclarationAlertDialog();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        showBackAlertDialog();
    }

    private void setUpActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null) {
            actionBar.setHomeButtonEnabled(true);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static Intent newIntent(Context context, int subjectId, int supervisorId) {
        Intent intent = new Intent(context, DeclarationActivity.class);
        intent.putExtra(KEY_SUBJECT_ID, subjectId);
        intent.putExtra(KEY_SUPERVISOR_ID, supervisorId);
        return intent;
    }

    private void showSendDeclarationAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Udostępnienie deklaracji")
                .setMessage("Czy na pewno chcesz udostępnić deklarację? Udostępniając ten dokument, deklarujesz chęć realizowanie pracy inżynierskiej o wybranym temacie.")
                .setCancelable(false)
                .setPositiveButton("Tak", (dialog, which) -> {
                    saveDeclaration();
                })
                .setNegativeButton("Nie", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }

    private void showBackAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("Rezygnuj z deklaracji")
                .setMessage("Czy na pewno chcesz zrezygnować z wypełnienia deklaracji? Pamiętaj, że wprowadzone dane nie zostaną zapamiętane.")
                .setCancelable(false)
                .setPositiveButton("Tak", (dialog, which) -> {
                    finish();
                })
                .setNegativeButton("Nie", (dialog, which) -> {

                })
                .create();
        alertDialog.show();
    }

    private void saveDeclaration() {
        int idSubject = getIntent().getIntExtra(KEY_SUBJECT_ID, 0);
        String language = etLanguage.getText().toString();
        String purposeRange = etPurposeRange.getText().toString();
        String shortDesc = etShortDesc.getText().toString();
        if(language.isEmpty() || purposeRange.isEmpty() || shortDesc.isEmpty()) {
            showToast("Nie udało się udostępnić deklaracji. Sprawdź czy wszystkie dane zostały wypełnione poprawnie");
            etLanguage.setText("");
            etPurposeRange.setText("");
            etShortDesc.setText("");
        } else {
            Declaration declaration = new Declaration(idSubject, TEMP_ID_GRADUATE, language, purposeRange, shortDesc, null, null, idDeclarationStatus);
            declarationViewModel.createDeclaration(declaration).observe(this, response -> {
                if(response != null && response.body != null) {
                    if(response.body.getSuccess()) {
                        subject.setTakenUp(subject.getTakenUp() + 1);
                        graduate.setIdSubject(subject.getIdSubject());
                        if(subject.getTakenUp() == subject.getLimit()) {
                            subject.setIdSubjectStatus(subjectStatus.getIdSubjectStatus());
                        }
                        declarationViewModel.updateSubject(subject).observe(this, responsee -> {
                            if(responsee != null && responsee.body != null) {
                                Log.e("update_subject", String.valueOf(responsee.body.getSuccess()));
                                Log.e("update_subject", String.valueOf(responsee.body.getMessage()));
                            } else {
                                Log.e("update_subject", "failed");
                            }
                        });
                        declarationViewModel.updateGraduate(graduate).observe(this, responsee -> {
                            if(responsee != null && responsee.body != null) {
                                Log.e("update_graduate", String.valueOf(responsee.body.getSuccess()));
                                Log.e("update_graduate", String.valueOf(responsee.body.getMessage()));
                            } else {
                                Log.e("update_graduate", "failed");
                            }
                        });
                        Intent intent = new Intent(this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        showToast("Deklaracja została przyjęta pomyślnie");
                    } else {
                        showToast("Nie udało się udostępnić deklaracji. Sprawdź czy wszystkie dane zostały wprowadzone poprawnie");
                    }
                }
            });
        }
    }

    public void showToast(String message) {
        Toast.makeText(this.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

