package wiki.tony.andboot.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import wiki.tony.andboot.R;

/**
 * Created by Tony on 3/14/16.
 */
public class MainMenuView extends FrameLayout implements View.OnClickListener {

    View action_settings;

    public MainMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView();
    }

    private void initView() {
        View.inflate(getContext(), R.layout.view_main_menu, this);

        action_settings = findViewById(R.id.action_settings);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_settings:
                actionSettings();
                break;
        }
    }

    private void actionSettings() {

    }
}
