package com.example.pckosek.dovolley;

/**
 * Created by lizzie on 12/7/18.
 */

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.pckosek.dovolley.R;
public class MenuFragment extends ListFragment {
    String[] AndroidOS = new String[] { "Milk: $0.5","Soda: $0.75","Duck: $15","Chicken Wings: $10","Pork Buns: $3.25","Soup: $4.75"};
    int[] Version = new int[]{0,0,0,0,0,0};
    @Override

    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.list_fragment, container, false);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, AndroidOS);
        setListAdapter(adapter);

        return view;

    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        TextFragment txt = (TextFragment)getFragmentManager().findFragmentById(R.id.fragment2);
        txt.change(AndroidOS[position],"Amount : "+Version[position]);
        getListView().setSelector(android.R.color.holo_blue_dark);
    }

    public int updateItems(String name){
        System.out.println("AndroidOs:" + AndroidOS);
        for(int i = 0; i < AndroidOS.length; i++){
            String str = AndroidOS[i].substring(0, AndroidOS[i].indexOf(":"));
            if(str.equalsIgnoreCase(name)){
                Version[i]++;
                return Version[i];
            }
        }
        return 0;
    }
}