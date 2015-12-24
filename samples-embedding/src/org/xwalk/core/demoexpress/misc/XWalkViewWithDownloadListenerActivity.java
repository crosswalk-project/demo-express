// Copyright (c) 2014 Intel Corporation. All rights reserved.
// Use of this source code is governed by a BSD-style license that can be
// found in the LICENSE file.

package org.xwalk.core.demoexpress.misc;



import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkDownloadListener;

import android.os.Bundle;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class XWalkViewWithDownloadListenerActivity extends XWalkActivity {
    public final static String CHROME_AGENT_TEXT = "Set Chrome UserAgent";
    public final static String DEFAULT_AGENT_TEXT = "Set Default UserAgent";
    public final static String CHROME_USER_AGENT = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36";
    private XWalkView mXWalkView;
    private TextView downloadText;
    private Button changeUserAgent;
    private String default_useragent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_download_listener);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    @Override
    protected void onXWalkReady() {
        downloadText = (TextView) findViewById(R.id.message_tv);
        changeUserAgent = (Button) findViewById(R.id.user_agent);
        changeUserAgent.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Button b = (Button)v;
                String s = b.getText().toString();
                if (s.equals(CHROME_AGENT_TEXT)) {
                    changeUserAgent.setText(DEFAULT_AGENT_TEXT);
                    mXWalkView.setUserAgentString(CHROME_USER_AGENT);
                    mXWalkView.load("http://m.baidu.com/", null);
                    downloadText.setText("UserAgent: " + CHROME_USER_AGENT);
                } else {
                    changeUserAgent.setText(CHROME_AGENT_TEXT);
                    mXWalkView.setUserAgentString(default_useragent);
                    mXWalkView.load("http://m.baidu.com/", null);
                    downloadText.setText("UserAgent: " + default_useragent);
                }
            }
        });
        mXWalkView.setDownloadListener(new XWalkDownloadListener(getApplicationContext()) {

            @Override
            public void onDownloadStart(String url, String userAgent,
                            String contentDisposition, String mimetype, long contentLength) {
                // TODO Auto-generated method stub
                // You can realize your down here.
                downloadText.setText(downloadText.getText() + "\n" +
                                    "url: " + url + "\n" +
                                    "userAgent: " + userAgent + "\n" +
                                    "contentDisposition: " + contentDisposition + "\n" +
                                    "mimeType: " + mimetype + "\n" +
                                    "contentLength: " + contentLength);
            }
        });
        mXWalkView.load("http://m.baidu.com/", null);
        default_useragent = mXWalkView.getUserAgentString();
        downloadText.setText("UserAgent: " + default_useragent);
    }

}
