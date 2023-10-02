package com.example.testemployees2.api;

import com.example.testemployees2.pojo.PersonDoc;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

  //  private static final String PARAMS_API_KEY = "X-API-KEY";

   // private static final String API_KEY = "5W90EA0-55VMD01-QCTCXS3-4RE59N7";
  //  private static final String PARAMS_ACCEPT = "accept";

   // private static final String ACCEPT = "application/json";

    /*private static String PARAMS_SORT_FIELD = "sortField";
    private static final String SORT_FIELD = "countAwards";


    private static final String PARAMS_NAME = "name";
    private static final String NOT_NULL = "!null";


    private static final String PARAMS_SELECT_FIELDS = "selectFields";
    private static final String SELECT_FIELDS1 = "name";
    private static final String SELECT_FIELDS2 = "birthPlace.value";
    private static final String SELECT_FIELDS3 = "enName";
    private static final String SELECT_FIELDS4 = "sex";
    private static final String SELECT_FIELDS5 = "countAwards";*/

    @GET("v1/{add}")
    Observable <PersonDoc> getPersons(
            @Path("add") String path,
            @Header ("X-API-KEY") String api_key,
            @Header ("accept") String accept,
            @Query ("page") String page,
            @Query ("limit") String limit,
            @Query ("sortField") String sortField,
            @Query ("selectFields") String name,
            @Query ("selectFields") String birthPlaceValue,
            @Query ("selectFields") String enName,
            @Query ("selectFields") String sex,
            @Query ("selectFields") String countAwards

    );
}
