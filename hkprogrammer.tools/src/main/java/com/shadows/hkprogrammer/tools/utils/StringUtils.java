/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.shadows.hkprogrammer.tools.utils;


/**
 *
 * @author John
 */
public class StringUtils {
  /**
   * Trim specified charcater from front of string
   * 
   * @param text
   *          Text
   * @param character
   *          Character to remove
   * @return Trimmed text
   */
  public static String trimFront(String text, char character) {
    String normalizedText;
    int index;
    normalizedText = text.trim();
    index = 0;

    while (normalizedText.charAt(index) == character) {
      index++;
    }
    return normalizedText.substring(index).trim();
  }

  /**
   * Trim specified charcater from end of string
   * 
   * @param text
   *          Text
   * @param character
   *          Character to remove
   * @return Trimmed text
   */
  public static String trimEnd(String text, char character) {
    String normalizedText;
    int index;

    normalizedText = text.trim();
    index = normalizedText.length() - 1;

    while (normalizedText.charAt(index) == character) {
      if (--index < 0) {
        return "";
      }
    }
    return normalizedText.substring(0, index + 1).trim();
  }

  /**
   * Trim specified charcater from both ends of a String
   * 
   * @param text
   *          Text
   * @param character
   *          Character to remove
   * @return Trimmed text
   */
  public static String trimAll(String text, char character) {
    String normalizedText = trimFront(text, character);

    return trimEnd(normalizedText, character);
  }
}
