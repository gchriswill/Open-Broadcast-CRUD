package me.gchriswill.mdf3labone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

public class MainAdapter extends BaseAdapter {

    private ArrayList<Person> persons;
    private Context context;

    public MainAdapter(Context context, ArrayList<Person> persons){

        this.context = context;
        this.persons = persons;

    }

    @Override
    public int getCount() {
        return persons.size();
    }

    @Override
    public Object getItem(int position) {
        return persons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return persons.indexOf( persons.get(position) );
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Person item = (Person) getItem(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list_item, null);

        ( (TextView) convertView.findViewById(R.id.name_textview) ).setText(item.getName() );
        ( (TextView) convertView.findViewById(R.id.lastname_textview) ).setText(item.getLastName() );
        ( (TextView) convertView.findViewById(R.id.age_textview) ).setText( "" + item.getAge() );

        return convertView;

    }
}
