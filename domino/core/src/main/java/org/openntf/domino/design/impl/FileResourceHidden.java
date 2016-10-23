/*
 * Copyright 2013
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 * implied. See the License for the specific language governing 
 * permissions and limitations under the License.
 */

package org.openntf.domino.design.impl;

import org.openntf.domino.Document;

/**
 * @author Roland Praml
 * 
 */
public final class FileResourceHidden extends AbstractDesignFileResource implements org.openntf.domino.design.FileResourceHidden {
	private static final long serialVersionUID = 1L;

	/**
	 * @param document
	 */
	protected FileResourceHidden(final Document document) {
		super(document);
	}

	@Override
	protected boolean enforceRawFormat() {
		// FileResourceHidden is exported in RAW-format. There is no DXL representation
		return true;
	}

	@Override
	protected boolean mustEncode(final String resName) {
		return false;
	}

	@Override
	public void setDeployable(final boolean deployable) {
		// TODO Auto-generated method stub

	}

}
