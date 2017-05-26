package tk.leventebajczi.pocketfinance;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import tk.leventebajczi.pocketfinance.fragments.Checking;
import tk.leventebajczi.pocketfinance.fragments.Exchange;
import tk.leventebajczi.pocketfinance.fragments.Investments;
import tk.leventebajczi.pocketfinance.fragments.Overview;
import tk.leventebajczi.pocketfinance.fragments.Shipments;
import tk.leventebajczi.pocketfinance.fragments.Transactions;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private Overview overviewFragment;
    private Checking checkingFragment;
    private Investments investmentsFragment;
    private Transactions transactionsFragment;
    private Shipments shipmentsFragment;
    private Exchange exchangeFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Overview");
        setSupportActionBar(toolbar);

        overviewFragment = new Overview();
        checkingFragment = new Checking();
        investmentsFragment = new Investments();
        transactionsFragment = new Transactions();
        shipmentsFragment = new Shipments();
        exchangeFragment = new Exchange();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        if (id == R.id.nav_overview) {
            ft.replace(R.id.mainfragment, overviewFragment);

        } else if (id == R.id.nav_checking) {
            ft.replace(R.id.mainfragment, checkingFragment);

        } else if (id == R.id.nav_investments) {
            ft.replace(R.id.mainfragment, investmentsFragment);

        } else if (id == R.id.nav_transactions) {
            ft.replace(R.id.mainfragment, transactionsFragment);

        } else if (id == R.id.nav_orders) {
            ft.replace(R.id.mainfragment, shipmentsFragment);

        } else if (id == R.id.nav_exchange) {
            ft.replace(R.id.mainfragment, exchangeFragment);

        } else if (id == R.id.nav_backup) {
            //backup();
        } else if (id == R.id.nav_restore) {
            //restore();
        } else if (id == R.id.nav_schedule){
            //setSchedule();
        }

        ft.commit();
        getSupportFragmentManager().executePendingTransactions();
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
