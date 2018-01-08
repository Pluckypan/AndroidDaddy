package engineer.echo.bigandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import engineer.echo.bigandroid.base.ui.EEScrollView;

public class WebActivity extends AppCompatActivity implements EEScrollView.EEScrollChangeListener {

    WebView webView;
    EEScrollView scrollView;
    View lineTop, lineRule;
    LinearLayout LFloat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebClient());
        webView.setWebChromeClient(new ChromeClinet());
        webView.loadUrl("http://www.1991th.com");

        scrollView = (EEScrollView) findViewById(R.id.scrollView);
        scrollView.setListener(this);

        lineTop = findViewById(R.id.lineTop);
        lineRule = findViewById(R.id.lineRule);
        LFloat = (LinearLayout) findViewById(R.id.LFloat);
    }

    private class ChromeClinet extends WebChromeClient {

    }

    private class WebClient extends WebViewClient {

    }

    @Override
    public void onEEChange(int l, int t, int oldl, int oldt) {
        int[] topLoc = new int[]{0, 0};
        lineTop.getLocationOnScreen(topLoc);

        int[] ruleLoc = new int[]{0, 0};
        lineRule.getLocationOnScreen(ruleLoc);

        if (ruleLoc[1] <= topLoc[1]) {
            LFloat.setTop(topLoc[1]);
        } else {
            LFloat.setTop(ruleLoc[1]);
        }
    }
}
