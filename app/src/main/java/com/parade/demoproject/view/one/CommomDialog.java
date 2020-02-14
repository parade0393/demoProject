package com.parade.demoproject.view.one;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.parade.demoproject.R;
import com.parade.demoproject.listener.FragmentLifeListener;

/***
 *author: parade岁月
 *date:  2020/2/14 10:18
 *description：
 */
public class CommomDialog extends DialogFragment {

    private View view;
    private FragmentLifeListener lifeListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (getActivity() instanceof FragmentLifeListener){
            lifeListener = (FragmentLifeListener) getActivity();
        }
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onAttach"+"\n");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onCreate"+"\n");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_demo, container, false);
            ((TextView)view.findViewById(R.id.tv_flag)).setText("Dialog");
        }
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onCreateView"+"\n");
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onViewCreated"+"\n");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onActivityCreated"+"\n");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onCreateDialog"+"\n");
        }
        return super.onCreateDialog(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onStart"+"\n");
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onPause"+"\n");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onStop"+"\n");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onDestroyView"+"\n");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onDestroy"+"\n");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>onDetach"+"\n");
        }
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        if (lifeListener != null){
            lifeListener.sendContent("DialogFragment:==>show"+"\n");
        }
        super.show(manager, tag);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }
}
