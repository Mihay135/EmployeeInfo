package jValidators;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

public class MaxLengthTextDocument extends PlainDocument {
    //Store maximum characters permitted
    private int maxChars;

    public void setMaxChars(int maxChars) {
		this.maxChars = maxChars;
	}

	@Override
    public void insertString(int offs, String str, AttributeSet a)
            throws BadLocationException {
        // the length of string that will be created is getLength() + str.length()
        if(str != null && (getLength() + str.length() <= maxChars)){
            super.insertString(offs, str, a);
        }
    }

}