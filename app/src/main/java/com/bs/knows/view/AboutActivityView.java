package com.bs.knows.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityAboutBinding;
import com.bs.knows.databinding.ActivityAboutViewBinding;
import com.bs.knows.viewmodel.AboutVM;

public class AboutActivityView extends BaseActivtyView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAboutViewBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_about_view);
        AboutVM aboutVM=new AboutVM();
        binding.setAboutview(aboutVM);
        initNavBar(true,"关于",false);

    }
}
