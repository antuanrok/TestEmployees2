package com.example.testemployees2.screens.persons;

import com.example.testemployees2.pojo.Person;

import java.util.List;

public interface PersonListView {
    void showData(List<Person> persons);
    void showError(Throwable throwable);
}
