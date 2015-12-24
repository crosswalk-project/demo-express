package org.xwalk.core.demoexpress.basic;


import org.xwalk.core.XWalkActivity;
import org.xwalk.core.XWalkPreferences;
import org.xwalk.core.XWalkView;
import org.xwalk.core.demoexpress.R;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class XWalkViewWithMultiSurfaceViews extends XWalkActivity {
    private Button mResizeButton;
    private RelativeLayout root;
    private TextView textDes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        XWalkPreferences.setValue(XWalkPreferences.ANIMATABLE_XWALK_VIEW, false);
        setContentView(R.layout.activity_xwalk_view_with_multi_views);
    }

    @Override
    protected void onXWalkReady() {

        LinearLayout parent = (LinearLayout) findViewById(R.id.multi_texture_views);

        textDes = (TextView)findViewById(R.id.multiViews_des);
        textDes.setText("This sample demonstrates Multiple SurfaceViews can be shown" +
                " in order. A,B,C views are SurfaceViews, D,E,F views are WebViews, " +
                "the sort order of A,B,C are the same with D,E,F when rotate or " +
                "restore the screen.");
        mResizeButton = (Button) findViewById(R.id.run_resize);
        mResizeButton.setVisibility(View.GONE);
        root = (RelativeLayout) findViewById(R.id.root_views);

        for (int i = 0; i < 3; i++) {
            XWalkView xWalkView = new XWalkView(this, this);
            xWalkView.setX(i * 100);
            xWalkView.setY(30 + i * 70);
            xWalkView.load(null, String.format("<html><head><meta name='viewport' content='width=device-width'/></head>"
                    + "<body style='background-color: %s;'><h1>%s</h1></body></html>", i % 2 == 0 ? "white" : "grey", i == 0 ? "A" : i == 1 ?  "B" : "C"));
            root.addView(xWalkView, 200, 200);
        }

        for (int i = 3; i < 6; i++) {
            WebView webView = new WebView(this);
            webView.setX(i * 100);
            webView.setY(30 + i * 70);
            webView.loadData(String.format("<html><body style='background-color: %s'><h1>%s</h1></body></html>",
                    i % 2 == 0 ? "white" : "grey", i == 3 ? "D" : i == 4 ?  "E" : "F"), "text/html", "utf-8");
            root.addView(webView, 200, 200);
        }
        setContentView(parent);
    }
}
