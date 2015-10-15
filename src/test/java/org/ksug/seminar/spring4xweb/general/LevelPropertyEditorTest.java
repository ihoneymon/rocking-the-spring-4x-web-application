package org.ksug.seminar.spring4xweb.general;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Before;
import org.junit.Test;

public class LevelPropertyEditorTest {

    private LevelPropertyEditor levelPropertyEditor;

    @Before
    public void setUp() {
        levelPropertyEditor = new LevelPropertyEditor();
    }

    @Test
    public void 문자열변환() throws Exception {

        // when
        levelPropertyEditor.setAsText("3");

        // then
        assertThat(levelPropertyEditor.getValue(), is(Level.GOLD));
    }
    
    @Test
    public void 숫자변환() throws Exception {
        //when
        levelPropertyEditor.setValue(Level.SILVER);
        
        //then
        assertThat(levelPropertyEditor.getAsText(), is("2"));
    }
}
