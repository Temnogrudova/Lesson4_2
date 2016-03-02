package com.temnogrudova.lesson4_2;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements Fragment2.onSomeEventListener {

    FragmentManager fragmentManager;
    Fragment frag1;
    Fragment frag2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fragmentManager= getSupportFragmentManager();
        frag1 = new Fragment1();
        frag2 = new Fragment2();
        fragmentManager.beginTransaction().add(R.id.frag1, frag1).addToBackStack("").commit();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((TextView) frag1.getView().findViewById(R.id.textView1)).setText("Access to Fragment 1 from Activity");
                Bundle bundle = new Bundle();
                bundle.putString("key", "Send parameters to Fragment 2 from Activity");
                frag2.setArguments(bundle);
                fragmentManager.beginTransaction().add(R.id.frag2, frag2).addToBackStack("").commit();
            }
        });

    }

    @Override
    public void someEvent(String s) {
        ((TextView)frag1.getView().findViewById(R.id.textView1)).setText("Text from Fragment 2:" + s);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
