package com.navigationexample;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by ajea on 16/05/17.
 */

public class NavigationManager {

    private static FragmentManager mFragmentManager;

    public NavigationManager(@NonNull FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    public void replaceFragment(Fragment fragment, int idContainer) {
        mFragmentManager.beginTransaction().replace(idContainer,fragment).commit();
    }

    public void addFragmentToBackStack(@NonNull Fragment fragment, int idContainer) {
        if(fragment != null && idContainer > 0){
            if (!exitsFragment(fragment.getClass().getName())) {
                FragmentTransaction ft = mFragmentManager.beginTransaction();
                ft.replace(idContainer, fragment);
                ft.addToBackStack(fragment.getClass().getName());
                ft.commit();
            }
        }
    }

    public void removeFragment(Fragment fragment) {
        if(fragment != null){
            mFragmentManager.beginTransaction().remove(fragment);
            mFragmentManager.beginTransaction().commit();
            mFragmentManager.popBackStack();
        }
    }

    public void navigateBack(Activity baseActivity) {
        if (mFragmentManager.getBackStackEntryCount() == 0) {
            baseActivity.finish();
        } else {
            mFragmentManager.popBackStackImmediate();
        }
    }

    public void openAsRoot(Fragment fragment,int idContainer) {
        if(fragment != null && idContainer > 0){
            popAllFragment();
            replaceFragment(fragment, idContainer);
        }
    }

    private void popAllFragment() {
        // Clear all back stack.
        int backStackCount = mFragmentManager.getBackStackEntryCount();
        for (int i = 0; i < backStackCount; i++) {
            // Get the back stack fragment id.
            int backStackId = mFragmentManager.getBackStackEntryAt(i).getId();
            mFragmentManager.popBackStack(backStackId, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        } /* end of for */
    }

    public void popFragments(String fragmentName){
        int backStackCount = mFragmentManager.getBackStackEntryCount();
        if(backStackCount > 0){
            for (int i = 1; i < backStackCount; i++) {
                mFragmentManager.popBackStackImmediate(fragmentName, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            }
        }
    }

    public boolean exitsFragment(String name) {
        boolean exist = false;

        for (Fragment fragment : mFragmentManager.getFragments()){
            if (fragment != null && fragment.isAdded() && fragment.getClass().getName().equals(name)) {
                exist = true;
                break;
            }
        }

        return exist;
    }
}
