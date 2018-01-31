package com.example.hubson.systemdyplomant.view.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.hubson.systemdyplomant.R;
import com.example.hubson.systemdyplomant.repository.local.entity.Declaration;
import com.example.hubson.systemdyplomant.repository.remote.Webservice;
import com.example.hubson.systemdyplomant.repository.remote.WebserviceImpl;
import com.example.hubson.systemdyplomant.view.subjects.SubjectListActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Declaration declaration = new Declaration(3, 2, 5,"Polski", "Bedzie spoko", "Kozak temat", null, null, 2);
        Webservice webservice = WebserviceImpl.getInstance();

        webservice.createDeclaration(declaration).observe(this, declarationResponseApiResponse -> Toast.makeText(MainActivity.this, declarationResponseApiResponse != null ? String.valueOf(declarationResponseApiResponse.body.getSuccess()) : "null", Toast.LENGTH_LONG).show());

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
