package com.mangosystem.rep.web.common;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseService {

	public final Logger logger = Logger.getLogger(this.getClass());


	@Resource(name="transactionManager")
	protected DataSourceTransactionManager transactionManager;

	protected void setTxManager(DataSourceTransactionManager transactionManager) {
		this.transactionManager = transactionManager;
	}


	/**
	 * Representation of the status of a transaction
	 * 
	 * @param propagation
	 * <ul>
	 * <li>PROPAGATION_REQUIRED : 0</li>
	 * <li>PROPAGATION_SUPPORTS : 1</li>
	 * <li>PROPAGATION_MANDATORY : 2</li>
	 * <li>PROPAGATION_REQUIRES_NEW : 3</li>
	 * <li>PROPAGATION_NOT_SUPPORTED : 4</li>
	 * <li>PROPAGATION_NEVER : 5</li>
	 * <li>PROPAGATION_NESTED : 6</li>
	 * </ul>
	 * @return {@link TransactionStatus}
	 */
	protected TransactionStatus getTxStatus(int propagation) {
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(propagation);

		return transactionManager.getTransaction(def);
	}

	/**
	 * Representation of the status of a transaction
	 * Default is PROPAGATION_REQUIRED
	 * 
	 * @return {@link TransactionStatus}
	 */
	protected TransactionStatus getTxStatus() {
		return getTxStatus(TransactionDefinition.PROPAGATION_REQUIRED);
	}

	/**
	 * Get HttpSession Object
	 * 
	 * @return {@link HttpSession}
	 */
	protected HttpSession getHttpSession() {
		ServletRequestAttributes attribute = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attribute.getRequest().getSession();
		return session;
	}

	/**
	 * Returns a string unique identifier.
	 * 
	 * @return sessionId
	 */
	protected String getSessionId() {
		return this.getHttpSession().getId();
	}

}