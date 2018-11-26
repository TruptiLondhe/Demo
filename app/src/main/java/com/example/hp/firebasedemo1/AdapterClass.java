package com.example.hp.firebasedemo1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyClass>
{
    List<ModalClass> list;
    Context context;
    LayoutInflater layoutInflater;
    private DatePickerDialog datePicker;
    private SimpleDateFormat dateFormat;
    SharedPreferences sharedPreferences;



    public AdapterClass(Online_Booking activity, List<ModalClass> list)
    {

        this.list = list;
        this.context = activity;
        layoutInflater=LayoutInflater.from(context);
    }



    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view=layoutInflater.inflate(R.layout.list_item,null);
        return new MyClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass myClass, int i)
    {
        ModalClass modalClass=list.get(i);
        myClass.textViewName.setText(modalClass.getName());
        myClass.textViewSpecialist.setText(modalClass.getSpecialist());
        myClass.imageView.setImageResource(modalClass.getImage());




    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    public class MyClass extends RecyclerView.ViewHolder
    {
        TextView textViewName,textViewSpecialist;
        ImageView imageView;
        Button button;
        Spinner spinner;
        String[] time={"Please Select Time","11-12","12-1","1-2","2-3"};

        public MyClass(@NonNull View itemView)
        {
            super(itemView);
            textViewName=itemView.findViewById(R.id.tv_name);
            textViewSpecialist=itemView.findViewById(R.id.tv_specialist);
            imageView=itemView.findViewById(R.id.imageView);
            button=itemView.findViewById(R.id.btn_book);
            
            button.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    final EditText editTextName,editTextAge;
                    Button buttonSubmit;
                    final TextView textViewDate;

                    final Dialog dialog = new Dialog(context,R.style.ThemeOverlay_AppCompat);

                    LayoutInflater layoutInflater=LayoutInflater.from(context);
                    View view=layoutInflater.inflate(R.layout.dialogue_patient,null);

                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

                    dialog.setContentView(view);



                    textViewDate=dialog.findViewById(R.id.tv_date);
                    dateFormat=new SimpleDateFormat("dd-MM-yy", Locale.US);
                    textViewDate.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            setDateTimeField();
                            datePicker.show();

                        }

                        private void setDateTimeField()
                        {


                                Calendar newCalendar = Calendar.getInstance();
                                datePicker = new DatePickerDialog(context,new DatePickerDialog.OnDateSetListener()
                                        {

                                            public void onDateSet(DatePicker view, int monthOfYear, int dayOfMonth,int year)
                                            {
                                                Calendar newDate = Calendar.getInstance();
                                                newDate.set(monthOfYear, dayOfMonth,year);
                                                textViewDate.setText(dateFormat.format(newDate.getTime()));
                                            }

                                        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


                        }

                    });

                    spinner=dialog.findViewById(R.id.spinner_time);
                    ArrayAdapter arrayAdapter=new ArrayAdapter(context,android.R.layout.simple_list_item_1,time);
                    spinner.setAdapter(arrayAdapter);
                    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
                    {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                        {
                            String item = (String)spinner.getItemAtPosition(position);
                            if(position>0)
                            {

                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });

                    buttonSubmit=dialog.findViewById(R.id.btn_submit);
                    buttonSubmit.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {


                            Intent intent =new Intent(context,Selected_Doctor.class);
                            context.startActivity(intent);
                            dialog.dismiss();


                        }
                    });

                    public void hist()
                    {

                    }
                    dialog.show();
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                }
            });
        }
    }

}
