package com.murach.tasklist;

/**
 * Created by taylo on 11/24/2015.
 */

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;

public class TaskFrag extends Fragment{

    private ListView lvTask;
    private String currTabName;

    @Override
    public View onCreateView(LayoutInflater inflate, ViewGroup box, Bundle savedInstanceState){
        View fragView = inflate.inflate(R.layout.frag_list, box, false);

        lvTask = (ListView)fragView.findViewById(R.id.lvTask);

        TabHost tabGrab = (TabHost) box.getParent().getParent();
        currTabName = tabGrab.getCurrentTabTag();

        refreshList();

        return fragView;
    }

    public void refreshList(){
        Context contHere = getActivity().getApplicationContext();
        TaskListDB db = new TaskListDB(contHere);
        ArrayList<Task> tasks = db.getTasks(currTabName);

        TaskListAdapt adapt = new TaskListAdapt(contHere, tasks);
        lvTask.setAdapter(adapt);
    }

    @Override
    public void onResume(){
        super.onResume();
        refreshList();
    }
}
