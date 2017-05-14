package me.gchriswill.mdf3labone;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class OuterReceiver extends BroadcastReceiver {

    public static String ACTION_SAVE_DATA = "com.fullsail.android.ACTION_SAVE_DATA";
    public static String ACTION_DELETE_DATA = "com.fullsail.android.ACTION_DELETE_DATA";

    @Override
    public void onReceive(Context context, Intent intent) {

        Person person = new Person(
                intent.getStringExtra(Person.EXTRA_FIRST_NAME),
                intent.getStringExtra(Person.EXTRA_LAST_NAME),
                intent.getIntExtra(Person.EXTRA_AGE, -1)
        );

        if (intent.getAction().equals(ACTION_SAVE_DATA) ) {

            FileStorage.addPerson(person, context);

        }

        if (intent.getAction().equals(ACTION_DELETE_DATA) ) {

            FileStorage.deletePerson(person, context);

        }

        Intent newIntent = new Intent(InnerReceiver.ACTION_UPDATE_LIST);
        context.sendBroadcast(newIntent);

    }

}
