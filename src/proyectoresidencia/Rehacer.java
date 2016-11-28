/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectoresidencia;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author Carlos Barahona
 */
public class Rehacer extends AbstractAction{
    
    private UndoManager manager;

    public Rehacer(UndoManager manager){
        this.manager = manager;
     }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            manager.redo();
        }catch (CannotRedoException e){
          Toolkit.getDefaultToolkit().beep();
        }
    }
    
    
}