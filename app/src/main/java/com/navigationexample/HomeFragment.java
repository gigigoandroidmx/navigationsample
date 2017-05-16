package com.navigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by ajea on 10/05/17.
 */

public class HomeFragment extends Fragment implements View.OnClickListener{

    public static HomeFragment newInstance(){
        HomeFragment mFragment = new HomeFragment();
        return mFragment;
    }

    private Button mButtomDetail;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.home_fragment, container, false);
        mButtomDetail = (Button) mView.findViewById(R.id.bt_home_detail);
        mButtomDetail.setOnClickListener(this);

        return mView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bt_home_detail:
                getNavigationManager().addFragmentToBackStack(DetailFragment.newInstance(), getFragmentIdContainer());
                break;
        }
    }

    public NavigationManager getNavigationManager(){
        return ((MainActivity)getActivity()).getNavigationManager();
    }

    public int getFragmentIdContainer(){
        return ((MainActivity)getActivity()).getIdFragmentContainer();
    }

}
