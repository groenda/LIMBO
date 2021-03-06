/*******************************************************************************
 * Copyright (c) 2014 Joakim von Kistowski
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package tools.descartes.dlim.generator.editor.popup.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewReference;
import org.eclipse.ui.PlatformUI;

import tools.descartes.dlim.generator.editor.dialogs.SavePlotViewImageDialog;
import tools.descartes.dlim.generator.editor.views.PlotView;

/**
 * Saves the graph in the PlotView to an image file.
 *
 * @author Joakim von Kistowski
 */
public class SaveGraphFromPlotViewAction implements IViewActionDelegate {

	private static final String PLOTVIEWID = "tools.descartes.dlim.generator.editor.views.PlotView";

	@Override
	public void run(IAction action) {
		// Display save dialog
		SavePlotViewImageDialog dialog = new SavePlotViewImageDialog(PlatformUI
				.getWorkbench().getActiveWorkbenchWindow().getShell());
		dialog.open();

		if (!dialog.wasCanceled()) {
			IViewReference[] references = PlatformUI.getWorkbench()
					.getActiveWorkbenchWindow().getActivePage()
					.getViewReferences();
			for (int i = 0; i < references.length; i++) {
				if (references[i].getId().equals(PLOTVIEWID)) {
					PlotView view = (PlotView) (references[i].getView(true));
					view.savePlotImage(dialog.getImageFilePath(),
							dialog.getWidth(), dialog.getHeight());
				}
			}
		}

	}

	@Override
	public void selectionChanged(IAction action, ISelection selection) {
		//does nothing
	}

	@Override
	public void init(IViewPart view) {
		//does nothing
	}

}
