package com.example.hp.firebasedemo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextName, editTextPhone, editTextAge, editTextEmail, editTextPassword, editTextCpassword;
    Button button;
    RadioGroup radioGroup;
    RadioButton radioButtonMale, radioButtonFemale,radioButton;
    TextView textView;
    String strGender;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextName = findViewById(R.id.et_name);
        editTextPhone = findViewById(R.id.et_number);
        editTextAge = findViewById(R.id.et_age);
        editTextEmail = findViewById(R.id.et_email);
        editTextPassword = findViewById(R.id.et_password);
        editTextCpassword = findViewById(R.id.et_cpassword);
        button = findViewById(R.id.btn_register);
        textView = findViewById(R.id.tv_login);
        radioButtonFemale = findViewById(R.id.female);
        radioButtonMale = findViewById(R.id.male);
        radioGroup = findViewById(R.id.radiobutton);

        mAuth = FirebaseAuth.getInstance();

        textView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                RadioButton value = findViewById(checkedId);
                strGender = value.getText().toString();
            }
        });





        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=editTextName.getText().toString();
                String email=editTextEmail.getText().toString();
                String password=editTextPassword.getText().toString();
                String cpassword=editTextCpassword.getText().toString();
                String phone=editTextPhone.getText().toString();
                String age=editTextAge.getText().toString();
                String MobilePattern = "[0-9]{10}";
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                int selectedId=radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(selectedId);

                if(radioButtonMale.isChecked()){
                    Toast.makeText(RegisterActivity.this,"You Selected Male",Toast.LENGTH_SHORT).show();

                }else if(radioButtonFemale.isChecked()){
                    Toast.makeText(RegisterActivity.this,"You Selected Female",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(RegisterActivity.this,"Plz select gender",Toast.LENGTH_SHORT).show();
                }

                if(name.equalsIgnoreCase(""))
                {
                    editTextName.setError("Enter Your Name");
                }
                else if(phone.equalsIgnoreCase("")){
                    editTextPhone.setError("Enter mobile Number");
                }

                else if(!phone.matches(MobilePattern)) {
                    editTextPhone.setError("Enter Valid mobile Number");
                }
                else if(age.equalsIgnoreCase("")) {
                    editTextAge.setError("Enter Your Age");
                }
//                else if(radio.matches("")) {
//                    radioButton.setError("Please Select ");
//                }

                else if(email.equalsIgnoreCase(""))
                {
                    editTextEmail.setError("Enter Email");
                }
                else if(!email.matches(emailPattern))
                {
                    editTextEmail.setError("Enter Valid Email");
                }
                else if(password.equalsIgnoreCase("")) {
                    editTextPassword.setError("Enter Password");
                }
                else if(cpassword.equalsIgnoreCase("")) {
                    editTextCpassword.setError("Enter Password");
                }
                else if(!password.matches(cpassword)) {
                    editTextPassword.setError("Password & confirm password Not match");
                }
                else
                {
                    //Intent intent=new Intent(RegisterActivity.this,MainActivity.class);
                    //startActivity(intent);
                    progressDialog=ProgressDialog.show(RegisterActivity.this, "Please Wait...","Processing...",true);
                    register_user(name, phone, age, strGender, email, password, cpassword );


                }

            }
        });

    }

    private void register_user(final String name, final String phone, final String age, final String strGender, final String email, final String password, final String cpassword)
    {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
        {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                if (task.isSuccessful())
                {
                    progressDialog.dismiss();
                    UserRegister userRegister = new UserRegister(name, phone, age, strGender, email, password, cpassword);
                    FirebaseDatabase.getInstance().getReference("User_Registers")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(userRegister);
                    Toast.makeText(RegisterActivity.this, "Register Succesfully...", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "User Not Register...", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}