package io.melawi.json.generate;

import java.math.BigDecimal;
import java.math.BigInteger;
import static java.util.Arrays.stream;

/**
 *
 * @author chin
 */
public class JSONGenerator {

    StringBuilder sb = new StringBuilder();

    public JSONGenerator startObject() {
        this.sb.append("{");
        return this;
    }

    public JSONGenerator startArray(String k) {
        appendFieldName(k);
        this.sb.append("[");
        return this;
    }

    public JSONGenerator endArray() {
        removeLastComma();
        this.sb.append("]");
        addComma();
        return this;
    }

    public JSONGenerator endObject() {
        removeLastComma();
        this.sb.append("}");
        addComma();
        return this;
    }

    public JSONGenerator writeString(String k, String v) {
        appendFieldName(k);
        this.sb.append("\"").append(v.trim()).append("\"");
        addComma();
        return this;
    }

    public JSONGenerator writeNumber(String k, int v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeNumber(String k, long v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeNumber(String k, BigInteger v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeNumber(String k, double v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeNumber(String k, float v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeNumber(String k, BigDecimal v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeBoolean(String k, boolean v) {
        appendFieldName(k);
        this.sb.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(String... v) {
        StringBuilder lsb = new StringBuilder();
        stream(v).forEach(s -> lsb.append("\"").append(s.trim()).append("\"").append(","));
        if (lsb.length() > 0) {
            this.sb.append(lsb.substring(0, lsb.length() - 1));
        }
        return this;
    }

    public JSONGenerator writeArray(Integer... v) {
        Appender<Integer> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(Long... v) {
        Appender<Long> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(BigInteger... v) {
        Appender<BigInteger> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(Double... v) {
        Appender<Double> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(Float... v) {
        Appender<Float> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(BigDecimal... v) {
        Appender<BigDecimal> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeArray(Boolean... v) {
        Appender<Boolean> ap = new Appender<>();
        ap.append(v);
        addComma();
        return this;
    }

    public JSONGenerator writeNull(String k) {
        appendFieldName(k);
        this.sb.append("null");
        addComma();
        return this;
    }

    @Override
    public String toString() {
        removeLastComma();
        return this.sb.toString();
    }

    private void appendFieldName(String fn) {
        this.sb.append("\"").append(fn.trim()).append("\"")
                .append(":");
    }

    private void removeLastComma() {
        if (this.sb.charAt(this.sb.length() - 1) == ',') {
            this.sb.deleteCharAt(this.sb.length() - 1);
        }
    }

    private void addComma() {
        this.sb.append(',');
    }
}
