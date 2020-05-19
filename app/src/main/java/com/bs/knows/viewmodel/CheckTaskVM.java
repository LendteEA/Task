package com.bs.knows.viewmodel;

import android.content.Context;

import com.bs.knows.databinding.ActivityCheckTaskViewBinding;

public class CheckTaskVM {
    private ActivityCheckTaskViewBinding binding;
    private Context context;

    public CheckTaskVM(ActivityCheckTaskViewBinding binding, Context context) {
        this.binding = binding;
        this.context = context;
    }
}
