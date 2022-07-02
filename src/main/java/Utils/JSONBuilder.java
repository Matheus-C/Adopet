/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 *
 * @author Mac
 */
public class JSONBuilder implements JsonObjectBuilder {

    JsonBuilderFactory jsonFactory;
    JsonObjectBuilder objectBuilder;

    public JSONBuilder() {
        this.jsonFactory = Json.createBuilderFactory(null);
        this.objectBuilder = jsonFactory.createObjectBuilder();
    }
        
    @Override
    public JSONBuilder add(String string, JsonValue jv) {
        if(jv == null) this.addNull(string);
        this.objectBuilder.add(string, jv);
        return this;
    }

    @Override
    public JSONBuilder add(String string, String string1) {
        if(string1 == null) this.addNull(string);
        else this.objectBuilder.add(string, string1);
        
        return this;
    }

    @Override
    public JSONBuilder add(String string, BigInteger bi) {
        if(bi == null) this.addNull(string);
        else this.objectBuilder.add(string, bi);
        return this;
    }

    @Override
    public JSONBuilder add(String string, BigDecimal bd) {
        if(bd == null) this.addNull(string);
        else this.objectBuilder.add(string, bd);
        return this;
    }

    @Override
    public JSONBuilder add(String string, int i) {
        this.objectBuilder.add(string, i);
        return this;
    }
    public JSONBuilder add(String string, Integer i) {
        if(i == null) this.addNull(string);
        else this.objectBuilder.add(string, i);
        return this;
    }
    
    @Override
    public JSONBuilder add(String string, long l) {
        this.objectBuilder.add(string, l);
        return this;
    }

    public JSONBuilder add(String string, Long l) {
        if(l == null) this.addNull(string);
        else this.objectBuilder.add(string, l);
        return this;
    }
    
    @Override
    public JSONBuilder add(String string, double d) {
        this.objectBuilder.add(string, d);
        return this;
    }
    public JSONBuilder add(String string, Double d) {
        if(d == null) this.addNull(string);
        else this.objectBuilder.add(string, d);
        return this;
    }

    @Override
    public JSONBuilder add(String string, boolean bln) {
        this.objectBuilder.add(string, bln);
        return this;
    }
    public JSONBuilder add(String string, Boolean bln) {
        if(bln == null) this.addNull(string);
        else this.objectBuilder.add(string, bln);
        return this;
    }

    @Override
    public JSONBuilder addNull(String string) {
        this.objectBuilder.addNull(string);
        return this;
    }

    @Override
    public JSONBuilder add(String string, JsonObjectBuilder job) {
        if(job == null) this.addNull(string);
        else this.objectBuilder.add(string, job);
        return this;
    }

    @Override
    public JSONBuilder add(String string, JsonArrayBuilder jab) {
        if(jab == null) this.addNull(string);
        else this.objectBuilder.add(string, jab);
        return this;
    }

    @Override
    public JsonObject build() {
        return this.objectBuilder.build();
    }

}
