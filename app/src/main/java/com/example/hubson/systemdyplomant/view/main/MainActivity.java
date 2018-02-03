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

/**
 * Aktywność stanowiąca menu główne aplikacji.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Metoda cyklu życia aktywności wywoływana jako pierwsza przy tworzeniu aktywności.
     * @param savedInstanceState dane, które zostały zapisane w momencie zniszczenia aktywności
     *                           i mają zostać odtworzone przy ponownym utworzeniu aktywnośći
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    /**
     * Metoda wyświetlająca w aktywności menu określone plikiem xml.
     * @param menu menu, w którym zostaną umieszczone utworzone elementy menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Metoda określająca akcję dla kliknięcia na poszczególne elementy menu.
     * @param item element menu, który został kliknięty
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_logout:
                logout();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Metoda wywoływana w momencie przyciśnięcia przycisku wyboru przejścia do listy tematów.
     * Umożliwia przejście do listy tematów.
     */
    @OnClick(R.id.card_subject_list)
    public void goToMySubject() {
        Intent intent = new Intent(this, SubjectListActivity.class);
        startActivity(intent);
    }

    /**
     * Metoda wylogowywująca użytkownika z aplikacji a następnie przekierowująca do aktywności logowania.
     */
    private void logout() {
        SessionManager.getInstance(this.getApplicationContext()).setLogin(false);
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
