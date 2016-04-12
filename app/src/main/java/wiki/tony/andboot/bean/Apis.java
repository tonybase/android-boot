package wiki.tony.andboot.bean;

import wiki.tony.andboot.AppEnv;

/**
 * Api 定义
 * <p/>
 * Created by Tony on 10/6/15.
 */
public class Apis {

    /**
     * 测试环境API
     */
    public static final String API_DEV = "https://api.github.com";

    /**
     * 生产环境API
     */
    public static final String API_PROD = "https://api.github.com";

    /**
     * API地址
     */
    public static final String BASE_API = AppEnv.ACTIVE_DEV ? API_DEV : API_PROD;

}

