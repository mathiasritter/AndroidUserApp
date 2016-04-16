package at.mritter.dezsys11.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.widget.EditText;

/**
 * Edit text UI element that enables to close the activity when closing the keyboard
 *
 * @author Mathias Ritter
 * @version 1.0
 */
public class EditTextWithBackButton extends EditText {
    public interface IOnBackButtonListener {
        boolean OnEditTextBackButton();
    }

    public EditTextWithBackButton(Context context) {
        super(context);
    }

    public EditTextWithBackButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setOnBackButtonListener(IOnBackButtonListener l) {
        _listener = l;
    }

    IOnBackButtonListener _listener;

    @Override
    public boolean onKeyPreIme(int keyCode, KeyEvent event)
    {
        if (event.getAction()==KeyEvent.ACTION_UP && keyCode==KeyEvent.KEYCODE_BACK)
        {
            if (_listener!=null && _listener.OnEditTextBackButton())
                return false;
        }
        return super.onKeyPreIme(keyCode, event);
    }
}
