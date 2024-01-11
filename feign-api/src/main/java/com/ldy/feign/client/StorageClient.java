package com.ldy.feign.client;

import com.ldy.feign.entity.Storage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("storage-service")
public interface StorageClient {
    @PutMapping("/storage/goods")
    String updateGoods(@RequestBody Storage storage);
}
