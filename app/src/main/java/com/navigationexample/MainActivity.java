package com.navigationexample;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, FragmentManager.OnBackStackChangedListener{

    private DrawerLayout drawer;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle toggle;
    private NavigationManager mNavigationManager;
    private int idFragmentContainer = 0;
    private InputMethodManager inputMethodManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        getSupportFragmentManager().addOnBackStackChangedListener(this);

        //Set Toolbar
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getSupportFragmentManager().getBackStackEntryCount() > 0){
                    getSupportFragmentManager().popBackStack();
                }else {
                    drawer.openDrawer(GravityCompat.START);
                }
            }
        });

        //Initialize NavigationManager and id container
        mNavigationManager = new NavigationManager(getSupportFragmentManager());
        idFragmentContainer = R.id.content_main;

        //Initialize drawerLayout
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(this, drawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //Initialize navigation view and listener
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    //Onbackpressed method from back listener
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            closeDrawer();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.

        switch (item.getItemId()){
            case R.id.nav_camera:
                mNavigationManager.openAsRoot(HomeFragment.newInstance(), idFragmentContainer);
                break;
            case R.id.nav_gallery:
                break;
            case R.id.nav_slideshow:
                break;
            case R.id.nav_manage:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
        }

        closeDrawer();
        return true;
    }

    @Override
    public void onBackStackChanged() {
        if(getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }else {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            toggle.syncState();
        }
    }

    private void closeDrawer(){
        if (drawer != null){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void hidekeyboard() {
        if (mToolbar != null)
            inputMethodManager.hideSoftInputFromWindow(mToolbar.getWindowToken(), 0);
    }

    /**
     * Getter and Setter Methods
     * **/

    public NavigationManager getNavigationManager() {
        return mNavigationManager;
    }

    public void setNavigationManager(NavigationManager mNavigationManager) {
        this.mNavigationManager = mNavigationManager;
    }

    public int getIdFragmentContainer() {
        return idFragmentContainer;
    }
}
