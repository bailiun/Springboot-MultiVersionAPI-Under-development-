package org.bailiun.multipleversionscoexist.en;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DualMap<K> extends HashMap<K, List<String>> {

    /**
     * 向 key 对应的 List 中添加一个 value。
     * 如果 key 不存在则自动创建新的 List。
     */
    public List<String> put(K key, String value) {
        List<String> list = this.computeIfAbsent(key, k -> new ArrayList<>());
        list.add(value);
        return list;
    }
}

//public class DualMap<K,V extends List<String>> extends HashMap<K,V> {
//    @SuppressWarnings("unchecked")
//    public V put(K key, String value) {
//        if(this.containsKey(key)){
//            this.get(key).add(value);
//        }else {
//                super.put(key, (V) new ArrayList<String>() {{add(value);}});
//        }
//        return this.get(key);
//    }
//}
