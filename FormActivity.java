package me.gchriswill.mdf3labone;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

public class FormActivity extends AppCompatActivity {

    private InnerReceiver innerReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);

    }

    @Override
    protected void onResume() {
        super.onResume();

        FormFragment formFragmentScreen = (FormFragment) getSupportFragmentManager()
                .findFragmentByTag(FormFragment.TAG);

        if (formFragmentScreen == null) {

            formFragmentScreen = FormFragment.newInstanceOf();

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.form_container,
                formFragmentScreen, FormFragment.TAG).commit();

        IntentFilter filter = new IntentFilter(InnerReceiver.ACTION_UPDATE_LIST);
        innerReceiver = new InnerReceiver();
        registerReceiver(innerReceiver, filter);

    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(innerReceiver);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.memu_form, menu);

        return super.onCreateOptionsMenu(menu);

    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_save) {

            String name = ( (EditText) findViewById(R.id.name_edittext) ).getText().toString();
            String lName = ( (EditText) findViewById(R.id.lastname_edittext) ).getText().toString();
            String age = ( (EditText) findViewById(R.id.age_edittext) ).getText().toString();

            Intent intent = new Intent(OuterReceiver.ACTION_SAVE_DATA);
            intent.putExtra(Person.EXTRA_FIRST_NAME, name );
            intent.putExtra(Person.EXTRA_LAST_NAME, lName);
            intent.putExtra(Person.EXTRA_AGE, Integer.parseInt( age ) );

            sendBroadcast(intent);

            return true;

        }

        return super.onOptionsItemSelected(item);


    }
}
