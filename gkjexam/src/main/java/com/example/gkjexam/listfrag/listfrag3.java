package com.example.gkjexam.listfrag;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gkjexam.Activity.SuccessActivity;
import com.example.gkjexam.R;

/**
 * Created by 郭康杰 on 2017/8/17.
 */
public class listfrag3 extends Fragment {
    private boolean s =true;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(s) {
                Intent intent = new Intent(getActivity(), SuccessActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.firstfrag3, container, false);
        SharedPreferences gkjexam = getActivity().getSharedPreferences("gkjexam", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = gkjexam.edit();
        edit.putBoolean("judge",true);
        edit.commit();
        handler.sendEmptyMessageDelayed(0,3000);
        Button bt_register = (Button) view.findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s=false;
                Intent intent = new Intent(getActivity(), SuccessActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return view;
    }
}
