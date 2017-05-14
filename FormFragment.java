package me.gchriswill.mdf3labone;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FormFragment extends Fragment {

    public static final String TAG = "FormFragment";

    public static FormFragment newInstanceOf() {

        return new FormFragment();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_form, container, false);

    }
}
