package cn.itcast.storage.web;

import cn.itcast.storage.entity.Storage;
import cn.itcast.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author 虎哥
 */
@RestController
@RequestMapping("storage")//storage前面不要加“/”，会报错找不到路径
public class StorageController {

    @Autowired
    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    /**
     * 扣减库存
     * @param code 商品编号
     * @param count 要扣减的数量
     * @return 无
     */
    @PutMapping("/{code}/{count}")
    public ResponseEntity<Void> deduct(@PathVariable("code") String code,@PathVariable("count") Integer count){
        storageService.deduct(code, count);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/goods")
    public String updateGoods(@RequestBody Storage storage){
        Storage storageOld = storageService.lambdaQuery()
                .eq(Storage::getCommodityCode, storage.getCommodityCode())
                .one();
        storage.setCount(storageOld.getCount()-
                storage.getCount());
        storageService.lambdaUpdate()
                .eq(Storage::getCommodityCode,storage.getCommodityCode())
                .update(storage);
        return "货物数量更新成功!";
    }

}
