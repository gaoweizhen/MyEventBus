package com.gaoweizhen.myeventbus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.gaoweizhen.myeventbus.bean.EventBean;
import com.gaoweizhen.myeventbus.myevent.MyEventBus;
import com.gaoweizhen.myeventbus.myevent.Subscrible;
import com.gaoweizhen.myeventbus.myevent.ThreadMode;

/**
*日期:2019/5/25
 * 时间:11:37
 * 作者:gaoweizhen
*类描述：
*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        首先注册EventBus,为了区别我们用MyEventBus
        MyEventBus.getDefault().register(this);
        CeShiActivity.launch(this);
    }

//    定义一个调用的方法
    @Subscrible(threadMode = ThreadMode.MAIN)
    public void getMessage(EventBean eventBean){
        Toast.makeText(MainActivity.this,eventBean.toString(),Toast.LENGTH_SHORT).show();
        Log.e("main", eventBean.toString());
    }
}
