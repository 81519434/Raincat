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

package com.raincat.common.bean;

import com.raincat.common.enums.PropagationEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * TxTransactionInfo.
 * @author xiaoyu
 */
@AllArgsConstructor
public class TxTransactionInfo {

    /**
     * 补偿方法对象.
     */
    @Getter
    private TransactionInvocation invocation;

    /**
     * 分布式事务组.
     */
    @Getter
    private String txGroupId;

    /**
     * 事务补偿id.
     */
    @Getter
    private String compensationId;

    /**
     * 事务等待时间.
     */
    @Getter
    private int waitMaxTime;
    /**
     * 是否为本地调用，也就是说不经过controller层，无http请求。如定时任务。
     */
    @Getter
    private boolean isLocalInvoke;

    @Getter
    private PropagationEnum propagationEnum;


}
