package me.gchriswill.mdf3labone;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainFragment extends ListFragment {

    public static final String TAG = "MainFragment";

    MainInterface mainInterface;

    public static MainFragment newInstanceOf(ArrayList<Person> persons) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSONS", persons);
        MainFragment mainFragment = new MainFragment();
        mainFragment.setArguments(bundle);

        return mainFragment;

    }

    public interface MainInterface { ArrayList<Person> getPersonList(); }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof MainInterface){

            mainInterface = (MainInterface) context;

        }else {

            throw new RuntimeException( context.toString() + " must implement MainInterface");

        }

    }

    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setListAdapter( new MainAdapter(getContext(),
                (ArrayList<Person>) getArguments().getSerializable("PERSONS") ) );

        setEmptyText("No person has been saved yet...");

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        String name = (String) ( (TextView) v.findViewById(R.id.name_textview) ).getText();
        String lName = (String) ( (TextView) v.findViewById(R.id.lastname_textview ) ).getText();
        String age = (String) ( (TextView) v.findViewById(R.id.age_textview) ).getText();

        Intent intent = new Intent(InnerReceiver.ACTION_VIEW_DATA);
        intent.putExtra(Person.EXTRA_FIRST_NAME, name);
        intent.putExtra(Person.EXTRA_LAST_NAME, lName);
        intent.putExtra(Person.EXTRA_AGE, Integer.parseInt(age) );

        getContext().sendBroadcast(intent);

        Log.i(TAG, "onListItemClick: click" + name + lName + age);

    }

    public void setFragmentList() {

        MainAdapter personAdapter = new MainAdapter(getContext(), mainInterface.getPersonList() );
        setListAdapter(personAdapter);

        Log.i("TEST", "setFragmentList: --------");

    }

}
