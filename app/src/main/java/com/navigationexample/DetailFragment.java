package com.navigationexample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ajea on 10/05/17.
 */

public class DetailFragment extends Fragment {

    public static DetailFragment newInstance(){
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.detail_fragment, container, false);

        return mView;
    }


}
