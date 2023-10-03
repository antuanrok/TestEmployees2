package com.example.testemployees2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.testemployees2.pojo.Person;

import java.util.List;

@Dao
public interface PersonDao {
    @Query("SELECT * FROM persons")
    LiveData<List<Person>> getAllPersons();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPersons(List<Person> persons);

    @Query("DELETE FROM persons")
    void deleteAllPersons();

}
