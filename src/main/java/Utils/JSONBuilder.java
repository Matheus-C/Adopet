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
    public JsonObjectBuilder add(String string, JsonValue jv) {
        this.objectBuilder.add(string, jv);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, String string1) {
        if(string1 == null) this.addNull(string);
        else this.objectBuilder.add(string, string1);
        
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, BigInteger bi) {
        this.objectBuilder.add(string, bi);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, BigDecimal bd) {
        this.objectBuilder.add(string, bd);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, int i) {
        this.objectBuilder.add(string, i);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, long l) {
        this.objectBuilder.add(string, l);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, double d) {
        this.objectBuilder.add(string, d);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, boolean bln) {
        this.objectBuilder.add(string, bln);
        return this;
    }

    @Override
    public JsonObjectBuilder addNull(String string) {
        this.objectBuilder.addNull(string);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, JsonObjectBuilder job) {
        this.objectBuilder.add(string, job);
        return this;
    }

    @Override
    public JsonObjectBuilder add(String string, JsonArrayBuilder jab) {
        this.objectBuilder.add(string, jab);
        return this;
    }

    @Override
    public JsonObject build() {
        return this.objectBuilder.build();
    }

}
