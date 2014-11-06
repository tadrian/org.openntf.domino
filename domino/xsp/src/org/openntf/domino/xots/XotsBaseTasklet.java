package org.openntf.domino.xots;

import lotus.domino.NotesException;
import lotus.domino.Session;

import org.openntf.domino.thread.AbstractDominoRunnable;

import com.ibm.domino.xsp.module.nsf.NotesContext;

@SuppressWarnings("serial")
public abstract class XotsBaseTasklet extends AbstractDominoRunnable {
	private String signer;

	public XotsBaseTasklet() {
		NotesContext ctx = NotesContext.getCurrentUnchecked();
		if (ctx != null) {
			Session signerSession = ctx.getSessionAsSigner();
			try {
				signer = signerSession.getEffectiveUserName();
			} catch (NotesException e) {
				e.printStackTrace();
			}
		}
		System.out.println("The Signer of " + this.getClass().getName() + " is " + signer);
	}

	@Override
	public String getSigner() {
		return signer;
	}

}
