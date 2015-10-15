package org.ksug.seminar.spring4xweb.general;

import java.beans.PropertyEditorSupport;

/**
 * Toby의 스프링 3.1 531p에 나오는 프로퍼티 에디터
 * 
 * @author honeymon
 *
 */
public class LevelPropertyEditor extends PropertyEditorSupport {

    @Override
    public String getAsText() {
        return String.valueOf(((Level) this.getValue()).getValue());
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        this.setValue(Level.valueOf(Integer.parseInt(text.trim())));
    }
}
