/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import javax.json.JsonObject;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;

/**
 *
 * @author Mac
 */
public class JSONArrayBuilder implements JsonArrayBuilder {

    JsonBuilderFactory jsonFactory;
    JsonArrayBuilder arrayBuilder;

    public JSONArrayBuilder() {
        this.jsonFactory = Json.createBuilderFactory(null);
        this.arrayBuilder = jsonFactory.createArrayBuilder();
    }
        
    @Override
    public JSONArrayBuilder add(JsonValue jv) {
        this.arrayBuilder.add( jv);
        return this;
    }

    @Override
    public JSONArrayBuilder add(String string) {
        if(string == null) this.addNull();
        else this.arrayBuilder.add(string);
        
        return this;
    }

    @Override
    public JSONArrayBuilder add(BigInteger bi) {
        this.arrayBuilder.add(bi);
        return this;
    }

    @Override
    public JSONArrayBuilder add(BigDecimal bd) {
        this.arrayBuilder.add(bd);
        return this;
    }

    @Override
    public JSONArrayBuilder add(int i) {
        this.arrayBuilder.add(i);
        return this;
    }

    @Override
    public JSONArrayBuilder add(long l) {
        this.arrayBuilder.add(l);
        return this;
    }

    @Override
    public JSONArrayBuilder add(double d) {
        this.arrayBuilder.add(d);
        return this;
    }

    @Override
    public JSONArrayBuilder add(boolean bln) {
        this.arrayBuilder.add(bln);
        return this;
    }

    @Override
    public JSONArrayBuilder addNull() {
        this.arrayBuilder.addNull();
        return this;
    }

    @Override
    public JSONArrayBuilder add(JsonObjectBuilder job) {
        this.arrayBuilder.add(job);
        return this;
    }

    @Override
    public JSONArrayBuilder add(JsonArrayBuilder jab) {
        this.arrayBuilder.add(jab);
        return this;
    }
 
    @Override
    public JsonArray build() {
        return this.arrayBuilder.build();
    }

}
