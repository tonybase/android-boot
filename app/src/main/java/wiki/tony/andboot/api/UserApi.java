package wiki.tony.andboot.api;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import wiki.tony.andboot.data.entity.User;

/**
 * Created by Tony on 3/10/16.
 */
public interface UserApi {

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<User>> loadUsers(
            @Path("owner") String owner, @Path("repo") String repo);

}
