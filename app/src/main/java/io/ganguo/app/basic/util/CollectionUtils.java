package io.ganguo.app.basic.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

/**
 * Created by Wilson on 14-8-21.
 */
public class CollectionUtils {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isEmptyMap(Map<?, ?> map) {
        return map == null || isEmpty(map.keySet());
    }
}
