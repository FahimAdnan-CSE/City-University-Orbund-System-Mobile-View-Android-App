package com.example.myproject12;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Feedback_activity extends AppCompatActivity {

    private Button sendbutton1,clearbutton1;
    private EditText nametext1,messagetext1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_feedback_activity);

        setContentView(R.layout.activity_feedback_activity);


        ActionBar actionBar=getSupportActionBar(); //how to remove action bar in android studio from specific activity
       // actionBar.isShowing();  //action bar is show in activity
        actionBar.hide();//action bar is hide from activity


        sendbutton1=(Button) findViewById(R.id.sendbuttonid);
        clearbutton1=(Button) findViewById(R.id.clearbuttonid);

        nametext1=(EditText) findViewById(R.id.enteryournameid);
        messagetext1=(EditText) findViewById(R.id.enteryourmessageid);

        Handler obj=new Handler();

        sendbutton1.setOnClickListener(obj);
        clearbutton1.setOnClickListener(obj);





    }

    public class Handler implements View.OnClickListener{


        @Override
        public void onClick(View v) {

            //try catch use if edite text and name is blank then the apllication will be close so why
                String name = nametext1.getText().toString();
                String message= messagetext1.getText().toString();


                //is name and message is empty then it wil show in text box invalid that's means we handle the exception

                if(name.isEmpty()||message.isEmpty())
                {

                        Toast.makeText(getApplicationContext(),"Error",Toast.LENGTH_SHORT).show();
                        if (name.isEmpty()) {
                            nametextfor();
                        } if (message.isEmpty()) {
                            messagetextfor();
                        }
                         else if(v.getId()==R.id.clearbuttonid)
                       {
                        nametext1.setText(""); //this will clear the name and message box
                        messagetext1.setText("");


                         }



                }


                //if no empty the message and name then it will go to the else condition and work the method


                else
                {

                    if(v.getId()==R.id.sendbuttonid)
                    {
                        Intent intent=new Intent(Intent.ACTION_SEND);
                        intent.setType("text/email");
                        intent.putExtra(Intent.EXTRA_EMAIL,new String[]{"fahimadnan5@gmail.com","fahimadnan9@gmail.com"});
                        intent.putExtra(Intent.EXTRA_SUBJECT,"FeedBack Message");
                        intent.putExtra(Intent.EXTRA_TEXT,"Name:"+name+"\nMessage:"+message);
                        startActivity(Intent.createChooser(intent,"FeedBack Via"));

                    }


                    else if(v.getId()==R.id.clearbuttonid)
                    {
                        nametext1.setText(""); //this will clear the name and message box
                        messagetext1.setText("");


                    }



                }

        }




    }




    void nametextfor() // this method will be show in edite text box that if you input invalid input
    {

        nametext1.setError("Enter a valid input");
        nametext1.requestFocus(); //editetext focus in cuourse
        return; //edittex return from this place


    }


    void messagetextfor() // this method will be show in edite text box that if you input invalid input
    {

        messagetext1.setError("Enter a valid input");
        messagetext1.requestFocus(); //editetext focus in cuourse
        return; //edittex return from this place


    }





}
