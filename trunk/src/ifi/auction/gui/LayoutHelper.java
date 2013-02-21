/**
 * @author NGUYEN Van Tho
 */
package ifi.auction.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

public class LayoutHelper {
    private GridBagConstraints textConstraints = null;
    private GridBagConstraints labelConstraints = null;
    private GridBagConstraints stretchConstraints = null;
    public LayoutHelper(){
    	
        textConstraints = new GridBagConstraints();
        textConstraints.fill = GridBagConstraints.HORIZONTAL;
        textConstraints.anchor = GridBagConstraints.NORTHWEST;
        textConstraints.weightx = 1.0;
        textConstraints.gridwidth = GridBagConstraints.REMAINDER;
        textConstraints.insets = new Insets(1, 10, 1, 1);

        labelConstraints =
            (GridBagConstraints) textConstraints.clone();

        labelConstraints.weightx = 0.0;
        labelConstraints.gridwidth = 1;
        textConstraints.insets = new Insets(1, 1, 1, 10);
        stretchConstraints = (GridBagConstraints) textConstraints.clone();
        stretchConstraints.fill = GridBagConstraints.SOUTH;
        stretchConstraints.gridwidth = GridBagConstraints.RELATIVE;
        
    }

    public void addPanel(Component c, Container parent){
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, textConstraints);
        parent.add(c);    	
    }
    
    public void addTextField(Component c, Container parent){
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, textConstraints);
        parent.add(c);   	
    }
        
    public void addLabel(Component c, Container parent) {
        GridBagLayout gbl = (GridBagLayout) parent.getLayout();
        gbl.setConstraints(c, labelConstraints);
        parent.add(c);
    }
}
