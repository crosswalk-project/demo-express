package org.xwalk.core.demoexpress.extended;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithLongClick extends XWalkActivity implements MessageInfoXWalkView.MessageListener{

    private MessageInfoXWalkView mXWalkView;

    private TextView listener_tv;

    private TextView perform_tv;

    private Button hb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_long_click);
    }

    @Override
    public void onMessageSent(String msg) {
        if(null != perform_tv){
            perform_tv.setText(msg);
        }
    }

    @Override
    protected void onXWalkReady() {
        listener_tv = (TextView)findViewById(R.id.listener_msg_label);
        listener_tv.setTextColor(Color.GREEN);

        perform_tv = (TextView)findViewById(R.id.perform_msg_label);
        perform_tv.setTextColor(Color.GREEN);

        mXWalkView = (MessageInfoXWalkView) findViewById(R.id.win_changed_xwalk_view);
        mXWalkView.setMessageListener(this);

        mXWalkView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                listener_tv.setText("OnLongClickListener is triggered. view:" + v.getClass());
                return false;
            }
        });
        mXWalkView.load("http://www.baidu.com", null);

        hb = (Button)findViewById(R.id.invoke_button);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button)v;
                String s = b.getText().toString();
                if (s.equals("PerformLongClick")) {
                    b.setText("Clear");
                    mXWalkView.performLongClick();
                } else {
                    b.setText("PerformLongClick");
                    listener_tv.setText("");
                    perform_tv.setText("");
                }
            }
        });
    }
}
