package com.pangw.java;

import java.util.*;

/**
 * @Auther: pangw
 * @Date: 2018/7/31 09:43
 * @Description: java 简单实现负载均衡
 * 方法： 轮训法   随机法  加权随机 加权轮训
 */
public class javaLoadBalance {
    public static void main(String[] args) {


    }

    /**
     * @Auther: pangw
     * @Date: 2018/7/31 上午10:14
     * @Description: 加权轮训
     */
    static class WeightRounRabin {
        private static Integer pos = 0;

        public static String getServer() {
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);

            Set<String> keySet = serverMap.keySet();
            List<String> keyList = new ArrayList<String>();

            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String server = iterator.next();
                int weight = serverMap.get(server);
                for (int i = 0; i < weight; i++) {
                    keyList.add(server);
                }
            }
            String server;
            synchronized (pos) {
                if (pos > keySet.size()) {
                    pos = 0;
                }
                server = keyList.get(pos);
                pos++;
            }
            return server;
        }
    }


    /**
     * @Auther: pangw
     * @Date: 2018/7/31 上午10:13
     * @Description: 加权随机
     */
    static class WeightRandom {
        public static String getServer() {
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);

            Set<String> keySet = serverMap.keySet();
            List<String> keyList = new ArrayList<String>();

            Iterator<String> iterator = keySet.iterator();
            while (iterator.hasNext()) {
                String server = iterator.next();
                int weight = serverMap.get(server);
                for (int i = 0; i < weight; i++) {
                    keyList.add(server);
                }
            }

            java.util.Random random = new java.util.Random();
            int pos = random.nextInt(keyList.size());
            return keyList.get(pos);
        }


    }


    /**
     * @Auther: pangw
     * @Date: 2018/7/31 上午10:05
     * @Description: 随机法
     */
    static class Random {
        public static String getServer() {
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);
            Set<String> keySet = serverMap.keySet();
            List<String> keyList = new ArrayList<String>();
            keyList.addAll(keySet);
            java.util.Random random = new java.util.Random();
            int pos = random.nextInt(keyList.size());
            return keyList.get(pos);
        }


    }


    /**
     * @Auther: pangw
     * @Date: 2018/7/31 上午9:59
     * @Description: 轮训法
     */
    static class RoundRobin {
        private static Integer pos = 0;

        public static String getServer() {
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);

            Set<String> keySet = serverMap.keySet();

            List<String> keyList = new ArrayList<String>();

            keyList.addAll(keySet);

            String server;
            synchronized (pos) {
                if (pos > keySet.size()) {
                    pos = 0;
                }
                server = keyList.get(pos);
                pos++;
            }
            return server;
        }
    }


    /**
     * @Description: 服务器地址池
     * @param:
     * @return:
     * @date: 2018/7/31 上午9:47
     */
    static class IpMap {
        public static HashMap<String, Integer> serverWeightMap = new HashMap<String, Integer>();

        static {
            serverWeightMap.put("192.168.30.201", 1);
            serverWeightMap.put("192.168.30.202", 2);
            serverWeightMap.put("192.168.30.203", 3);
            serverWeightMap.put("192.168.30.204", 4);
            serverWeightMap.put("192.168.30.205", 2);
            serverWeightMap.put("192.168.30.206", 1);
            serverWeightMap.put("192.168.30.207", 5);
        }

    }

}
