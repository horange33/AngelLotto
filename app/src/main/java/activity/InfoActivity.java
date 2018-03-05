package activity;

import helper.SQLiteHandler;
import helper.SessionManager;

import java.util.HashMap;

import qrcodereader.FullScannerFragmentActivity;
import tbc.angellotto.R;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.support.design.widget.FloatingActionButton;

public class InfoActivity extends Activity {

    private Class<?> mClss;
    private static final int ZXING_CAMERA_PERMISSION = 1;

    private TextView txtName;
    private TextView txtEmail;
    private TextView txtBirthday;
    private Button btnLogout;
    private Button btnGetNum;
    private Button btnAroundStore;
    private Button btnbiorhythm;
    private Button btntemp;
    private SQLiteHandler db;
    private SessionManager session;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.CHANGE_WIFI_STATE
    };  // 권한 추가 chkim

    LinearLayout ll_ex_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        txtName = (TextView) findViewById(R.id.name);
        txtEmail = (TextView) findViewById(R.id.email);
        txtBirthday = (TextView) findViewById(R.id.birthday);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnGetNum = (Button) findViewById(R.id.GetNumBtn);
        btnAroundStore = (Button) findViewById(R.id.btnAroundStore);
        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "QR코드 촬영 여기에", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                launchFullFragmentActivity(view);
            }
        });
        // Fetching user details from sqlite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");
        String birthday = user.get("birthday");

        // Displaying the user details on the screen
        txtName.setText(name);
        //txtEmail.setText(email);
        //txtBirthday.setText(birthday);

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // 권한 획득에 대한 설명 보여주기
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {

                ActivityCompat.requestPermissions(this,
                        PERMISSIONS_STORAGE,
                        MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }


        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        btnGetNum.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, GetLottoActivity.class);
                startActivity(intent);
            }
        });
        btnAroundStore.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoActivity.this, map.MapsActivity.class);
                intent.putExtra("Map", "Around");
                startActivity(intent);
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(InfoActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZXING_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }
    public void launchFullFragmentActivity(View v) {
        launchActivity(FullScannerFragmentActivity.class);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}