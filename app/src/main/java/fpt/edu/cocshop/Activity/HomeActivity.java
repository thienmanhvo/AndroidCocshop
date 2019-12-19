package fpt.edu.cocshop.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.util.Locale;

import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Fragment.HomeFragment;
import fpt.edu.cocshop.Fragment.MyOrderFragment;
import fpt.edu.cocshop.Fragment.UserFragment;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.CurrentLocation;
import fpt.edu.cocshop.Util.ExceptionHandler;
import fpt.edu.cocshop.Util.GpsUtils;
import fpt.edu.cocshop.Util.MyAccount;
import fpt.edu.cocshop.Util.Token;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentManager fragmentManager;
    //private ViewPager mViewPager;
    private LinearLayout mLLHelp, mLLMyOrder, mLLSaved, mLLUser;
    private TextView mTxtHelp, mTxtMyOrder, mTxtSaved, mTxtUser, mTxtHome;
    private ImageView mImgHelp, mImgMyOrder, mImgSaved, mImgUser;

    private ImageView mImgHome, mImgHomeCircle;
    private FusedLocationProviderClient mFusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallback locationCallback;
    private boolean isGPS = false;
    GoogleSignInClient mGoogleSignInClient;

    String name;
    String email;
    String id;
    String avatar;
    private boolean isContinue = true;
    private boolean isCommit = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        Token.token = "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNyc2Etc2hhNTEyIiwidHlwIjoiSldUIn0.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQWRtaW4iLCJTdGFmZiIsIlVzZXIiXSwidXNlcm5hbWUiOiJ0aGllbm1hbmh2byIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWVpZGVudGlmaWVyIjoiMGQzNzdkNTgtMWFhZS00OTk5LTAyZGItMDhkNzNkOTQwMjNmIiwiZXhwIjoxNTc5Mjk0NDEyLCJpc3MiOiJodHRwczovL3d3dy5mYWNlYm9vay5jb20vdGhpZW5uYjE2NDMyMTYiLCJhdWQiOiJOZ3V54buFbiBC4bqjbyBUaGnhu4duIn0.lEYBY8Gbt2JoPxRQTOG0gsY4K2wUiWByaou9MBk_j2PSu-Q2cqPqomLU_X7UYf00BWgauvNg8nrYwtf2j_b_eqkwPzukx26h0Kji8tClYkqK9LgTi4pMy6M0z7eV6ZGtlae9hfXKWny8PWpU-SN-NfljUf2VEtOQxiVfw3jU4v9_b860CujZ0KmjW4QeemqhzrIIndKjD5HMitEx1XpdjdqJ0Srqx5p2tVOTmkgBBEWtnYUpG2ox__6dn1-rg1d8BQZk6xtGuZWnfa84KVLMvwyMagsDllBFRpE-kp4LJUi1M1frKXRkDAWDlQl4bfRCibRAHg4d4tWxlVEP5sjkUw";
        initView();
        initData();

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(HomeActivity.this);
        if (acct != null) {
            name = acct.getDisplayName();
            email = acct.getEmail();
            id = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            avatar = personPhoto == null ? "https://img.icons8.com/plasticine/2x/user.png" : personPhoto.toString();
            MyAccount.url = avatar;

            // Glide.with(this).load(personPhoto).into(photoIV);
        }

    }

    private void initView() {
        // mViewPager = findViewById(R.id.vp_home);
        mLLHelp = findViewById(R.id.ll_button_help);
        mLLMyOrder = findViewById(R.id.ll_button_my_order);
        mLLSaved = findViewById(R.id.ll_button_saved);
        mLLUser = findViewById(R.id.ll_button_user);
        mTxtHelp = findViewById(R.id.txt_title_help);
        mTxtMyOrder = findViewById(R.id.txt_title_my_order);
        mTxtSaved = findViewById(R.id.txt_title_saved);
        mTxtUser = findViewById(R.id.txt_title_user);
        mImgHelp = findViewById(R.id.img_icon_help);
        mImgMyOrder = findViewById(R.id.img_icon_my_order);
        mImgSaved = findViewById(R.id.img_icon_saved);
        mImgUser = findViewById(R.id.img_icon_user);
        mImgHome = findViewById(R.id.img_home);
        mImgHomeCircle = findViewById(R.id.img_home_circle);
        mTxtHome = findViewById(R.id.txt_title_home);
        //mEdtSearch = findViewById(R.id.edt_search_home);

        //mImgNotfy = findViewById(R.id.ic_notification);
    }

    private void initData() {
        Integer page = (Integer) getIntent().getSerializableExtra(Constant.SET_PAGE_ACTIVE);
        mLLHelp.setOnClickListener(this);
        mLLMyOrder.setOnClickListener(this);
        mLLSaved.setOnClickListener(this);
        mLLUser.setOnClickListener(this);
        mImgHome.setOnClickListener(this);
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(10 * 1000); // 10 seconds
        locationRequest.setFastestInterval(5 * 1000); // 5 seconds
        new GpsUtils(this).turnGPSOn(new GpsUtils.onGpsListener() {
            @Override
            public void gpsStatus(boolean isGPSEnable) {
                // turn on GPS
                isGPS = isGPSEnable;
            }
        });
        fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        if (!isCommit) {
                            fragmentTransaction.add(R.id.ll_content, new HomeFragment().newInstance(), null);
                            fragmentTransaction.commit();
                            isCommit = true;
                            if (page != null) {
                                setActive(page);
                            } else {
                                setActive(Constant.HOME_PAGE_POSITION);
                            }
                        }
                        CurrentLocation.latitude = location.getLatitude();
                        CurrentLocation.longitude = location.getLongitude();
                        if (!isContinue && mFusedLocationClient != null) {
                            mFusedLocationClient.removeLocationUpdates(locationCallback);
                        }
                    }
                }
            }
        };
        getLocation();


    }

    private void setActive(int position) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        setInActive();
        switch (position) {
            case Constant.HELP_PAGE_POSITION:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgHelp.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mTxtHelp.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
                break;
            case Constant.MY_ORDER_PAGE_POSITION:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgMyOrder.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mTxtMyOrder.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
                fragmentTransaction.replace(R.id.ll_content, new MyOrderFragment(), null);
                fragmentTransaction.commit();
                break;
            case Constant.SAVED_PAGE_POSITION:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgSaved.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mTxtSaved.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
                break;
            case Constant.USER_PAGE_POSITION:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgUser.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mTxtUser.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
                UserFragment userFragment = new UserFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", name);
                bundle.putString("email", email);
                bundle.putString("id", id);
                bundle.putString("avatar", avatar);
                userFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_content, userFragment, null);
                fragmentTransaction.commit();
                break;
            case Constant.HOME_PAGE_POSITION:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgHome.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mImgHomeCircle.setImageResource(R.drawable.custom_button_home_color_active);

                mTxtHome.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
                fragmentTransaction.replace(R.id.ll_content, new HomeFragment(), null);
                fragmentTransaction.commit();

                break;
            default:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgHome.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mTxtHome.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
        }

    }

    private void setInActive() {
        DrawableCompat.setTint(
                DrawableCompat.wrap(mImgHelp.getDrawable()),
                getColor(R.color.colorIcon)
        );
        DrawableCompat.setTint(
                DrawableCompat.wrap(mImgUser.getDrawable()),
                getColor(R.color.colorIcon)
        );
        DrawableCompat.setTint(
                DrawableCompat.wrap(mImgSaved.getDrawable()),
                getColor(R.color.colorIcon)
        );
        DrawableCompat.setTint(
                DrawableCompat.wrap(mImgMyOrder.getDrawable()),
                getColor(R.color.colorIcon)
        );
        DrawableCompat.setTint(
                DrawableCompat.wrap(mImgHome.getDrawable()),
                getColor(R.color.colorIcon)
        );
        mImgHomeCircle.setImageResource(R.drawable.custom_button_home_color_icon);
        mTxtHelp.setTextColor(getResources().getColor(R.color.colorIcon, getResources().newTheme()));
        mTxtUser.setTextColor(getResources().getColor(R.color.colorIcon, getResources().newTheme()));
        mTxtSaved.setTextColor(getResources().getColor(R.color.colorIcon, getResources().newTheme()));
        mTxtMyOrder.setTextColor(getResources().getColor(R.color.colorIcon, getResources().newTheme()));
        mTxtHome.setTextColor(getResources().getColor(R.color.colorIcon, getResources().newTheme()));
    }

    private void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    Constant.LOCATION_REQUEST);

        } else {
            if (isContinue) {
                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
            } else {
                mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                    if (location != null) {
                        CurrentLocation.latitude = location.getLatitude();
                        CurrentLocation.longitude = location.getLongitude();
                    } else {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    }
                });
            }
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1000: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (isContinue) {
                        mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                    } else {
                        mFusedLocationClient.getLastLocation().addOnSuccessListener(this, location -> {
                            if (location != null) {
                                CurrentLocation.latitude = location.getLatitude();
                                CurrentLocation.longitude = location.getLongitude();
                            } else {
                                mFusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
                            }
                        });
                    }
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constant.GPS_REQUEST) {
                isGPS = true; // flag maintain before get location
            }
        }
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_button_help:
                setActive(Constant.HELP_PAGE_POSITION);
                break;
            case R.id.ll_button_my_order:
                setActive(Constant.MY_ORDER_PAGE_POSITION);
                break;
            case R.id.ll_button_saved:
                setActive(Constant.SAVED_PAGE_POSITION);

                break;
            case R.id.ll_button_user:
                setActive(Constant.USER_PAGE_POSITION);
                break;
            case R.id.img_home:
                setActive(Constant.HOME_PAGE_POSITION);
                break;
        }
    }
}
