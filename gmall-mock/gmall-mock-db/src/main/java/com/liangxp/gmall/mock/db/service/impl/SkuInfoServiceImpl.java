package com.liangxp.gmall.mock.db.service.impl;

import com.liangxp.gmall.mock.db.bean.SkuInfo;
import com.liangxp.gmall.mock.db.mapper.SkuInfoMapper;
import com.liangxp.gmall.mock.db.service.SkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 库存单元表 服务实现类
 * </p>
 *
 * @author zc
 * @since 2020-02-23
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements SkuInfoService {


    public SkuInfo getSkuInfoById(List<SkuInfo> skuInfoList, Long skuId){
        for (SkuInfo skuInfo : skuInfoList) {
            if(skuInfo.getId().equals(skuId)){
                return skuInfo;
            }
        }
        return null;
    }

}
