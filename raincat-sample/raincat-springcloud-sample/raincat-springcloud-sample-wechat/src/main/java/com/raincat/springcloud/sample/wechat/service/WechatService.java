/*
 *
 * Copyright 2017-2018 549477611@qq.com(xiaoyu)
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, see <http://www.gnu.org/licenses/>.
 *
 */
package com.raincat.springcloud.sample.wechat.service;

import com.raincat.springcloud.sample.wechat.entity.Wechat;

/**
 * @author xiaoyu
 */
public interface WechatService {

    /**
     * 微信付款
     *
     * @param wechat 实体对象
     * @return rows
     */
    int payment(Wechat wechat);

    /**
     * 支付失败
     */
    void payFail();

    /**
     * 支付超时
     *
     * @param wechat 实体对象
     */
    void payTimeOut(Wechat wechat);

    /**
     * 同时调用支付宝失败
     *
     * @param wechat 实体对象
     */
    void wxAliFail(Wechat wechat);

    void wxFor(int i);
}
