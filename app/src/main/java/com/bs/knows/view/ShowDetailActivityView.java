package com.bs.knows.view;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.bs.knows.R;
import com.bs.knows.databinding.ActivityShowDetailBinding;
import com.bs.knows.model.InitListModel;
import com.bs.knows.model.ShowDetailModel;

import com.bs.knows.utils.StaticUtils;
import com.bs.knows.viewmodel.ShowDetailVM;
import com.leon.lfilepickerlibrary.LFilePicker;
import com.leon.lfilepickerlibrary.utils.Constant;

import java.util.List;
import java.util.Objects;


public class ShowDetailActivityView extends BaseActivtyView {

    private Intent intent;
    private String FilePath;
    private int REQUESTCODE_FROM_ACTIVITY = 1000;
    private ActivityShowDetailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_show_detail);
        intent = getIntent();
        ShowDetailVM showDetailVM = new ShowDetailVM(this, binding, intent);
        binding.setShowDetail(showDetailVM);

        initView();


        ShowDetailModel.showTaskDetail(this, binding, intent);


        if (intent.getStringExtra("from").equals("TeaTaskPlan")) {

            InitListModel.initUploadTaskListView(binding, this);

        } else if (!intent.getStringExtra("from").equals("finishChooseTask")
                && !intent.getStringExtra("from").equals("TeaTaskPlan")
                && !intent.getStringExtra("from").equals("StuTaskPlan")
                && !intent.getStringExtra("from").equals("StudentDetail")
                && !intent.getStringExtra("from").equals("TeacherDetail")) {

            InitListModel.InitChooseTaskStuListView(binding, this, intent);
            Log.d(TAG, "onCreate: InitChooseTaskStuListView");

        }


        binding.UploadFile.setOnClickListener(v -> getFile());

        binding.btnConformUpload.setOnClickListener(v -> {
            if (FilePath == null) {
                Toast.makeText(getApplicationContext(), "请选择上传文件", Toast.LENGTH_SHORT).show();
            } else if (binding.UploadFile.getText().equals("上传完成！")) {
                Toast.makeText(getApplicationContext(), "该文件已上传！", Toast.LENGTH_SHORT).show();
            } else {
                binding.UploadFile.setText("上传中...请不要退出！");
                ShowDetailModel.UploadFile(FilePath, binding);

            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUESTCODE_FROM_ACTIVITY) {
                List<String> list = data.getStringArrayListExtra(Constant.RESULT_INFO);
                if (!TextUtils.isEmpty(list.get(0))) {
                    binding.UploadFile.setText("已选择:" + list.get(0));
                    Toast.makeText(getApplicationContext(), "已选择：" + list.get(0), Toast.LENGTH_SHORT).show();
                    FilePath = list.get(0);
                }


            }
        }
    }


    private void getFile() {

        new LFilePicker()
                .withActivity(ShowDetailActivityView.this)
                .withTitle("选择文件")//标题文字
                .withTitleColor("#FF99CC")//文字颜色
                .withStartPath("/storage/emulated/0/")//指定初始显示路径
                .withIconStyle(Constant.ICON_STYLE_BLUE)
                .withRequestCode(REQUESTCODE_FROM_ACTIVITY)
                .withIsGreater(false)//过滤文件大小 小于指定大小的文件
                .withFileSize(100 * 1024 * 1024)//指定文件大小为500K
                .start();
    }


    private void initView() {
        StaticUtils.STU_FINISH_CHOOSE_TASK = false;
        switch (Objects.requireNonNull(intent.getStringExtra("from"))) {

            case "StudentDetail":
            case "chooseTaskStu":
                initNavBar(true, "学生详情", false);
                break;
            case "TeacherDetail":
                initNavBar(true, "教师详情", false);
                break;

            case "showTaskDetail":
            case "beganChooseTask":
            case "finishChooseTask":
            case "showTaskDetail_count3":
            case "checkUploadTaskDetail":
                initNavBar(true, "课题详情", false);
                break;

            case "TaskProgress":
                initNavBar(true, "设置教学计划", false);
                break;

            case "TeaTaskPlan":
            case "StuTaskPlan":
                initNavBar(true, "论文进度", false);
                break;


            default:
                return;
        }

        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(v -> onBackPressed());

    }

}
