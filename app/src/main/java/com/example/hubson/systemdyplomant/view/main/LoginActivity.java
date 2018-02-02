package com.example.hubson.systemdyplomant.view.main;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.Resource;
import com.example.hubson.systemdyplomant.repository.local.entity.Graduate;
import com.example.hubson.systemdyplomant.utils.InjectorUtils;
import com.example.hubson.systemdyplomant.utils.SessionManager;
import com.example.hubson.systemdyplomant.viewmodel.UserViewModel;
import com.example.hubson.systemdyplomant.viewmodel.UserViewModelFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_student_no_login)
    EditText etStudentNoLogin;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.pb_login_loading)
    ProgressBar pbLoginLoading;

    private UserViewModel userViewModel;
    private SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        sessionManager = SessionManager.getInstance(this.getApplicationContext());
        if (sessionManager.isLoggedIn()) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        UserViewModelFactory userViewModelFactory = InjectorUtils.provideUserViewModelFactory(this.getApplicationContext());
        userViewModel = ViewModelProviders.of(this, userViewModelFactory).get(UserViewModel.class);
        userViewModel.getGraduateByNo().observe(this, this::processResource);
    }

    @OnClick(R.id.btn_login)
    public void login() {
        String studentNo = etStudentNoLogin.getText().toString();
        String password = etPassword.getText().toString();

        if(!studentNo.isEmpty() && !password.isEmpty()) {
            observeGraduate(studentNo);
        }
    }

    private void observeGraduate(String studentNo) {
        userViewModel.setStudentNo(studentNo);
    }

    private void processResource(Resource<Graduate> resource) {
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
        pbLoginLoading.setVisibility(View.VISIBLE);
    }

    private void renderDataState(Graduate graduate) {
        pbLoginLoading.setVisibility(View.GONE);
        sessionManager.setLogin(true);
        sessionManager.setUserId(graduate.getIdGraduate());
        Log.e("id_graduate", String.valueOf(graduate.getIdGraduate()));
        Log.e("id_subject", String.valueOf(graduate.getIdSubject()));
        Log.e("name", String.valueOf(graduate.getName()));
        Log.e("surname", String.valueOf(graduate.getSurname()));
        Log.e("student_no", String.valueOf(graduate.getStudentNo()));
        Log.e("speciality", String.valueOf(graduate.getSpeciality()));
        Log.e("id_form", String.valueOf(graduate.getIdForm()));
        Log.e("year_of_studies", String.valueOf(graduate.getYearOfStudies()));
        goToMainActivity();
    }

    private void renderErrorState() {
        pbLoginLoading.setVisibility(View.GONE);
        Toast.makeText(this, R.string.load_user_failed_text, Toast.LENGTH_SHORT).show();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
