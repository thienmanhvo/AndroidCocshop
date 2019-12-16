package fpt.edu.cocshop.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import fpt.edu.cocshop.Adapter.MainAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Fragment.HomeFragment;
import fpt.edu.cocshop.Fragment.UserFragment;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.ExceptionHandler;
import fpt.edu.cocshop.Util.Token;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private MainAdapter mMainAdapter;
    private FragmentManager fragmentManager;
    //private ViewPager mViewPager;
    private LinearLayout mLLHelp, mLLMyOrder, mLLSaved, mLLUser;
    private TextView mTxtHelp, mTxtMyOrder, mTxtSaved, mTxtUser, mTxtHome;
    private ImageView mImgHelp, mImgMyOrder, mImgSaved, mImgUser;

    private ImageView mImgHome, mImgHomeCircle;
    private TextView mEdtSearch;

    private ImageView mImgNotfy;

    GoogleSignInClient mGoogleSignInClient;

    String name;
    String email;
    String id;
    String avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        Token.token = "Bearer eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNyc2Etc2hhNTEyIiwidHlwIjoiSldUIn0.eyJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOlsiQWRtaW4iLCJTdGFmZiIsIlVzZXIiXSwidXNlcm5hbWUiOiJ0aGllbm1hbmh2byIsImh0dHA6Ly9zY2hlbWFzLnhtbHNvYXAub3JnL3dzLzIwMDUvMDUvaWRlbnRpdHkvY2xhaW1zL25hbWVpZGVudGlmaWVyIjoiMGQzNzdkNTgtMWFhZS00OTk5LTAyZGItMDhkNzNkOTQwMjNmIiwiZXhwIjoxNTc2NDE2MTUxLCJpc3MiOiJodHRwczovL3d3dy5mYWNlYm9vay5jb20vdGhpZW5uYjE2NDMyMTYiLCJhdWQiOiJOZ3V54buFbiBC4bqjbyBUaGnhu4duIn0.VqqAIANMdCJ_4MVNAZ6z-Y7DcKzHMxpkpk6TJuQGgXtC0wQnyJT44ian10h64NqF8dZxQ_QHvVUTbSzehlGNLd1S__N-qKRpXPRB8OM88yCxBeZN2Bo3GYlfAwdrnbxYO-Tuzmk9z0UBMqzQVGCF21AbSK7VS6hxoAvnmpe2lgrQ4-hHKReI1Vj_y1lG5-RZfpwk2SZr-8DTSngvRQjzdydZNqYU0tKyYUz5E_K2uGACU1aywPSLJSfHQYPT1Z0p1wmEYNM2OqiRwEXmA3L_MfXv0vKiHuvrls6_qfxn1qrGdG9f2iY-1bQvMgq4un0VbXAySYqPHDbhNOA_3I3Nsw";
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
             avatar = personPhoto.toString();




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
        mEdtSearch = findViewById(R.id.edt_search_home);

        mImgNotfy = findViewById(R.id.ic_notification);
    }

    private void initData() {
        mLLHelp.setOnClickListener(this);
        mLLMyOrder.setOnClickListener(this);
        mLLSaved.setOnClickListener(this);
        mLLUser.setOnClickListener(this);
        mImgHome.setOnClickListener(this);

        mEdtSearch.setOnClickListener(this);


        mImgNotfy.setOnClickListener(this);

        //mMainAdapter = new MainAdapter(getSupportFragmentManager());
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.ll_content, new HomeFragment().newInstance(), null);
        //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
        fragmentTransaction.commit();
        setActive(Constant.HOME_PAGE_POSITION);
//        mViewPager.setAdapter(mMainAdapter);
//        mViewPager.setCurrentItem(Constant.HOME_PAGE_POSITION);
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                if (position == Constant.HOME_PAGE_POSITION) {
//                    setInActive(mViewPager.getCurrentItem());
//                    setActive(position);
//                }
////                else if (position == 1) {
////                    setPageHistory();
////                } else if (position == 2) {
////                    setPageAlbum();
////                } else if (position == 3) {
////                    setPageUser();
////                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
    }

    private void setActive(int position) {
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
                break;
            case Constant.HOME_PAGE_POSITION:
                DrawableCompat.setTint(
                        DrawableCompat.wrap(mImgHome.getDrawable()),
                        getColor(R.color.colorActive)
                );
                mImgHomeCircle.setImageResource(R.drawable.custom_button_home_color_active);

                mTxtHome.setTextColor(getResources().getColor(R.color.colorActive, getResources().newTheme()));
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

    @Override
    public void onClick(View view) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.ll_button_help:
                setInActive();
                setActive(Constant.HELP_PAGE_POSITION);
//                mViewPager.setCurrentItem(Constant.HELP_PAGE_POSITION);
                break;
            case R.id.ll_button_my_order:
                setInActive();
                setActive(Constant.MY_ORDER_PAGE_POSITION);
//                mViewPager.setCurrentItem(Constant.MY_ORDER_PAGE_POSITION);
                break;
            case R.id.ll_button_saved:
                setInActive();
                setActive(Constant.SAVED_PAGE_POSITION);
//                mViewPager.setCurrentItem(Constant.SAVED_PAGE_POSITION);
                break;
            case R.id.ll_button_user:
                setInActive();
                setActive(Constant.USER_PAGE_POSITION);
//                mViewPager.setCurrentItem(Constant.USER_PAGE_POSITION);
                UserFragment userFragment = new UserFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("email",email);
                bundle.putString("id",id);
                bundle.putString("avatar",avatar);
                userFragment.setArguments(bundle);
                fragmentTransaction.replace(R.id.ll_content,userFragment,null);
                fragmentTransaction.commit();

                break;
            case R.id.img_home:
                setInActive();
                setActive(Constant.HOME_PAGE_POSITION);
                fragmentTransaction.replace(R.id.ll_content,new HomeFragment(),null);
                fragmentTransaction.commit();

//                mViewPager.setCurrentItem(Constant.HOME_PAGE_POSITION);
                break;

//            case R.id.edt_search_home:
//                onMoveToSearchActivity();
//                break;
//
//            case R.id.ic_notification:
//                onMoveToNotificationActivity();
//                break;
        }
    }
}
