package com.example.registration;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    TextView tv;
    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView img;
    MyReceiver mr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        img=findViewById(R.id.img);
        mr=new MyReceiver(img);
        IntentFilter i1=new IntentFilter();
        i1.addAction(Intent.ACTION_POWER_CONNECTED);
        i1.addAction(Intent.ACTION_BATTERY_LOW);
        this.registerReceiver(mr,i1);
        tv=findViewById(R.id.tv);
        registerForContextMenu(tv);
        tabLayout=findViewById(R.id.tab);
        viewPager=findViewById(R.id.vp);
        viewPager.setAdapter(new myviewpageadaptor(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity2.this, "You selected"+tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity2.this, "You selected"+tab.getText().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                Toast.makeText(MainActivity2.this, "You selected"+tab.getText().toString(), Toast.LENGTH_SHORT).show();

            }
        });
    }
    //Fragment page Adaptor Class
    public class myviewpageadaptor extends FragmentPagerAdapter{
        String[] title={"Chats","Status","Calls"};

        public myviewpageadaptor(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if (position==0) {

                return new ChatFragment();
            }
            if (position==1){
                return new StatusFragment();
            }
            if (position==2){
                return new CallsFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return title.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.text:
                tv.setText("This is my Text View");
                break;
            case R.id.exit:
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setIcon(R.drawable.img);
                builder.setMessage("Are you Sure to Exit");
                builder.setTitle("Do you want to Exit");
                builder.setCancelable(false);
                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                builder.show();
                break;
            case R.id.date:
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_WEEK);
                DatePickerDialog date=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tv.setText(i+"/"+(++i1)+"/"+i2);
                    }
                },year,month,day);
                date.show();
                break;
            case R.id.time:
                Calendar t=Calendar.getInstance();
                int hour=t.get(Calendar.HOUR);
                int min=t.get(Calendar.MINUTE);
                TimePickerDialog time=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tv.setText(i+"::"+i1);
                    }
                },hour,min,true);
                time.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.mymenu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        switch (item.getItemId()){
            case R.id.text:
                tv.setText("Hello");
                break;
            case R.id.exit:
                Toast.makeText(this, "Are You Sure TO Exit", Toast.LENGTH_SHORT).show();
                break;
            case R.id.date:
                Calendar c=Calendar.getInstance();
                int year=c.get(Calendar.YEAR);
                int month=c.get(Calendar.MONTH);
                int day=c.get(Calendar.DAY_OF_WEEK);
                DatePickerDialog date=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        tv.setText("date is"+i+"/"+(++i1)+"/"+i2);
                    }
                },year,month,day);
                date.show();
                break;
            case R.id.time:
                Calendar t=Calendar.getInstance();
                int hour=t.get(Calendar.HOUR);
                int min=t.get(Calendar.MINUTE);
                TimePickerDialog time=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        tv.setText("Time is: "+i+"::"+i1);
                    }
                },hour,min,false);
                time.show();
                break;

        }
        return super.onContextItemSelected(item);
    }
}