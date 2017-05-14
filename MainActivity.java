package me.gchriswill.mdf3labone;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainFragment.MainInterface {

    private static final String TAG = "MainActivity";

    private InnerReceiver innerReceiver;
    private ArrayList<Person> persons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();

        persons = FileStorage.getPersons(this);

        Log.i(TAG, "onResume: " + persons.size() );

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentByTag(MainFragment.TAG);

        if(mainFragment == null){

            mainFragment = MainFragment.newInstanceOf( persons );

        }else {

            mainFragment.setFragmentList();

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.main_container,
                mainFragment, MainFragment.TAG).commit();

        innerReceiver = new InnerReceiver();
        IntentFilter filter = new IntentFilter(InnerReceiver.ACTION_VIEW_DATA);
        registerReceiver(innerReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(innerReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {

            Intent intent = new Intent(this, FormActivity.class);
            startActivity(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public ArrayList<Person> getPersonList() {

        Log.i("TEST", "getPersonList: --------");

        return persons;

    }
}
