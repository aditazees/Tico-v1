package com.aditazees.ticov1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class Help extends AppCompatActivity {

    //navbar_header_start
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    //navbar_header_end


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
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
                        FirebaseAuth.getInstance().signOut();
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
                        Intent i = new Intent(getApplicationContext(), Login.class);
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
