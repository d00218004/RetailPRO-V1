package com.openbravo.editor;

/**
 *
 * @author JG uniCenta
 */
public class JEditorString extends JEditorText {
    
    /** Creates a new instance of JEditorString */
    public JEditorString() {
        super();
    }
    
    /**
     *
     * @return
     */
    @Override
    protected final int getMode() {
        return EditorKeys.MODE_STRING;
    }
        
    /**
     *
     * @return
     */
    @Override
    protected int getStartMode() {
        return MODE_Abc1;
    }
    
}

