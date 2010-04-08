package com.hxzy.base.web.listener;

import java.util.List;

import org.hibernate.StaleObjectStateException;
import org.zkoss.util.logging.Log;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.util.ExecutionCleanup;
import org.zkoss.zk.ui.util.ExecutionInit;
import org.zkoss.zkplus.hibernate.HibernateUtil;

public class OpenSessionInViewListener implements ExecutionInit, ExecutionCleanup {
	private static final Log log = Log.lookup(OpenSessionInViewListener.class);

	public void init(Execution exec, Execution parent) {
		if (parent == null) {
			log.debug("Starting a database transaction: " + exec);
			HibernateUtil.currentSession().beginTransaction();
		}
	}

	public void cleanup(Execution exec, Execution parent, List errs) {
		if (parent != null)
			return;
		try {
			if ((errs == null) || (errs.isEmpty())) {
				log.debug("Committing the database transaction: " + exec);
				HibernateUtil.currentSession().getTransaction().commit();
			} else {
				Throwable ex = (Throwable) errs.get(0);
				if (ex instanceof StaleObjectStateException) {
					handleStaleObjectStateException(exec, (StaleObjectStateException) ex);
				} else {
					handleOtherException(exec, ex);
				}
			}
		} finally {
			HibernateUtil.closeSession();
		}
	}

	protected void handleStaleObjectStateException(Execution exec, StaleObjectStateException ex) {
		log.error("This listener does not implement optimistic concurrency control!");
		rollback(exec, ex);
	}

	protected void handleOtherException(Execution exec, Throwable ex) {
		ex.printStackTrace();
		rollback(exec, ex);
	}

	private void rollback(Execution exec, Throwable ex) {
		try {
			if (HibernateUtil.currentSession().getTransaction().isActive()) {
				log.debug("Trying to rollback database transaction after exception:" + ex);
				HibernateUtil.currentSession().getTransaction().rollback();
			}
		} catch (Throwable rbEx) {
			log.error("Could not rollback transaction after exception! Original Exception:\n" + ex,
					rbEx);
		}
	}
}