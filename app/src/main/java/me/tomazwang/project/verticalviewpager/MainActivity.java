package me.tomazwang.project.verticalviewpager;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements TabAdapter.OnItemClickListener, ViewPager.OnPageChangeListener {

    private String[] data;

    ViewPager viewPager;
    ListView tabs;
    private TabAdapter tabAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.vertical_viewPager);
        tabs = (ListView) findViewById(R.id.lv_tabs);

        initData();
        initView();
    }

    private void initData() {
        this.data = new String[]{
                "Page 1",
                "Page 2",
                "Page 3",
                "Page 4",
                "Page 5",
                "Page 6"
        };
    }


    private void initView() {
        PageAdapter viewPageAdapter = new PageAdapter(getSupportFragmentManager(), data);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.addOnPageChangeListener(this);

        if (tabs != null) {
            // landscape mode
            this.tabAdapter = new TabAdapter(data, tabs, this);
            tabs.setAdapter(tabAdapter);
            tabs.setDivider(null);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.swich_view:
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                } else {
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void selectItem(int position) {
        viewPager.setCurrentItem(position, true);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if(tabAdapter != null){
            tabAdapter.setCurrentSelected(position);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
