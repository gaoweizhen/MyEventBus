package com.gaoweizhen.myeventbus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.gaoweizhen.myeventbus.bean.EventBean;
import com.gaoweizhen.myeventbus.myevent.MyEventBus;
/**
*日期:2019/5/25
 * 时间:16:07
 * 作者:gaoweizhen
*类描述：发送eventbus的类
*/
public class CeShiActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn;
public static void launch(Context context){
    Intent intent = new Intent(context, CeShiActivity.class);
    context.startActivity(intent);
}
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ce_shi);
        initView();

    }

    private void initView() {
        btn = (Button) findViewById(R.id.btn);

        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:
//        调用MyEventBus。发送消息
                MyEventBus.getDefault().post(new EventBean("张三","测试数据"));
                break;
        }
    }
}
