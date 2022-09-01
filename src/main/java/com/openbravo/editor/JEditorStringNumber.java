package com.openbravo.editor;

/**
 *
 * @author adrian
 */
public class JEditorStringNumber extends JEditorText {
    
    /** Creates a new instance of JEditorStringNumber */
    public JEditorStringNumber() {
        super();
    }
    
    /**
     *
     * @return
     */
    protected int getMode() {
        return EditorKeys.MODE_INTEGER_POSITIVE;
    }

    /**
     *
     * @return
     */
    protected int getStartMode() {
        return MODE_123;
    }    
}
