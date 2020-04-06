package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ClipData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import com.example.chapter3.homework.R;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {
    private ArrayAdapter<Item> adapterItems;
    private ListView lvItems;
    private LottieAnimationView animationView;

    private OnItemSelectedListener listener;
    private AnimatorSet animatorSet;

    public interface OnItemSelectedListener {
        public void onItemSelected(Item i);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Create arraylist from item fixtures
        ArrayList<Item> items = Item.getItems();
        adapterItems = new ArrayAdapter<Item>(getActivity(),
                android.R.layout.simple_list_item_activated_1, items);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        animationView = view.findViewById(R.id.fade_out_view);

        lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapterItems);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position,
                                    long rowId) {
                // Retrieve item based on position
                Item i = adapterItems.getItem(position);
                // Fire selected event for item
                listener.onItemSelected(i);
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        //这里虽然加了，但是仍然是一次性的，没有用
        lvItems.setAlpha(0f);
        animationView.setAlpha(1f);


        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ObjectAnimator animator = ObjectAnimator.ofFloat(animationView,"alpha",1f,0f);
                animator.setDuration(1000);
                animator.setRepeatCount(1);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(lvItems,"alpha",0f,1f);
                animator2.setDuration(1000);
                animator2.setRepeatCount(1);
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator,animator2);
                animatorSet.start();
            }
        }, 5000);





    }
}
