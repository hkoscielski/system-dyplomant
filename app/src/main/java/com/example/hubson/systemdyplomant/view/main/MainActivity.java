package com.example.hubson.systemdyplomant.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.utils.SessionManager;
import com.example.hubson.systemdyplomant.view.subjects.SubjectListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //SessionManager sessionManager = SessionManager.getInstance(this.getApplicationContext());
        //sessionManager.setLogin(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_account:
                //TODO akcja
                break;
            case R.id.item_about:
                //TODO akcja
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.card_subject_list)
    public void goToMySubject() {
        Intent intent = new Intent(this, SubjectListActivity.class);
        startActivity(intent);
    }
}
