package engineer.echo.bigandroid;

import android.net.http.SslError;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class JDPayActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jdpay);
        webView = (WebView) findViewById(R.id.webView);

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setGeolocationDatabasePath(getFilesDir().getPath());


        webView.setWebViewClient(new FHWebClient());
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        String url = "http://presentbox.jp/";
        //"http://jdpaydemo.jd.com/payStart.htm"
        webView.loadUrl(url);
    }

    class FHWebClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            //super.onReceivedSslError(view, handler, error);
            Log.d("Plucky", error.toString());
            handler.proceed();
        }
    }
}
