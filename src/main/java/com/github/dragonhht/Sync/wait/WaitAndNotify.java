package com.github.dragonhht.Sync.wait;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用wait()、notify()和notifyAll()实现同步.
 *
 * @author: huang
 * @Date: 2019-2-28
 */
public class WaitAndNotify {

    /**
     * 模拟存储的队列.
     */
    @Data
    private class Storage {
        private int maxSize;
        private List<String> data;

        public Storage(int maxSize) {
            this.maxSize = maxSize;
            this.data = new LinkedList<>();
        }

        /**
         * 添加数据.
         */
        public synchronized void add(String data) {
            while (this.data.size() == maxSize) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            this.data.add(data);
            notifyAll();
        }
    }

}
