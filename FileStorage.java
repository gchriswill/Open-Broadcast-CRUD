package me.gchriswill.mdf3labone;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileStorage {

    private static final String TAG = "FileStorage";
    public static final String File = "persons_db";

    static public void addPersons(ArrayList<Person> persons, Context context){

        try {
            
            FileOutputStream fos = context.openFileOutput(File, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persons);
            oos.close();

        } catch (IOException e) {

            e.printStackTrace();

            Log.i(TAG, "addPersons: ---> Added multiple persons to file database");

        }

    }

    public static void addPerson(Person person, Context context) {

        ArrayList<Person> persons = getPersons(context);

        boolean check = false;

        for (Person p : persons) {

            boolean nameCheck = p.getName().equals(person.getName() );
            boolean lastNameCheck = p.getLastName().equals(person.getLastName() );
            boolean ageCheck = p.getAge() == person.getAge();

            boolean validate = ( nameCheck && lastNameCheck && ageCheck );

            if ( validate ){

                Toast.makeText(context, "Person is already saved!", Toast.LENGTH_SHORT).show();
                check = true;
                break;

            }

        }

        if (!check) {

            persons.add(person);
            Toast.makeText(context, "Person saved successfully...", Toast.LENGTH_SHORT).show();

        }

        try {

            FileOutputStream fos = context.openFileOutput(File, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(persons);
            oos.close();

        } catch (IOException e) {

            e.printStackTrace();

            Log.i(TAG, "addPerson: ---> Added a single person to file database");

        }

    }

    @SuppressWarnings("unchecked")
    public static ArrayList<Person> getPersons( Context context) {

        ArrayList<Person> persons = new ArrayList<>();

        try {

            FileInputStream fis = context.openFileInput(File);
            ObjectInputStream ois = new ObjectInputStream(fis);
            persons = (ArrayList<Person>) ois.readObject();

            ois.close();

        } catch (ClassNotFoundException | IOException e) {

            e.printStackTrace();

            Log.i(TAG, "getPerson: ---> Read multiple persons from file database");

        }

        return persons;

    }

    public static void deletePerson( Person person, Context context) {

        ArrayList<Person> persons = getPersons(context);

        int i = 0;
        for (Person p : persons) {

            boolean nameCheck = p.getName().equals(person.getName() );
            boolean lastNameCheck = p.getLastName().equals(person.getLastName() );
            boolean ageCheck = p.getAge() == person.getAge();

            boolean validate = ( nameCheck && lastNameCheck && ageCheck );

            if ( validate ){

                persons.remove(i);

                Toast.makeText(context, "Person deleted!", Toast.LENGTH_SHORT).show();

                break;

            }

            i++;

        }

        addPersons(persons, context);

        Log.i(TAG, "deletePerson: ---> Deleted single person from file database");

    }

}
