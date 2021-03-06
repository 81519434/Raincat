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

package com.raincat.core.service.handler;

import com.raincat.common.bean.TxTransactionInfo;
import com.raincat.common.constant.CommonConstant;
import com.raincat.core.concurrent.threadlocal.CompensationLocal;
import com.raincat.core.concurrent.threadlocal.TxTransactionLocal;
import com.raincat.core.service.TxTransactionHandler;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 补偿发起者处理器
 * this is execute compensation.
 * @author xiaoyu
 */
@Component
public class StartCompensationHandler implements TxTransactionHandler {

    private final PlatformTransactionManager platformTransactionManager;

    @Autowired
    public StartCompensationHandler(final PlatformTransactionManager platformTransactionManager) {
        this.platformTransactionManager = platformTransactionManager;
    }

    /**
     * 补偿的时候，不走分布式事务处理.
     *
     * @param point point 切点
     * @param info  信息
     * @return Object
     * @throws Throwable ex
     */
    @Override
    public Object handler(final ProceedingJoinPoint point, final TxTransactionInfo info) throws Throwable {
        TxTransactionLocal.getInstance().setTxGroupId(CommonConstant.COMPENSATE_ID);
        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        TransactionStatus transactionStatus = platformTransactionManager.getTransaction(def);
        try {
            final Object proceed = point.proceed();
            platformTransactionManager.commit(transactionStatus);
            return proceed;
        } catch (Throwable e) {
            platformTransactionManager.rollback(transactionStatus);
            throw e;
        } finally {
            TxTransactionLocal.getInstance().removeTxGroupId();
            CompensationLocal.getInstance().removeCompensationId();
        }
    }
}
