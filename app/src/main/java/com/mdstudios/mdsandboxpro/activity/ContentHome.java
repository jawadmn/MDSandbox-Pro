package com.mdstudios.mdsandboxpro.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;

import com.mdstudios.mdsandboxpro.R;
import com.mdstudios.mdsandboxpro.drawer.DrawerControllerFragment;
import com.mdstudios.mdsandboxpro.drawer.ExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jawad on 26/08/14.
 */
public class ContentHome extends Activity implements ExpandableListView.OnChildClickListener, AdapterView.OnItemClickListener {
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    // Used to setup the drawer
    private DrawerControllerFragment mDrawerController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenthome_activity);

    //    initExpListView(); // Sets up the expanded list view

        // Initialize the Drawer Controller fragment
        initDrawerController();
    }

    private void initDrawerController(){
        // Get the DrawerController from the layout
        mDrawerController = (DrawerControllerFragment) getFragmentManager()
                .findFragmentById(R.id.drawer_fragment);

        // Tell the drawer to set itself up
        mDrawerController.setUpDrawer(
                (DrawerLayout) findViewById(R.id.drawer_layout)
        );
    }

    // Sets up the expanded list view
    private void initExpListView(){
        // get the listview
    //      expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Allow the activity to react to clicks on a child item in the list
        expListView.setOnChildClickListener(this);

        expListView.setOnItemClickListener(this);
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Top 250");
        listDataHeader.add("Now Showing");
        listDataHeader.add("Coming Soon..");

        // Adding child data
        List<String> top250 = new ArrayList<String>();
        top250.add("The Shawshank Redemption");
        top250.add("The Godfather");
        top250.add("The Godfather: Part II");
        top250.add("Pulp Fiction");
        top250.add("The Good, the Bad and the Ugly");
        top250.add("The Dark Knight");
        top250.add("12 Angry Men");

        List<String> nowShowing = new ArrayList<String>();
        nowShowing.add("The Conjuring");
        nowShowing.add("Despicable Me 2");
        nowShowing.add("Turbo");
        nowShowing.add("Grown Ups 2");
        nowShowing.add("Red 2");
        nowShowing.add("The Wolverine");

        List<String> comingSoon = new ArrayList<String>();
        comingSoon.add("2 Guns");
        comingSoon.add("The Smurfs 2");
        comingSoon.add("The Spectacular Now");
        comingSoon.add("The Canyons");
        comingSoon.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), top250); // Header, Child data
        listDataChild.put(listDataHeader.get(1), nowShowing);
        listDataChild.put(listDataHeader.get(2), comingSoon);
    }

    //--Called when a child item is clicked--
        //--Returns true if click was handled
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
        return false;
    }

    //--Called when a group item is clicked--
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    // --> In order to fully implement a navigation drawer, the following two methods must be used
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // Forward the action button click to the drawer listener
        if(mDrawerController.getDrawerToggle().onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Call syncState() from activity's onPostCreate to synchronize
        // the indicator with the state of the linked DrawerLayout after
        // onRestoreInstanceState has occurred.
        mDrawerController.getDrawerToggle().syncState();
        // Note: Indicator will NOT show without this
    }
}
