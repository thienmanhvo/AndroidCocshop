package fpt.edu.cocshop.Fragment;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import fpt.edu.cocshop.Activity.StoreActivity;
import fpt.edu.cocshop.Activity.StoreListActivity;
import fpt.edu.cocshop.Adapter.FoodPicksAdapter;
import fpt.edu.cocshop.Constant.Constant;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListContract;
import fpt.edu.cocshop.Home_Store_List.HomeStoreListPresenter;
import fpt.edu.cocshop.Home_Store_List.ShowEmptyView;
import fpt.edu.cocshop.Model.Brand;
import fpt.edu.cocshop.Model.Location;
import fpt.edu.cocshop.Model.Store;
import fpt.edu.cocshop.R;
import fpt.edu.cocshop.Util.Token;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements HomeStoreListContract.View, ShowEmptyView {
    private static final String TAG = "HomeFragment";
    private RecyclerView mRcvFoodPicks, mRcvTopBrand, mRcvTopStore;
    private FoodPicksAdapter mFoodPicksAdapter;
    private List<Store> mStoreList;
    private View mView;
    private ProgressBar pbLoading;
    private TextView txtEmptyView;
    private HomeStoreListPresenter storeListPresenter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        initData();
        return mView;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void initView() {
        pbLoading = mView.findViewById(R.id.pb_loading);

        mRcvFoodPicks = mView.findViewById(R.id.rcv_food_picks);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRcvFoodPicks.setLayoutManager(manager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
        mRcvTopBrand = mView.findViewById(R.id.rcv_popular_brands);
        mRcvTopBrand.setLayoutManager(layoutManager);

        LinearLayoutManager managerChef = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        mRcvTopStore = mView.findViewById(R.id.rcv_top_store);
        mRcvTopStore.setLayoutManager(managerChef);

//        GridLayoutManager layoutManagerFollow = new GridLayoutManager(getContext(),2);
//        mRcvTopDishyFollow = mView.findViewById(R.id.rcv_top_recipe_follow);
//        mRcvTopDishyFollow.setLayoutManager(layoutManagerFollow);

    }

    private void initData() {

//        List<Location> locations = new ArrayList<>();
//        locations.add(new Location("269 Liên phường"));
        mStoreList = new ArrayList<>();
//        mStoreList.add(new Brand("Mì Trường Thọ", locations, "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", 3));
//        mStoreList.add(new Brand("Gà sốt phô mai", locations, "https://znews-photo.zadn.vn/w660/Uploaded/Ohunoaa/2016_12_31/d6.jpg", (float) 4.7));
//        mBrandList.add(new Brand("Gà sốt phô mai", locations, "https://bepmenau.com/wp-content/uploads/2018/05/Lau-Thai-hai-san_8_1.1.359_1124X1685.jpeg", 5));
//        mBrandList.add(new Brand("Kimbap chiên xù", locations, "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRqGelogsMrJv1R3tkdQXER63ewilYAUzG4UAO0KWIfSZpGWSn", 2));
//        mBrandList.add(new Brand("Bánh chocolate", locations, "https://images.pexels.com/photos/3026810/pexels-photo-3026810.jpeg?cs=srgb&amp;dl=avocado-chocolate-dessert-3026810.jpg&amp;fm=jpg", 4));
//        mBrandList.add(new Brand("Tôm ghim chua ngọt", locations, "https://images.pexels.com/photos/699544/pexels-photo-699544.jpeg?cs=srgb&amp;dl=chopsticks-cuisine-delicious-699544.jpg&amp;fm=jpg", 4));
//        mBrandList.add(new Brand("Bún đậu mắm tôm", locations, "https://vnn-imgs-f.vgcloud.vn/2018/09/18/12/cach-lam-bun-dau-mam-tom-ngon-nhu-cua-ba-noi-phim-gao-nep-gao-te.jpg", 3));
        updateUIRcvFoodPicks(mStoreList);
        storeListPresenter = new HomeStoreListPresenter(this);
        storeListPresenter.requestDataFromServer(10, 1, 10.806941, 106.788891, 10);
//        mTopDishy = new ArrayList<>();
//        mTopDishy.add(new Dishy("Mì Trường Thọ", "https://images.pexels.com/photos/3026808/pexels-photo-3026808.jpeg?cs=srgb&amp;dl=asian-food-bowl-food-photography-3026808.jpg&amp;fm=jpg", "20 phút", 3, 5, "Trung bình", 53, mStep1, mMaterial1, mChef1));
//        mTopDishy.add(new Dishy("Bánh tráng trộn", "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg", "24 phút", 5, "Khó", 100));
//        mTopDishy.add(new Dishy("Lẩu gà lá giang", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/07/lau-ga-la-giang.jpg", "20 phút", 3, "Dễ", 200));
//        mTopDishy.add(new Dishy("Mì cay", "https://cdn.tgdd.vn/Files/2016/07/26/863246/cach-lam-mi-cay-3-cap-do-cho-ngay-mua-them-am-bung4.jpg", "18 phút", 2, "Trung bình", 230));
//        mTopDishy.add(new Dishy("Hamburger thịt bò", "https://images.pexels.com/photos/1199960/pexels-photo-1199960.jpeg?cs=srgb&amp;dl=burger-cheeseburger-close-up-1199960.jpg&amp;fm=jpg", "Dễ", 4, "Dễ", 500));
//        updateUIRcvTopDishy(mTopDishy);
//
//        mChefs = new ArrayList<>();
//        mChefs.add(new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F", "Nguyễn Thanh Nhàn", 100, 1000, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-9/70172923_530781041027944_2863099953419386880_o.jpg?_nc_cat=101&_nc_oc=AQlFYB2tkZCgQUyLzRy8IO8HK8iejP05xdDPdg9v6BQRynJ23HLIN2fRf5kdmsrDikZee4UamPU5-Kx540iCc_Nh&_nc_ht=scontent.fsgn5-1.fna&oh=fbdca8ce8e94d3f0362b0b88a6de1a60&oe=5DF68723", "Diệp Thị Hiếu Phụng", 100, 989, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c94.0.320.320a/p320x320/10354686_10150004552801856_220367501106153455_n.jpg?_nc_cat=1&_nc_oc=AQnkg1FO0BOUe_3tyQjvLTX0BQknJmTx2Xi46NAM5vcMHW1iain3p1l3Wtld3oXnWyHbYc7B8ePQLpI3SaicuUX2&_nc_ht=scontent.fsgn5-2.fna&oh=9ec1f59f0c8d4f8cc46af0a329d330da&oe=5DFFDC59", "Nguyễn Văn Hoà", 90, 800, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/p320x320/42702447_309972646225392_437175461110349824_n.jpg?_nc_cat=107&_nc_oc=AQltiSM9CxF69luR0u8RtBS8zt-vjOsQhNThCzmsDSEskyPJJ0OIs5mDc53b-qc6wEzMIhuB-pKxmRBsfaIUk1SZ&_nc_ht=scontent.fsgn5-2.fna&oh=4e4e4be1e05f5f224306f01f99a9dadb&oe=5E351777", "Phạm Minh Thành", 80, 700, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-6.fna.fbcdn.net/v/t1.0-0/s206x206/67623070_1513383675469863_7139297301738029056_n.jpg?_nc_cat=106&_nc_oc=AQlDpAwagXzUiYMNII1OTEUgF_hbe4JsVVXtGQrh4NdQdiCOyqobyD8Yp0kAqS7q6Kl9ywuetZvDPdqneKAU3pIa&_nc_ht=scontent.fsgn5-6.fna&oh=979a38f27a9022b4abb79921f421f9e4&oe=5E33A071", "Nguyễn Phúc Hậu", 70, 520, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-1/p320x320/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlqW0sH4rpBiS2IPFfCEaorz-_7CDmDvZCV3YdL5u0-dyJcqxhMRJMtySaDEMPjS-uGvtyeUqP4ZJT4328aZLhU&_nc_ht=scontent.fsgn5-3.fna&oh=ea35b802e27df0b8c9f7bc852baec68f&oe=5E023271", "Nguyễn Hoàng Bá Khánh", 60, 450, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/p320x320/53412001_630154360777669_6753580234643079168_n.jpg?_nc_cat=105&_nc_oc=AQke1iRQnNv-Oa0QHsOIrHt-ZImOFADfaBh2P6WoMF4Np-Cz0vTY6KAE_rkYdWzNoB5kekgk2Jk08fOMI55IYj1v&_nc_ht=scontent.fsgn5-2.fna&oh=43c8e8a45e0d7d2ba4c66235fa403110&oe=5E00C2A0", "Nguyễn Nghĩa Mai Linh", 50, 430, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/983876_369442600117243_1445371059219521621_n.jpg?_nc_cat=101&_nc_oc=AQlJTywK2U-4NaFc2ikP6TZY-Q6jQxc3vEog1YcOSfe4E08-YxspR4aCkzNzivL3qbAVczrAaelzFrveGJqtw_m9&_nc_ht=scontent.fsgn5-1.fna&oh=591e9cc67683820744e9363778dfcfb3&oe=5E2CA73B", "Nguyễn Bảo Thiện", 30, 390, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c0.0.320.320a/p320x320/10599638_1504520149789789_4487427289442049165_n.jpg?_nc_cat=107&_nc_oc=AQne8gyZOiKbnc5zvKNLPtE9JeuC3flPcvR_za52mOc-3TbE4W7PH4f7z6JNwOznFtH-JPIglXn769P5Kl4ORJ0e&_nc_ht=scontent.fsgn5-2.fna&oh=4a4dbfc66aa3a1fb5aeeefa754ce422f&oe=5E2E96E2", "Nguyễn Văn Lương", 20, 300, mDishyList));
//        mChefs.add(new Chef("https://scontent.fsgn5-4.fna.fbcdn.net/v/t1.0-1/p320x320/56922724_993070917567979_1385984228432281600_n.jpg?_nc_cat=104&_nc_oc=AQkclC0oXgatAne3ddan4uDnoLHScDDXh1r4TaQB4lHiZI5efE7WrAqUxsCP2IXJpKAKVPEx_GNkxUvsJI1k4M2u&_nc_ht=scontent.fsgn5-4.fna&oh=1811529e821ba45253c10d35236a335b&oe=5DF418A8", "Dương Đức Duy ", 10, 100, mDishyList));
//        updateUIRcvTopChef(mChefs);
//
//        Chef chef1 = new Chef("https://scontent.fsgn5-1.fna.fbcdn.net/v/t1.0-1/p320x320/61090498_1285841494925963_1183091008456359936_n.jpg?_nc_cat=101&_nc_oc=AQkXDsimHahDSzxF7BS9NbBvgox8P-BAyPNh2DvJlOZdkZqhBm3KS206w5f7cw1PBneiFi6EtydeF5Gf1avxxUxS&_nc_ht=scontent.fsgn5-1.fna&oh=5092122e39724a586043169cf47d0696&oe=5E2FC72F", "Nguyễn Thanh Nhàn", 100, 1000, mDishyList);
//        Chef chef2 = new Chef("https://scontent.fsgn5-3.fna.fbcdn.net/v/t1.0-1/p320x320/70205972_1445859698901960_6020314693128683520_n.jpg?_nc_cat=111&_nc_oc=AQlqW0sH4rpBiS2IPFfCEaorz-_7CDmDvZCV3YdL5u0-dyJcqxhMRJMtySaDEMPjS-uGvtyeUqP4ZJT4328aZLhU&_nc_ht=scontent.fsgn5-3.fna&oh=ea35b802e27df0b8c9f7bc852baec68f&oe=5E023271", "Nguyễn Hoàng Bá Khánh", 60, 450, mDishyList);
//        Chef chef3 = new Chef("https://scontent.fsgn5-2.fna.fbcdn.net/v/t1.0-1/c0.0.320.320a/p320x320/10599638_1504520149789789_4487427289442049165_n.jpg?_nc_cat=107&_nc_oc=AQne8gyZOiKbnc5zvKNLPtE9JeuC3flPcvR_za52mOc-3TbE4W7PH4f7z6JNwOznFtH-JPIglXn769P5Kl4ORJ0e&_nc_ht=scontent.fsgn5-2.fna&oh=4a4dbfc66aa3a1fb5aeeefa754ce422f&oe=5E2E96E2", "Nguyễn Văn Lương", 20, 300, mDishyList);
//        mDishyFollowList = new ArrayList<>();
//        mDishyFollowList.add(new Dishy("Mì cay", "https://cdn.tgdd.vn/Files/2016/07/26/863246/cach-lam-mi-cay-3-cap-do-cho-ngay-mua-them-am-bung4.jpg", "18 phút", 2, "Trung bình", 230, chef1));
//        mDishyFollowList.add(new Dishy("Lẩu gà lá giang", "https://cdn.daynauan.info.vn/wp-content/uploads/2018/07/lau-ga-la-giang.jpg", "20 phút", 3, "Dễ", 200, chef1));
//        mDishyFollowList.add(new Dishy("Hamburger thịt bò", "https://images.pexels.com/photos/1199960/pexels-photo-1199960.jpeg?cs=srgb&amp;dl=burger-cheeseburger-close-up-1199960.jpg&amp;fm=jpg", "Dễ", 4, "Dễ", 500, chef2));
//        mDishyFollowList.add(new Dishy("Bánh tráng trộn", "https://i.ytimg.com/vi/8lNLepEuR8I/maxresdefault.jpg", "24 phút", 5, "Khó", 100, chef3));
//        updateUIRcvDishyFollow(mDishyFollowList);


    }

    private void updateUIRcvFoodPicks(List<Store> mBrandList) {
        if (mFoodPicksAdapter == null) {
            mFoodPicksAdapter = new FoodPicksAdapter(getContext(), mBrandList);
            mRcvFoodPicks.setAdapter(mFoodPicksAdapter);
            mFoodPicksAdapter.setmOnFoodPicksClickListener(new FoodPicksAdapter.OnFoodPicksClickListener() {
                @Override
                public void onClick(Store store) {
                    Intent intent = null;
                    if (store.totalStore == 1) {
                        intent = new Intent(getContext(), StoreActivity.class);
                        intent.putExtra(Constant.STORE, store);

                    } else {
                        intent = new Intent(getContext(), StoreListActivity.class);
                        intent.putExtra(Constant.STORE_LIST_TITLE, store.getName());
                    }
                    startActivity(intent);
                }
            });
        } else {
            mFoodPicksAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showProgress() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void setDataToRecyclerView(List<Store> StoreArrayList) {
        mStoreList.addAll(StoreArrayList);
        mFoodPicksAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResponseFailure(String throwable) {
        Log.e(TAG, throwable);
        Toast.makeText(getContext(), getString(R.string.communication_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showEmptyView() {
        mRcvFoodPicks.setVisibility(View.GONE);
        txtEmptyView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyView() {
        mRcvFoodPicks.setVisibility(View.VISIBLE);
        txtEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        storeListPresenter.onDestroy();
    }
}
