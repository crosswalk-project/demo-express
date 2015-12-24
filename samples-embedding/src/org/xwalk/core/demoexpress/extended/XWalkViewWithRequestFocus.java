package org.xwalk.core.demoexpress.extended;


import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithRequestFocus extends XWalkActivity implements MessageInfoXWalkView.MessageListener{

    private MessageInfoXWalkView mXWalkView;

    private TextView tv;

    private Button hb;

    private EditText et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_request_focus);
    }

    @Override
    public void onMessageSent(String msg) {
        if(null != tv){
            String combineMsg = tv.getText().toString();
            if (0 == combineMsg.length()){
                combineMsg = "requestFocus works. Focus Changed:" + msg;
            }else{
                combineMsg += "->" + msg;
            }
            tv.setText(combineMsg);
        }
    }

    @Override
    protected void onXWalkReady() {
        mXWalkView = (MessageInfoXWalkView) findViewById(R.id.win_changed_xwalk_view);
        mXWalkView.setMessageListener(this);
        mXWalkView.load("http://www.baidu.com", null);
        tv = (TextView)findViewById(R.id.windows_focus_msg_label);
        tv.setTextColor(Color.GREEN);
        et = (EditText)findViewById(R.id.edit_text);

        hb = (Button)findViewById(R.id.switch_focus_button);
        hb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et.isFocused()) {
                    mXWalkView.requestFocus();
                }else{
                    et.requestFocus();
                    onMessageSent("EditText");
                }
            }
        });
    }
}
