package com.example.testemployees2.screens.persons;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testemployees2.api.ApiFactory;
import com.example.testemployees2.api.ApiService;
import com.example.testemployees2.data.AppDatabase;
import com.example.testemployees2.pojo.Person;
import com.example.testemployees2.pojo.PersonDoc;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PersonViewModel extends AndroidViewModel {

    private static AppDatabase db;
    private LiveData<List<Person>> persons;
    private MutableLiveData<Throwable> errors;



    private CompositeDisposable compositeDisposable;

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

    public LiveData<Throwable> getErrors() {
        return errors;
    }

    public void clearErrors(){
      errors.setValue(null);
    }

    public PersonViewModel(@NonNull Application application) {
        super(application);
        db = AppDatabase.getInstance(application);
        persons = db.personDao().getAllPersons();
        errors = new MutableLiveData<>();
    }

    public LiveData<List<Person>> getPersons() {
        return persons;
    }

    /**
     * @noinspection unchecked
     */
    private void insertPersons(List<Person> personList) {
        new InsertPersonTask().execute(personList);
    }

    private static class InsertPersonTask extends AsyncTask<List<Person>, Void, Void> {

        @SafeVarargs
        @Override
        protected final Void doInBackground(List<Person>... lists) {
            if (lists != null && lists.length > 0) {
                db.personDao().insertPersons(lists[0]);
            }
            return null;
        }
    }

    private void deletePersons() {
        new DeleteAllTask().execute();
    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            db.personDao().deleteAllPersons();
            return null;
        }
    }

    public void loadData() {
        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();
        compositeDisposable = new CompositeDisposable();
        Disposable disposable = apiService.getPersons("person", API_KEY, ACCEPT, PAGE, LIMIT, SORT_FIELD, SELECT_FIELDS1, SELECT_FIELDS2, SELECT_FIELDS3, SELECT_FIELDS4, SELECT_FIELDS5)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PersonDoc>() {
                    @Override
                    public void accept(PersonDoc personDoc) throws Exception {
                        //view.showData(personDoc.getDocs());
                        // adapter.setPersons(personDoc.getDocs());
                        deletePersons();
                        insertPersons(personDoc.getDocs());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        errors.setValue(throwable);
                      //  view.showError(throwable);
                        //  Toast.makeText(PersonListActivity.this, "ошибка получения данных: " + throwable.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });

        compositeDisposable.add(disposable);
    }


    @Override
    protected void onCleared() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        super.onCleared();
    }
}