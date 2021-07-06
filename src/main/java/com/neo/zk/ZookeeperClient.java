package com.neo.zk;


import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

@Component
public class ZookeeperClient {
    private CuratorFramework curatorFramework = null;

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    public ZookeeperClient(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }

    /**
     * 创建节点
     *
     * @param path
     * @param data
     * @param createMode
     */
    public void save(String path, String data, CreateMode createMode) {
        try {
            curatorFramework.create().creatingParentsIfNeeded().withMode(createMode).forPath(path, data.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 查询节点信息
     *
     * @return
     */
    public String query(String path) {
        try {
            byte[] data = curatorFramework.getData().forPath(path);
            if (data != null && data.length > 0) {
                return new String(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
