package me.gchriswill.mdf3labone;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    InnerReceiver innerReceiver;
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();

        person = new Person(
                intent.getStringExtra(Person.EXTRA_FIRST_NAME),
                intent.getStringExtra(Person.EXTRA_LAST_NAME),
                intent.getIntExtra(Person.EXTRA_AGE, -1)
        );

        Log.i("TEST", "onResume: @@@" + person.getLastName() );

        DetailFragment detailFragmentScreen = (DetailFragment) getSupportFragmentManager()
                .findFragmentByTag(DetailFragment.TAG);

        if (detailFragmentScreen == null){

            detailFragmentScreen = DetailFragment.newInstanceOf( person );

        }

        getSupportFragmentManager().beginTransaction().replace(R.id.detail_container,
                detailFragmentScreen, DetailFragment.TAG).commit();

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

        getMenuInflater().inflate(R.menu.menu_detail, menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_delete) {

            Intent intent = new Intent(OuterReceiver.ACTION_DELETE_DATA);
            intent.putExtra(Person.EXTRA_FIRST_NAME, person.getName() );
            intent.putExtra(Person.EXTRA_LAST_NAME, person.getLastName() );
            intent.putExtra(Person.EXTRA_AGE, person.getAge());
            sendBroadcast(intent);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
