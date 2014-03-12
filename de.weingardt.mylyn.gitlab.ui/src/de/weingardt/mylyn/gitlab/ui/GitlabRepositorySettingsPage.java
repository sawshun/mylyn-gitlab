/*******************************************************************************
 * Copyright (c) 2014, Paul Weingardt
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Paul Weingardt - initial API and implementation
 *******************************************************************************/



package de.weingardt.mylyn.gitlab.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.mylyn.tasks.core.TaskRepository;
import org.eclipse.mylyn.tasks.ui.wizards.AbstractRepositorySettingsPage;
import org.eclipse.swt.widgets.Composite;

import de.weingardt.mylyn.gitlab.core.GitlabConnector;
import de.weingardt.mylyn.gitlab.core.GitlabPluginCore;

public class GitlabRepositorySettingsPage extends AbstractRepositorySettingsPage {

	public GitlabRepositorySettingsPage(String title, String description,
			TaskRepository taskRepository) {
		super(title, description, taskRepository);
		
		setNeedsValidateOnFinish(true);
	}

	@Override
	protected void createAdditionalControls(Composite composite) {
		
	}

	@Override
	public String getConnectorKind() {
		return GitlabPluginCore.CONNECTOR_KIND;
	}

	@Override
	public TaskRepository createTaskRepository() {
		TaskRepository repo = super.createTaskRepository();
		repo.setBugRepository(true);
		return repo;
	}
	
	@Override
	public void applyTo(TaskRepository repository) {
		repository.setCategory(TaskRepository.CATEGORY_BUGS);
		super.applyTo(repository);
	}
	
	@Override
	protected Validator getValidator(final TaskRepository repository) {
		return new Validator() {

			@Override
			public void run(IProgressMonitor monitor) throws CoreException {
				GitlabConnector.validate(repository);
			}
			
		};
	}


}