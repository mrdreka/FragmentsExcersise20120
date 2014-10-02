package com.hound.vision.fragmentsexcersise;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends FragmentActivity
        implements MenuFragment.OnMenuFragmentSelectedListener{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check for the right fragment Layout
        if (findViewById(R.id.fragment_container) !=null) {

            //if we are restoring from a earlier state then do nothing
            if (savedInstanceState != null) {
                return;
            }
           //new fragment to create and place in active layout
            MenuFragment firstFragment = new MenuFragment();

            //In case of an Intent, not important this time

            firstFragment.setArguments(getIntent().getExtras());

            //finally ad the fragment to the "fragment_container"
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onMenuFragmentSelected(int position) {

        ContentActivity contentFrag = (ContentActivity)
                getSupportFragmentManager().findFragmentById(R.id.content_fragment);
        if(contentFrag !=null){
            //if true we are in two panel layout

            //call a method in the contentFragment to update
            contentFrag.updateContentView(position);
        }
        else{
            // we are in one pane layout
            // Create fragment and give it an argument specifying the article it should show
            ContentActivity newFragment = new ContentActivity();
            Bundle args = new Bundle();
            args.putInt(ContentActivity.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.fragment_container, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }

    }
}
