package com.example.testemployees2.screens.persons;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployees2.R;
import com.example.testemployees2.adapters.PersonAdapters;
import com.example.testemployees2.api.ApiFactory;
import com.example.testemployees2.api.ApiService;
import com.example.testemployees2.pojo.Person;
import com.example.testemployees2.pojo.PersonDoc;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class PersonListActivity extends AppCompatActivity implements PersonListView{


    private RecyclerView recyclerView;
    private PersonAdapters adapter;

    private PersonListPresenter presenter;


    @Override
    protected void onDestroy() {
        presenter.disposeDisposable();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new PersonListPresenter(this);
        recyclerView = findViewById(R.id.recyclerViewPersons);
        adapter = new PersonAdapters();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPersons(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        presenter.loadData();
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

    @Override
    public void showData(List<Person> persons) {
        adapter.setPersons(persons);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(PersonListActivity.this, "ошибка получения данных: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
    }

}