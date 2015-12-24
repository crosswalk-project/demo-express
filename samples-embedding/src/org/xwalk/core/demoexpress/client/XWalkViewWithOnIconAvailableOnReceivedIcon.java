package org.xwalk.core.demoexpress.client;


import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.demoexpress.R;

public class XWalkViewWithOnIconAvailableOnReceivedIcon extends XWalkActivity {
    private XWalkView mXWalkView;
    private TextView mTitleText;
    private ImageView mFavicon;
    private Button changeWebsite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_oniconavailable_onreceivedicon);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview_embedding);
    }

    class TestXWalkUIClientBase extends XWalkUIClient {

        public TestXWalkUIClientBase(XWalkView arg0) {
            super(arg0);
        }

        @Override
        public void onIconAvailable(XWalkView view, String url, Message msg) {
            mTitleText.setText("onIconAvailable() is invoked. ");
            msg.sendToTarget();
            super.onIconAvailable(view, url, msg);
        }

        @Override
        public void onReceivedIcon(XWalkView view, String url, Bitmap icon) {
            mTitleText.setText(mTitleText.getText() + "onReceivedIcon() is invoked. The favicon is");
            mFavicon.setImageBitmap(icon);
            super.onReceivedIcon(view, url, icon);
        }
    }

    @Override
    protected void onXWalkReady() {

        mXWalkView.setUIClient(new TestXWalkUIClientBase(mXWalkView));

        XWalkPreferences.setValue(XWalkPreferences.SUPPORT_MULTIPLE_WINDOWS, true);
        XWalkPreferences.setValue(XWalkPreferences.REMOTE_DEBUGGING, true);
        XWalkPreferences.setValue(XWalkPreferences.JAVASCRIPT_CAN_OPEN_WINDOW, true);
        mTitleText = (TextView) findViewById(R.id.message_tv);
        mFavicon = (ImageView) findViewById(R.id.imageView1);
        changeWebsite = (Button) findViewById(R.id.change_website);
        changeWebsite.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Button b = (Button)v;
                String s = b.getText().toString();
                if (s.equals("Load Sina")) {
                    changeWebsite.setText("Load Baidu");
                    mXWalkView.load("http://sina.cn/", null);
                } else {
                    changeWebsite.setText("Load Sina");
                    mXWalkView.load("http://m.baidu.com/", null);
                }

            }
        });
        mXWalkView.load("http://m.baidu.com/", null);
    }
}
