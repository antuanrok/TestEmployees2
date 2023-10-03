package com.example.testemployees2.screens.persons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployees2.R;
import com.example.testemployees2.adapters.PersonAdapters;
import com.example.testemployees2.pojo.Person;

import java.util.ArrayList;
import java.util.List;


public class PersonListActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private PersonAdapters adapter;
    private PersonViewModel personViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        personViewModel = new ViewModelProvider(this).get(PersonViewModel.class);
        recyclerView = findViewById(R.id.recyclerViewPersons);
        adapter = new PersonAdapters();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPersons(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        personViewModel.getPersons().observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(List<Person> personList) {
                if (personList!=null){adapter.setPersons(personList);}
            }
        });
        personViewModel.getErrors().observe(this, new Observer<Throwable>() {
            @Override
            public void onChanged(Throwable throwable) {
                if (throwable != null) {
                    Toast.makeText(PersonListActivity.this, "ошибка получения данных: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    personViewModel.clearErrors();
                }
            }
        });
        personViewModel.loadData();
        /*Person person1 = new Person();
        person1.setName("иван");
        List<BirthPlace> birthPlaces1 = new ArrayList<>();
        BirthPlace birthPlace1 = new BirthPlace();
        birthPlace1.setValue("1");
        birthPlaces1.add(birthPlace1);
        BirthPlace birthPlace2 = new BirthPlace();
        birthPlace2.setValue("2");
        birthPlaces1.add(birthPlace2);
        BirthPlace birthPlace3 = new BirthPlace();
        birthPlace3.setValue("3");
        birthPlaces1.add(birthPlace3);
        person1.setBirthPlace(birthPlaces1);

        Person person2 = new Person();
        person2.setName("коля");
        List<BirthPlace> birthPlaces2 = new ArrayList<>();
        BirthPlace birthPlace11 = new BirthPlace();
        birthPlace11.setValue("11");
        birthPlaces2.add(birthPlace11);
        BirthPlace birthPlace22 = new BirthPlace();
        birthPlace22.setValue("22");
        birthPlaces2.add(birthPlace22);
        BirthPlace birthPlace33 = new BirthPlace();
        birthPlace33.setValue("33");
        birthPlaces2.add(birthPlace33);
        person2.setBirthPlace(birthPlaces2);

        Person person3 = new Person();
        person3.setName("дуся");

        List<BirthPlace> birthPlaces3 = new ArrayList<>();
        BirthPlace birthPlace111 = new BirthPlace();
        birthPlace111.setValue("111");
        birthPlaces3.add(birthPlace111);
        BirthPlace birthPlace222 = new BirthPlace();
        birthPlace222.setValue("222");
        birthPlaces3.add(birthPlace222);
        BirthPlace birthPlace333 = new BirthPlace();
        birthPlace333.setValue("333");
        birthPlaces3.add(birthPlace333);
        person3.setBirthPlace(birthPlaces3);

        List<Person> persons = new ArrayList<>();

        persons.add(person1);
        persons.add(person2);
        persons.add(person3);

        adapter.setPersons(persons);*/

    }

}