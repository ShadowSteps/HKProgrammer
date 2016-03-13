/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.tools.utils;

import com.shadows.hkprogrammer.core.utils.ByteArray;
import com.shadows.hkprogrammer.core.utils.ByteConvertHelper;
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
    public Integer ReadByteArrayFromTextArea(TextArea area,ByteConvertHelper convert){
        String text = area.getText();
        text = text.replace("new byte[] {\n", "");
        text = text.replace("\n" + "};", "");
        String[] values = text.split(",\n");
        ByteArray barray = new ByteArray(values.length);
        for(String value : values){
            barray.Write(new byte[]{(byte)Integer.parseInt(value.replace("\t", "").trim())});
        }
        return barray.ToInteger();
    }
}
