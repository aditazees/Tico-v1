package com.aditazees.ticov1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {


    TextView hitxt;
    ProgressBar pbar;
    DatabaseReference ref;

    Button typeamountb, amazonb, flipkartb, myaccb, helpb;


    //navbar_header_start
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    //navbar_header_end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hitxt=(TextView)findViewById(R.id.homescreentext);
        pbar=(ProgressBar)findViewById(R.id.homescreenpbar);
        typeamountb=(Button) findViewById(R.id.typeamtbtn);
        amazonb=(Button)findViewById(R.id.amazonbtn);
        flipkartb=(Button)findViewById(R.id.flipkartbtn);
        myaccb=(Button)findViewById(R.id.myaccountbtn);
        helpb=(Button)findViewById(R.id.helpbtn);


        ref= FirebaseDatabase.getInstance().getReference("User Info")
                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());



        //navbar_midportion_start
        dl = (DrawerLayout)findViewById(R.id.activity_main);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.home:
                    {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.typeamt:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getApplicationContext(), TypeAmount.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.amazon:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getApplicationContext(), Amazon.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.flipkart:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getApplicationContext(), Flipkart.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.myaccount:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getApplicationContext(), MyAccount.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.signout:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(MainActivity.this, Login.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.aboutus:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getApplicationContext(), AboutUs.class);
                        startActivity(i);
                        break;
                    }
                    case R.id.help:
                    {
                        FirebaseAuth.getInstance().signOut();
                        Intent i = new Intent(getApplicationContext(), Help.class);
                        startActivity(i);
                        break;
                    }
                    default:
                        return true;
                }
                return true;
            }
        });
        //navbar_midportion_end


        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String v1 = dataSnapshot.child("name").getValue(String.class);
                hitxt.setVisibility(View.INVISIBLE);
                pbar.setVisibility(View.VISIBLE);
                hitxt.setText("Hi, "+v1);
                pbar.setVisibility(View.INVISIBLE);
                hitxt.setVisibility(View.VISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        typeamountb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), TypeAmount.class);
                startActivity(i);
            }
        });

        amazonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Amazon.class);
                startActivity(i);
            }
        });

        flipkartb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Flipkart.class);
                startActivity(i);
            }
        });

        myaccb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), MyAccount.class);
                startActivity(i);
            }
        });

        helpb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(getApplicationContext(), Help.class);
                startActivity(i);
            }
        });



    }

    //navbat_footer_start
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
    //navbar_footer_end


}
