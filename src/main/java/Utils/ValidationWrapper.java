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
}
