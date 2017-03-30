package engineer.echo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import engineer.echo.base.DaggerStudentComponent;
import engineer.echo.base.Student;
import engineer.echo.base.StudentModule;

import javax.inject.Inject;

public class ScrollingActivity extends AppCompatActivity {
    @Inject
    Student student1;
    @Inject
    Student student2;
    @Bind(R.id.fab)
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scrolling);
        ButterKnife.bind(this);

        DaggerStudentComponent.builder().studentModule(new StudentModule()).build().inject(this);

        System.out.println("dagger student1:");
        System.out.println("time@" + student1.name + " address:" + student1.toString());
        System.out.println("dagger student2:");
        System.out.println("time@" + student2.name + " address@" + student2.toString());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @OnClick(value = {R.id.fab})
    public void onViewClick(View view) {
        switch (view.getId()) {
            case R.id.fab:
                Intent intent = new Intent(this, BaseActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_scrolling, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
