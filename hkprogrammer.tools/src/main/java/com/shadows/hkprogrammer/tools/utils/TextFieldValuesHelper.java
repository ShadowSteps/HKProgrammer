/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.tools.utils;

import javafx.scene.control.TextArea;

/**
 *
 * @author John
 */
public class TextFieldValuesHelper {
    public void WriteByteArrayToTextArea(TextArea area,byte[] values){
        String text = "new byte[] {";
        for(byte value : values)
            text += "\n\t" + value + ",";
        text = StringUtils.trimEnd(text, ',');
        text += "\n" + "};";
        area.setText(text);
    }
}
