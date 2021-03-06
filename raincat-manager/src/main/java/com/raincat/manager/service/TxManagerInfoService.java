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

package com.raincat.manager.service;

import com.raincat.common.entity.TxManagerServer;
import com.raincat.common.entity.TxManagerServiceDTO;
import com.raincat.manager.entity.TxManagerInfo;

import java.util.List;

/**
 * TxManagerInfoService.
 * @author xiaoyu
 */
public interface TxManagerInfoService {

    /**
     * 分布式事务的开关切换
     */
    void switchTransaction();
    /**
     * 业务端获取TxManager信息.
     *
     * @return TxManagerServer
     */
    TxManagerServer findTxManagerServer();


    /**
     * 服务端信息.
     *
     * @return TxManagerInfo
     */
    TxManagerInfo findTxManagerInfo();

    /**
     * 获取eureka上的注册服务.
     *
     * @return List TxManagerServiceDTO
     */
    List<TxManagerServiceDTO> loadTxManagerService();

}
