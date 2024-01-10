package cn.itcast.storage.service;

import cn.itcast.storage.entity.Storage;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StorageService extends IService<Storage> {

    /**
     * 扣除存储数量
     */
    void deduct(String commodityCode, int count);
}