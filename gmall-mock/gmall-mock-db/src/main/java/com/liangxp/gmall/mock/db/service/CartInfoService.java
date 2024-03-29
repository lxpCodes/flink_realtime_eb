package com.liangxp.gmall.mock.db.service;

import com.liangxp.gmall.mock.db.bean.CartInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 购物车表 用户登录系统时更新冗余 服务类
 * </p>
 *
 * @author zc
 * @since 2020-02-24
 */
public interface CartInfoService extends IService<CartInfo> {

    public void  genCartInfo(boolean ifClear);

}
