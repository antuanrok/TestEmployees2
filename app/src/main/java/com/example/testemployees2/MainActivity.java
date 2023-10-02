package com.example.testemployees2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.testemployees2.adapters.PersonAdapters;
import com.example.testemployees2.api.ApiFactory;
import com.example.testemployees2.api.ApiService;
import com.example.testemployees2.pojo.BirthPlace;
import com.example.testemployees2.pojo.Person;
import com.example.testemployees2.pojo.PersonDoc;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {


    private static final String PERSON_URL = "https://api.kinopoisk.dev/v1/person";


    private static final String PARAMS_API_KEY = "X-API-KEY";

    private static final String API_KEY = "5W90EA0-55VMD01-QCTCXS3-4RE59N7";
    private static final String PARAMS_ACCEPT = "accept";

    private static final String ACCEPT = "application/json";
    private static final String PARAMS_PAGE = "page";
    private static final String PAGE = "1";
    private static final String PARAMS_LIMIT = "limit";
    private static final String LIMIT = "20";
    private static final String PARAMS_SORT_FIELD = "sortField";
    private static final String SORT_FIELD = "countAwards";


    private static final String PARAMS_NAME = "name";
    private static final String NOT_NULL = "!null";


    private static final String PARAMS_SELECT_FIELDS = "selectFields";
    private static final String SELECT_FIELDS1 = "name";
    private static final String SELECT_FIELDS2 = "birthPlace.value";
    private static final String SELECT_FIELDS3 = "enName";
    private static final String SELECT_FIELDS4 = "sex";
    private static final String SELECT_FIELDS5 = "countAwards";

    private RecyclerView recyclerView;
    private PersonAdapters adapter;

    private Disposable disposable;
    private CompositeDisposable compositeDisposable;

    @Override
    protected void onDestroy() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerViewPersons);
        adapter = new PersonAdapters();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setPersons(new ArrayList<>());
        recyclerView.setAdapter(adapter);
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        disposable = apiService.getPersons("person", API_KEY, ACCEPT, PAGE, LIMIT, SORT_FIELD, SELECT_FIELDS1, SELECT_FIELDS2, SELECT_FIELDS3, SELECT_FIELDS4, SELECT_FIELDS5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonDoc>() {
                    @Override
                    public void accept(PersonDoc personDoc) throws Exception {
                        adapter.setPersons(personDoc.getDocs());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(MainActivity.this, "ошибка получения данных: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        compositeDisposable.add(disposable);
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