package org.xwalk.core.demoexpress.client;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkResourceClient;
import org.xwalk.core.XWalkUIClient;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.widget.TextView;

public class XWalkViewWithRedirection extends XWalkActivity{

    private XWalkView mXWalkView;
    private TextView state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xwalk_view_with_redirection);
        mXWalkView = (XWalkView) findViewById(R.id.xwalkview);
    }

    class UIResourceClient extends XWalkResourceClient{

        public UIResourceClient(XWalkView view) {
            super(view);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onProgressChanged(XWalkView view, int progressInPercent) {
            // TODO Auto-generated method stub
            super.onProgressChanged(view, progressInPercent);
            updateLoadingState();
        }
    }


    class UIClient extends XWalkUIClient{

        public UIClient(XWalkView view) {
            super(view);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onPageLoadStopped(XWalkView view, String url,
                LoadStatus status) {
            // TODO Auto-generated method stub
            super.onPageLoadStopped(view, url, status);
            updateLoadingState();
        }

        @Override
        public void onPageLoadStarted(XWalkView view, String url) {
            // TODO Auto-generated method stub
            super.onPageLoadStarted(view, url);
            updateLoadingState();
        }
    }

    private void updateLoadingState() {
        // TODO Auto-generated method stub
        String s = "http://www.cnn.com/ ==> " + mXWalkView.getUrl();
        state.setText(s);
    }

    @Override
    protected void onXWalkReady() {

        state = (TextView) findViewById(R.id.message_tv);

        mXWalkView.setResourceClient(new UIResourceClient(mXWalkView));
        mXWalkView.setUIClient(new UIClient(mXWalkView));
        mXWalkView.load("http://www.cnn.com/", null);
    }
}