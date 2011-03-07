package net.bioclipse.ds.sdk.pdewizard;

import org.eclipse.pde.ui.templates.AbstractChoiceOption;
import org.eclipse.pde.ui.templates.BaseOptionTemplateSection;
import org.eclipse.pde.ui.templates.ComboChoiceOption;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class DSComboChoiceOption extends AbstractChoiceOption {

	private Combo fCombo;
	private Label fLabel;

	/**
	 * Constructor for ComboChoiceOption.
	 *
	 * @param section
	 *            the parent section.
	 * @param name
	 *            the unique name
	 * @param label
	 *            the presentable label
	 * @param choices
	 *            the list of choices from which the value can be chosen. Each
	 *            array entry should be an array of size 2, where position 0
	 *            will be interpeted as the choice unique name, and position 1
	 *            as the choice presentable label. 
	 */
	public DSComboChoiceOption(BaseOptionTemplateSection section, String name, String label, String[][] choices) {
		super(section, name, label, choices);
	}

	public void createControl(Composite parent, int span) {
		fLabel = createLabel(parent, 1);
		fLabel.setEnabled(isEnabled());
		fill(fLabel, 1);

		fCombo = new Combo(parent, SWT.READ_ONLY);
		fill(fCombo, 1);
		for (int i = 0; i < fChoices.length; i++) {
			String[] choice = fChoices[i];
			fCombo.add(choice[1], i);
			fCombo.setEnabled(isEnabled());
		}
		fCombo.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if (isBlocked())
					return;
				if (fCombo.getSelectionIndex() != -1) {
					String[] choice = fChoices[fCombo.getSelectionIndex()];
					// Since this is being fired by the combo, suppress updates
					// back to the control
					setValue(choice[0], false);
					getSection().validateOptions(DSComboChoiceOption.this);
				}
			}
		});

		if (getChoice() != null)
			selectChoice(getChoice());
	}

	protected void setOptionValue(Object value) {
		if (fCombo != null && value != null) {
			selectChoice(value.toString());
		}
	}

	protected void setOptionEnabled(boolean enabled) {
		if (fLabel != null) {
			fLabel.setEnabled(enabled);
			fCombo.setEnabled(enabled);
		}
	}

	protected void selectOptionChoice(String choice) {
		// choice is the value not the description
		int index = getIndexOfChoice(choice);

		if (index == -1) {
			// Set to the first item
			// Using set Value to keep everything consistent
			fCombo.select(0);
			setValue(fChoices[0][0], false);
		} else {
			fCombo.select(index);
		}
	}

	/**
	 * Get the index (in the collection) of the choice
	 * 
	 * @param choice
	 *            The key of the item
	 * @return The position in the list, or -1 if not found
	 * @since 3.4
	 */
	protected int getIndexOfChoice(String choice) {
		final int NOT_FOUND = -1;
		if (choice == null) {
			return NOT_FOUND;
		}
		for (int i = 0; i < fChoices.length; i++) {
			String testChoice = fChoices[i][0];
			if (choice.equals(testChoice)) {
				return i;
			}
		}
		return NOT_FOUND;
	}

	//remove all old and add new choices
	public void setChoices(String[][] choices){
		fChoices=choices;
		fCombo.removeAll();
		for (int i = 0; i < fChoices.length; i++) {
			String[] choice = fChoices[i];
			fCombo.add(choice[1], i);
			fCombo.setEnabled(isEnabled());
		}
	}
	
}