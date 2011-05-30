package fonts.actions;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.preferences.ScopedPreferenceStore;

/**
 * @author Guy Korland
 */
public class FontsDecreaseSizeAction implements IWorkbenchWindowActionDelegate {
	private IWorkbenchWindow window;
	private ScopedPreferenceStore store;
	/**
	 * The constructor.	
	 */
	public FontsDecreaseSizeAction() {
	}

	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) { 
		String font = store.getString(JFaceResources.TEXT_FONT);
		String[] split = font.split("\\|");
		float fontSize = Float.parseFloat(split[2]) - 1;
		if(fontSize<=0)
			fontSize = 1;

		split[2] = Float.toString(fontSize);
		StringBuilder builder = new StringBuilder(split[0]);
		for(int i=1; i<split.length ; ++i){
			builder.append('|').append(split[i]);
		}
		store.setValue(JFaceResources.TEXT_FONT, builder.toString());
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
		this.store = new ScopedPreferenceStore( new InstanceScope(), "org.eclipse.ui.workbench" );

	}
}