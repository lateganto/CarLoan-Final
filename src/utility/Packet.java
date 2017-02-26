package utility;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by salvatore on 07/10/15.
 */
public class Packet {

    private Map<String, Object> map = new HashMap<String, Object>();


    public Object get(String key) {
        return map.get(key);
    }

    public void set(String key, Object value) {
        map.put(key, value);
    }

    public void remove(String key) {
        map.remove(key);
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }
}
