package engineer.echo.bigandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.mancj.slideup.SlideUp;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initView();
    }

    SlideUp slideUp;

    private void initView() {
        View view = View.inflate(this, R.layout.layout_popup, null);
//        View view = findViewById(R.id.tvPop);
        slideUp = new SlideUp.Builder(view).withStartState(SlideUp.State.HIDDEN)
                .withStartGravity(Gravity.BOTTOM)
                .build();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, DragSwipeActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.action_webview) {
            Intent intent = new Intent(this, JDPayActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_web) {
            Intent intent = new Intent(this, WebActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_slideup) {
            slideUp.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
