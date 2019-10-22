package com.example.dormitory_magician;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceFragment extends Fragment {
    private Button needHelpBtn;
    private Button helpOthersBtn;
    private NeedHelpFragment needHelpFragment;
    public static ServiceFragment newInstance(){
        return new ServiceFragment();
    }
    public ServiceFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.service_fragment, container, false);
        //TextView tv = (TextView)view.findViewById(R.id.service_text);
        needHelpBtn = (Button) view.findViewById(R.id.need_help_btn);
        helpOthersBtn = (Button) view.findViewById(R.id.help_others_btn);

        needHelpBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction transaction = fragmentManager.
                        beginTransaction();
                needHelpFragment=NeedHelpFragment.newInstance();
                transaction.replace(R.id.tabs, needHelpFragment);
                transaction.commit();
            }
        });
        return view;
    }

}
