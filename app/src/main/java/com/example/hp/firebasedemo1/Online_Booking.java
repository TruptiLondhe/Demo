package com.example.hp.firebasedemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Online_Booking extends AppCompatActivity
{
    RecyclerView recyclerView;
    List<ModalClass> list =new ArrayList<>();
    AdapterClass adapterClass;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online__booking);
        recyclerView=findViewById(R.id.recyclerView);
        adapterClass=new AdapterClass(this,list);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterClass);
        data();




    }
    public void data()
    {
        ModalClass modalClass1=new ModalClass("Dr.Trupti","Dentist",R.drawable.doctor);
        list.add(modalClass1);

        ModalClass modalClass2=new ModalClass("Dr.Trupti","Dentist",R.drawable.man);
        list.add(modalClass2);

        ModalClass modalClass3=new ModalClass("Dr.Trupti","Dentist",R.drawable.doctor);
        list.add(modalClass3);

        ModalClass modalClass4=new ModalClass("Dr.Trupti","Dentist",R.drawable.girl);
        list.add(modalClass4);

        ModalClass modalClass5=new ModalClass("Dr.Trupti","Dentist",R.drawable.doctor);
        list.add(modalClass5);

        ModalClass modalClass6=new ModalClass("Dr.Trupti","Dentist",R.drawable.man);
        list.add(modalClass6);
    }
}
