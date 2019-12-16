package fpt.edu.cocshop.Fragment;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;

import fpt.edu.cocshop.R;


public class UserFragment extends Fragment {
    TextView nameTV;
    TextView mailTV;
    TextView idTV;
    ImageView photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        nameTV  = view.findViewById(R.id.personName);
//        mailTV = view.findViewById(R.id.mail);
//        idTV  = view.findViewById(R.id.id);
        photo = view.findViewById(R.id.avatar);
        Bundle bundle = getArguments();
        System.out.println("bundlesout"+bundle.getString("name"));
        if (bundle != null){

            nameTV.setText(bundle.getString("name"));
//            mailTV.setText(bundle.getString("mail"));
//            idTV.setText(bundle.getString("id"));

        }
//        Uri uriData = Uri.parse(bundle.getString("avatar"));
        System.out.println(bundle.getString("avatar")+"nhucc");
        Glide.with(this).load(bundle.getString("avatar")).into(photo);

        return view;
    }



}
