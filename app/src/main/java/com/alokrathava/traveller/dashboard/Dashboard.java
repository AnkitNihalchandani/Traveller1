package com.alokrathava.traveller.dashboard;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.alokrathava.traveller.R;
import com.alokrathava.traveller.SessionManagement.SessionManagement;
import com.alokrathava.traveller.auth.Login;
import com.alokrathava.traveller.databinding.ActivityDashboardBinding;
import com.alokrathava.traveller.utils.Config;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.navigation.NavigationView;

public class Dashboard extends AppCompatActivity {

    private final Context context = this;
    ActionBarDrawerToggle mToggle;
    DrawerLayout mDrawerLayout;
    NavigationView nav;
    private ActivityDashboardBinding binding;
    private androidx.appcompat.widget.Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_dashboard );

        binding = ActivityDashboardBinding.inflate ( getLayoutInflater ( ) );
        View view = binding.getRoot ( );
        setContentView ( view );

        mtoolbar = findViewById ( R.id.toolbar );
        setSupportActionBar ( mtoolbar );

        nav           = findViewById ( R.id.navmenu );
        mDrawerLayout = findViewById ( R.id.drawer );


        mToggle = new ActionBarDrawerToggle ( this , mDrawerLayout , mtoolbar , R.string.Open , R.string.Close );
        mDrawerLayout.addDrawerListener ( mToggle );
        mToggle.syncState ( );

        nav.setNavigationItemSelectedListener ( item -> {

            switch (item.getItemId ( )) {

                case R.id.home:
                    Toast.makeText(Dashboard.this, "Home", Toast.LENGTH_SHORT).show();
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.order:
                    Toast.makeText(Dashboard.this, "Order", Toast.LENGTH_SHORT).show();
//                    Intent order = new Intent(Home.this,Order.class);
//                    view.getContext().startActivity(order);
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.air:
                    startActivity(new Intent(Dashboard.this, AirTicketsWeb.class));
                    Config.showToast(context, "Air");
                    mDrawerLayout.closeDrawer((GravityCompat.START));
                    break;
                case R.id.train:
                    Config.showToast(context, "Train");
                    startActivity(new Intent(Dashboard.this, TrainTicketWeb.class));
                    mDrawerLayout.closeDrawer((GravityCompat.START));
                    break;
                case R.id.history:
                    Toast.makeText(Dashboard.this, "History", Toast.LENGTH_LONG).show();
//                        startActivity(new Intent(Dashboard.this,History.class));
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    break;

                case R.id.logout:
                    Toast.makeText ( Dashboard.this , "Logout" , Toast.LENGTH_SHORT ).show ( );
                    LogOut ( );
                    mDrawerLayout.closeDrawer ( GravityCompat.START );

            }

            return true;
        } );
//Date Picker
        MaterialDatePicker.Builder materialDateBuilder = MaterialDatePicker.Builder.datePicker ( );
        materialDateBuilder.setTitleText ( "Select Date" );
        final MaterialDatePicker materialDatePicker = materialDateBuilder.build ( );
        binding.pickDateButton.setOnClickListener ( v -> materialDatePicker.show ( getSupportFragmentManager ( ) , "MATERIAL_DATE_PICKER" ) );
        materialDatePicker.addOnPositiveButtonClickListener (
                selection -> binding.pickDateButton.setText ( materialDatePicker.getHeaderText ( ) ) );
//        Addnew Trip
        binding.addnewtrip.setOnClickListener ( v -> AddNewTrip ( ) );
//        Activity Redirection
        binding.BagInfobtn.setOnClickListener ( v -> startActivity ( new Intent ( Dashboard.this , Baggage.class ) ) );
        binding.Docbtn.setOnClickListener ( v -> startActivity ( new Intent ( Dashboard.this , DocWallet.class ) ) );
        binding.taxibtn.setOnClickListener ( v -> startActivity ( new Intent ( Dashboard.this , TaxiBook.class ) ) );
    }

    private void AddNewTrip() {
        Config.showToast ( Dashboard.this , "Button Works" );
    }


    private void LogOut() {
        SessionManagement sessionManagement = new SessionManagement ( Dashboard.this );
        sessionManagement.removeSession ( );
        MoveToLogin ( );
    }

    private void MoveToLogin() {
        startActivity ( new Intent ( Dashboard.this , Login.class ) );
    }
}