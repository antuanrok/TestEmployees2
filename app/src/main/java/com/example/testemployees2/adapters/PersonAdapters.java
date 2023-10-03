package com.example.testemployees2.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testemployees2.R;
import com.example.testemployees2.pojo.BirthPlace;
import com.example.testemployees2.pojo.Person;

import java.util.List;

public class PersonAdapters extends RecyclerView.Adapter<PersonAdapters.PersonViewHolder> {

    private List<Person> persons;

    public void setPersons(List<Person> persons) {
        this.persons = persons;
        notifyDataSetChanged();
    }

    public List<Person> getPersons() {
        return persons;
    }


    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.emloyee_item, parent, false);
        return new PersonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {

        Person person = persons.get(position);
        holder.textViewName.setText(person.getName());
       List<BirthPlace> birthPlaces = person.getBirthPlace();
        if (birthPlaces!=null&& birthPlaces.size()!=0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (BirthPlace place :
                    birthPlaces) {
                stringBuilder.append(place.getValue());
                stringBuilder.append(",");
            }
            if (stringBuilder.length() != 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                holder.textViewBirth.setText(stringBuilder.toString());
            } else {
                holder.textViewBirth.setText("null");
            }
        }
    }


    @Override
    public int getItemCount() {
        return persons.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewBirth;

        public PersonViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textViewName);
            textViewBirth = itemView.findViewById(R.id.textViewBirth);
        }
    }
}
