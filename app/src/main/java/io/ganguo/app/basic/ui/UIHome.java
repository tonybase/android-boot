package io.ganguo.app.basic.ui;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import io.ganguo.app.basic.R;
import io.ganguo.app.basic.common.UIHelper;
import io.ganguo.app.basic.core.http.HttpFactory;
import io.ganguo.app.basic.core.http.api.HttpService;
import io.ganguo.app.basic.core.http.api.StringHttpListener;


public class UIHome extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_home);

        findViewById(R.id.btn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpService http = HttpFactory.getHttpService();
                http.doGet("http://www.baidu.com", null, new StringHttpListener() {
                    @Override
                    public void onSuccess(String content) {
                        UIHelper.toastMessage(UIHome.this, content);
                    }

                    @Override
                    public void onFailure(String error, Throwable e) {
                        UIHelper.toastMessage(UIHome.this, error);
                    }
                }, 5000);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.uihome, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
