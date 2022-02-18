package interview;

import java.util.*;

public class MainHuawei {
    /**
     * 每xxx次操作后，清理失效的缓存
     */
    final int AfterOperateTimesClean = 10_000;

    private Map<String, LinkedList<String>> cache = new HashMap<>();
    //对应每一个缓存，保存其过期时间
    private Map<String, LinkedList<Long>> cacheExpire = new HashMap<>();

    public boolean lpush(String key, String value){
        return  lpush(key, value,null);
    }
    public boolean lpush(String key, String value, Date expired) {
        LinkedList<String> values = cache.getOrDefault(key, new LinkedList<String>());
        LinkedList<Long> valuesExpire = cacheExpire.getOrDefault(key,new LinkedList<Long>());

        values.addFirst(value);
        if(expired==null){
            valuesExpire.addFirst(-1L);
        }else{
            valuesExpire.addFirst(expired.getTime());
        }
        if (values.size() == 1) {
            cache.put(key, values);
            cacheExpire.put(key,valuesExpire);
        }
        return true;
    }

    public boolean rpush(String key, String value) {
        return  rpush(key, value,null);
    }

    public boolean rpush(String key, String value, Date expired) {
        LinkedList<String> values = cache.getOrDefault(key, new LinkedList<String>());
        LinkedList<Long> valuesExpire = cacheExpire.getOrDefault(key,new LinkedList<Long>());

        values.addLast(value);
        if(expired==null){
            valuesExpire.addLast(-1L);
        }else{
            valuesExpire.addLast(expired.getTime());
        }

        if (values.size() == 1) {
            cache.put(key, values);
            cacheExpire.put(key,valuesExpire);
        }
        return true;
    }

    public String lpop(String key) {
        LinkedList<String> values = cache.get(key);
        if (values.size() == 0) {
            throw new RuntimeException("No Cache！");
        }
        String  value = values.removeFirst();
        Long expireT = cacheExpire.get(key).removeFirst();
        if(!expire(expireT)){
            return value;
        }
        throw new RuntimeException("key expired");
    }

    public String rpop(String key) {
        LinkedList<String> values = cache.get(key);
        if (values.size() == 0) {
            throw new RuntimeException("No Cache！");
        }
        String value = values.removeLast();
        Long expireT = cacheExpire.get(key).removeLast();
        if(!expire(expireT)){
            return value;
        }
        throw new RuntimeException("key expired");
    }

    public void whencleanclean(){
        //定时清理一下空闲的key
    }

    public List<String> getCache(String key) {
        LinkedList<String> values = cache.getOrDefault(key, new LinkedList<String>());
        LinkedList<Long> expired = cacheExpire.getOrDefault(key,new LinkedList<Long>());
        Iterator<String> valIt = values.iterator();
        Iterator<Long> expIt = expired.iterator();
        while (expIt.hasNext()){
            Long expTemp = (Long) expIt.next();
            valIt.next();
            if(expire(expTemp)){
                expIt.remove();
                valIt.remove();
            }
        }
        return values;
    }

    private boolean expire(Long expireT){
        return !(System.currentTimeMillis()<=expireT || expireT.longValue() ==-1L);
    }

    public int keySize() {
        return cache.keySet().size();
    }

    public int listSize(String key) {
        return getCache(key).size();
    }
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for (String temp : cache.keySet()) {
            List<String>values = getCache(temp);
            sb.append("key"+temp+" values:"+values);
        }
        return  sb.toString();
    }

    public static void main(String[] args) throws InterruptedException {
        /**
         * key1 value1 value2 value3
         */
        MainHuawei test = new MainHuawei();
        System.out.println(test);

        test.lpush("key1","value2");
        System.out.println(test);

        test.lpush("key1","value1");
        System.out.println(test);

        test.rpush("key1","value3");
        System.out.println(test);

        //1s后过期
        test.lpush("key1","value1E", new Date(System.currentTimeMillis()+1000));
        test.rpush("key1","value11E", new Date(System.currentTimeMillis()+1000));
        test.lpush("key1","value2E", new Date(System.currentTimeMillis()+2000));
        test.rpush("key1","value12E", new Date(System.currentTimeMillis()+2000));
        System.out.println(test);
        Thread.sleep(1500);
        System.out.println("1.5s 后"+test);

        Thread.sleep(3000);
        System.out.println("3s 后"+test);



    }

}
