package com.example.hp.firebasedemo1;

import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity
{
    EditText editTextEmail,editTextPassword;
    Button buttonlogin;
    private FirebaseAuth mAuth;
    ProgressDialog progressDialog;
    CheckBox checkBoxPassword;
    TextView textViewForgotPassword;
    String uemail,email;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail=findViewById(R.id.email);
        editTextPassword=findViewById(R.id.password);
        buttonlogin=findViewById(R.id.login);
        checkBoxPassword=findViewById(R.id.ck_password);
        textViewForgotPassword=findViewById(R.id.forgotpassword);

        email=editTextEmail.getText().toString();



        mAuth = FirebaseAuth.getInstance();

        if(mAuth.getCurrentUser()!=null)
        {
            startActivity(new Intent(MainActivity.this,ProfileActivity.class));
            finish();
        }

        checkBoxPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if(!isChecked)
                {
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                else
                {
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });

        textViewForgotPassword.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mAuth.sendPasswordResetEmail(editTextEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(),"Email sent",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email not sent",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 uemail=editTextEmail.getText().toString().trim();
                String upassword=editTextPassword.getText().toString();
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
//                String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
               // login_user(uemail, upassword);


               if(uemail.equalsIgnoreCase(""))
                {
                    editTextEmail.setError("Enter Email");
                }
                else if(!uemail.matches(emailPattern))
                {
                    editTextEmail.setError("Please Enter Valid Email");
                }
                else if(upassword.equalsIgnoreCase(""))
                {
                    editTextPassword.setError("Enter password");
                }
                else
               {
                   progressDialog=ProgressDialog.show(MainActivity.this,"Please wait...","Processing",true);
                   login_user(uemail, upassword);

               }
            }
        });

        TextView textViewRegister=findViewById(R.id.tv_register);
        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void login_user(String email, String password)
    {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {

                        if (task.isSuccessful())
                        {
                            progressDialog.dismiss();
                            Toast.makeText(MainActivity.this,"Login Successful!",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(MainActivity.this,ProfileActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                           progressDialog.dismiss();

                            Toast.makeText(MainActivity.this,"Login Failed!",Toast.LENGTH_SHORT).show();

                        }

                    }
                });

    }
}
