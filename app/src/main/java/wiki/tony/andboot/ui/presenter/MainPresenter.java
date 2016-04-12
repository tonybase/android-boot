package wiki.tony.andboot.ui.presenter;


import java.util.List;

import wiki.tony.andboot.data.entity.User;

/**
 * Created by Tony on 3/17/16.
 */
public interface MainPresenter {
    void setView(View view);

    void loadUsers();

    interface View {
        void onUserLoaded(List<User> users);

        void onUserLoadedError(Throwable error);
    }
}
