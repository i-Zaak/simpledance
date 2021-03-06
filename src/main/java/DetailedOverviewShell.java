import gnu.gettext.GettextResource;

import java.util.ResourceBundle;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * @author Standard
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class DetailedOverviewShell
{
	// *** BEGIN I18N
	static private ResourceBundle localeResource;
	static
	{
		try
		{
			localeResource = GettextResource.getBundle("SimpleDanceBundle");
		} catch(Exception e){};
	};
	static final private String _(String str)
	{
		if (localeResource == null) return str;
		return GettextResource.gettext(localeResource,str);
	}
	// *** END I18N

	private Shell parentShell;
	private Shell shell;
	private DetailedOverviewCanvas overview;
	private ScrolledComposite scrolledComposite;

	private Pattern pattern;
	
	DetailedOverviewShell(Shell parentShell)
	{
		this.parentShell = parentShell;
	}
	
	void open()
	{
		if (shell != null)
		{
			if (!shell.isDisposed())
			{
				return;
			}
		}

		shell = new Shell(parentShell,SWT.RESIZE|SWT.CLOSE|SWT.TITLE);
		shell.setText("SimpleDance - " + _("Detailed step overview"));
		shell.setLayout(new GridLayout(1,false));

		GridData gridData;
		gridData = new GridData();
		gridData.horizontalAlignment = GridData.FILL;
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		
		scrolledComposite = new ScrolledComposite(shell,SWT.H_SCROLL|SWT.V_SCROLL|SWT.BORDER);
		scrolledComposite.setLayoutData(gridData);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		overview = new DetailedOverviewCanvas(scrolledComposite,0);
		overview.addDetailedOverviewListener(new DetailedOverviewListener()
		{
			public void stepClicked(DetailedOverviewEvent ev)
			{
				pattern.setCurrentStepNum(ev.stepNo);
			}
		});
		scrolledComposite.setContent(overview);
		
		setPattern(pattern);
		shell.pack();
		shell.open();
	}

	public void setPattern(Pattern pattern)
	{
		this.pattern = pattern;
		if (shell == null) return;
		if (shell.isDisposed()) return;
		overview.setPattern(pattern);
		scrolledComposite.setMinSize(overview.computeSize(SWT.DEFAULT,SWT.DEFAULT));
	}

	public void refresh()
	{
		setPattern(pattern);
	}
}
