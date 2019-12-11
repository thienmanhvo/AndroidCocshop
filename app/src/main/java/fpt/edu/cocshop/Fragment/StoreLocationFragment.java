package fpt.edu.cocshop.Fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import de.hdodenhof.circleimageview.CircleImageView;
import fpt.edu.cocshop.R;

public class StoreLocationFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private View mView;
    private MapView mMapView;
    private static final String TAG = "StoreLocationFragment";
    private CircleImageView mMarkerImageView;
    private View mCustomMarkerView;

    public static StoreLocationFragment newInstance() {
        StoreLocationFragment fragment = new StoreLocationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mMapView = mView.findViewById(R.id.map_view);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();

        mMapView.getMapAsync(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_store_location, container, false);
        mCustomMarkerView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_view_marker, null);
        mMarkerImageView = (CircleImageView) mCustomMarkerView.findViewById(R.id.profile_image);
        return mView;

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady() called with");
        mMap = googleMap;
        LatLng sydney = new LatLng(10.806941, 106.788891);

        LatLng mDummyLatLng = new LatLng(10.807871, 106.785478);
        MapsInitializer.initialize(getActivity());
        addCustomMarker(R.drawable.custom_mark_blue, mDummyLatLng);
        addCustomMarker(R.drawable.custom_mark_red, sydney);
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(sydney)      // Sets the center of the map to Mountain View
                .zoom(17)                   // Sets the zoom
                //.bearing(90)                // Sets the orientation of the camera to east
                // .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }

    private void addCustomMarker(@DrawableRes int pointerId, final LatLng latLng) {
        Log.d(TAG, "addCustomMarker()");
        if (mMap == null) {
            return;
        }

        // adding a marker on map with image from  drawable
       /* mMap.addMarker(new MarkerOptions()
                .position(mDummyLatLng)
                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, R.drawable.avatar))));*/

        // adding a marker with image from URL using glide image loading library
        mCustomMarkerView.setBackgroundResource(pointerId);
        Picasso.get()
                .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRKRaly5PfHhWcufq8HuSf3MLHj-wLbjFVjQvjIYmkvJr_sbnUfHQ&s")
                .resize(30, 30)
                .error(R.mipmap.ic_launcher)
                .placeholder(R.drawable.ic_launcher_background)
                //.resize(100,100)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        mMap.addMarker(new MarkerOptions()
                                .position(latLng)
                                .icon(BitmapDescriptorFactory.fromBitmap(getMarkerBitmapFromView(mCustomMarkerView, bitmap))));
                       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13f));

                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {
                        Log.e("PICASSO", e.getMessage());
                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });
    }

    private Bitmap getMarkerBitmapFromView(View view, @DrawableRes int resId) {

        mMarkerImageView.setImageResource(resId);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }

    private Bitmap getMarkerBitmapFromView(View view, Bitmap bitmap) {

        mMarkerImageView.setImageBitmap(bitmap);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        canvas.drawColor(Color.WHITE, PorterDuff.Mode.SRC_IN);
        Drawable drawable = view.getBackground();
        if (drawable != null)
            drawable.draw(canvas);
        view.draw(canvas);
        return returnedBitmap;
    }

    public Bitmap loadBitmapFromView(View view, Bitmap bitmap) {
        mMarkerImageView.setImageBitmap(bitmap);
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        return returnedBitmap;
    }
}