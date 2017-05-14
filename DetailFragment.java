package me.gchriswill.mdf3labone;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class DetailFragment extends Fragment {

    public static final String TAG = "DetailFragment";

    public static DetailFragment newInstanceOf(Person person) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("PERSON", person);
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.setArguments(bundle);

        return detailFragment;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_detail, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Activity act = getActivity();

        Bundle bundle = getArguments();
        Person person = (Person) bundle.getSerializable("PERSON");

                ((EditText) act.findViewById(R.id.name_fake)).setText( person.getName() );
        ( (EditText) act.findViewById(R.id.lastname_fake) ).setText( person.getLastName() );
        ( (EditText) act.findViewById(R.id.age_fake) ).setText( "" + person.getAge() );

    }
}
