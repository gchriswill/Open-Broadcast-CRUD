package me.gchriswill.mdf3labone;

import java.io.Serializable;

public class Person implements Serializable {

    public static final String EXTRA_FIRST_NAME = "com.fullsail.android.EXTRA_FIRST_NAME";
    public static final String EXTRA_LAST_NAME = "com.fullsail.android.EXTRA_LAST_NAME";
    public static final String EXTRA_AGE = "com.fullsail.android.EXTRA_AGE";

    private String name;
    private String lastName;
    private int age;

    public Person(String name, String lastName, int age){

        this.name = name;
        this.lastName = lastName;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

}
