/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import HttpErrors.BadRequest;
import java.time.Instant;
import java.util.Date;

/**
 *
 * @author Mac
 */
public class ValidationWrapper {

    public static boolean parseBoolean(String fieldName, String value_str) throws BadRequest {
        try {
            boolean value = Boolean.parseBoolean(value_str);
            return value;
        } catch (NumberFormatException ex) {
            throw new BadRequest("O campo " + fieldName + " não é um boolean válido");
        }
    }
    
    
    public static long parseLongId(String fieldName, String value_str) throws BadRequest {
        try {
            long value = Long.parseLong(value_str);
            return value;
        } catch (NumberFormatException ex) {
            throw new BadRequest("O campo " + fieldName + " não é um id válido");
        }
    }
    
    public static double parseDouble(String fieldName, String value_str) throws BadRequest {
        try {
            double value = Double.parseDouble(value_str);
            return value;
        } catch (NumberFormatException ex) {
            throw new BadRequest("O campo " + fieldName + " não é um número válido");
        }
    }

    
    public static int parseInt(String fieldName, String value_str) throws BadRequest {
        try {
            int value = Integer.parseInt(value_str);
            return value;
        } catch (NumberFormatException ex) {
            throw new BadRequest("O campo " + fieldName + " não é um inteiro válido");
        }
    }

    public static Date parseDate(String fieldName, String value_str) throws BadRequest {
        try {
            Date value = null;
            if (value_str.length() > 0) {
                value = Date.from(Instant.parse(value_str));
            }
            return value;
        } catch (Exception e) {
            throw new HttpErrors.BadRequest("O campo " + fieldName + " não é uma data válida");
        }
    }
    
    public static boolean isSet(String o) {
        if(o == null) return false;
        
        if(o instanceof String) {
            return ((String) o).length() > 0;
        }
        
        return false;
    }
}
