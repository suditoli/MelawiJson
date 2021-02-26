/*
 * $Id: JSONArray.java,v 1.1 2006/04/15 14:10:48 platform Exp $
 * Created on 2006-4-10
 */
package io.melawi.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import static java.util.stream.IntStream.range;

/**
 * A JSON array. JSONObject supports java.util.List interface.
 *
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
public class JSONArray extends ArrayList implements JSONAware, JSONStreamAware {

    private static final long serialVersionUID = 3957988303675231981L;

    private static JSONException wrongValueFormatException(int idx, String valueType, Throwable cause) {
        return new JSONException("JSONArray[" + idx + "] is not a " + valueType + ".", cause);
    }

    /**
     * Constructs an empty JSONArray.
     */
    public JSONArray() {
        super();
    }

    /**
     * Constructs a JSONArray containing the elements of the specified
     * collection, in the order they are returned by the collection's iterator.
     *
     * @param c the collection whose elements are to be placed into this
     * JSONArray
     */
    public JSONArray(Collection c) {
        super(c);
    }

    public JSONArray addStrings(String... values) {
        Arrays.stream(values).forEach(this::add);

        return this;
    }

    public JSONArray addNumbers(Number... values) {
        Arrays.stream(values).forEach(this::add);

        return this;
    }

    public JSONArray addBooleans(Boolean... values) {
        Arrays.stream(values).forEach(this::add);

        return this;
    }

    public JSONArray addNulls(int number) {
        range(0, number).forEach(i -> this.add(null));

        return this;
    }

    public JSONArray addObject(Consumer<JSONObject> c) {
        JSONObject jsonObject = new JSONObject();
        c.accept(jsonObject);

        this.add(jsonObject);

        return this;
    }

    public JSONArray array(Consumer<JSONArray> c) {
        JSONArray jsonArray = new JSONArray();
        c.accept(jsonArray);

        this.add(jsonArray);

        return this;
    }

    public boolean getBoolean(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Boolean.valueOf((String) object);
            }
            return (Boolean) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Boolean", null);
        }
    }

    public boolean getBoolean(final int index, boolean defaultValue) {
        try {
            return getBoolean(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public byte getByte(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Byte.parseByte((String) object);
            }
            return (byte) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Byte", null);
        }
    }

    public byte getByte(final int index, byte defaultValue) {
        try {
            return getByte(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public int getInteger(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Integer.parseInt((String) object);
            }
            return (int) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Integer", null);
        }
    }

    public int getInteger(final int index, int defaultValue) {
        try {
            return getInteger(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public long getLong(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Long.parseLong((String) object);
            }
            return (long) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Long", null);
        }
    }

    public long getLong(final int index, long defaultValue) {
        try {
            return getLong(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public BigInteger getBigInteger(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = new BigInteger((String) object);
            }
            return (BigInteger) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "BigInteger", null);
        }
    }

    public BigInteger getBigInteger(final int index, BigInteger defaultValue) {
        try {
            return getBigInteger(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public short getShort(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Short.valueOf((String) object);
            }
            return (short) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Short", null);
        }
    }

    public short getShort(final int index, short defaultValue) {
        try {
            return getShort(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public float getFloat(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Float.valueOf((String) object);
            }
            return (float) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Float", null);
        }
    }

    public float getFloat(final int index, float defaultValue) {
        try {
            return getFloat(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public double getDouble(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = Double.valueOf((String) object);
            }
            return (double) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "Double", null);
        }
    }

    public double getDouble(final int index, double defaultValue) {
        try {
            return getFloat(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public BigDecimal getBigDecimal(final int index) {
        try {
            Object object = this.get(index);
            if (object instanceof String) {
                object = new BigDecimal((String) object);
            }
            return (BigDecimal) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "BigDecimal", null);
        }
    }

    public BigDecimal getBigDecimal(final int index, BigDecimal defaultValue) {
        try {
            return getBigDecimal(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public String getString(final int index) {
        try {
            Object object = this.get(index);
            return (String) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(index, "String", null);
        }
    }

    public String getString(final int index, String defaultValue) {
        try {
            return getString(index);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public JSONObject getJSONObject(int index) {
        try {
            return (JSONObject) this.get(index);
        } catch (Exception ex) {
            if (this.isEmpty(index)) {
                return new JSONObject();
            }
            if (this.get(index) instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) this.get(index);
                return (JSONObject) jsonArray.getJSONObject(0);
            }
            throw wrongValueFormatException(index, "JSONObject", null);
        }
    }

    public JSONArray getJSONArray(int index) {
        try {
            return (JSONArray) this.get(index);
        } catch (Exception ex) {
            if (this.isEmpty(index)) {
                return new JSONArray();
            }
            if (this.get(index) instanceof JSONObject) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(this.get(index));
                return jsonArray;
            }
            throw wrongValueFormatException(index, "JSONArray", null);
        }
    }

    public boolean has(Object o) {
        return this.contains(o);
    }

    public boolean isNull(int index) {
        try {
            return this.get(index) == null;
        } catch (Exception ex) {
            return false;
        }
    }

    public boolean isEmpty(int index) {
        if (isNull(index) && index >= this.size()) {
            return true;
        } else {
            Object object = this.get(index);
            if (object instanceof String) {
                return getString(index).isEmpty();
            } else if (object instanceof JSONObject) {
                return getJSONObject(index).isEmpty();
            } else if (object instanceof JSONArray) {
                return getJSONArray(index).isEmpty();
            } else {
                return false;
            }
        }
    }

    /**
     * Encode a list into JSON text and write it to out. If this list is also a
     * JSONStreamAware or a JSONAware, JSONStreamAware and JSONAware specific
     * behaviours will be ignored at this top level.
     *
     * @see org.json.simple.JSONValue#writeJSONString(Object, Writer)
     *
     * @param collection
     * @param out
     */
    public static void writeJSONString(Collection collection, Writer out) throws IOException {
        if (collection == null) {
            out.write("null");
            return;
        }

        boolean first = true;
        Iterator iter = collection.iterator();

        out.write('[');
        while (iter.hasNext()) {
            if (first) {
                first = false;
            } else {
                out.write(',');
            }

            Object value = iter.next();
            if (value == null) {
                out.write("null");
                continue;
            }

            JSONValue.writeJSONString(value, out);
        }
        out.write(']');
    }

    @Override
    public void writeJSONString(Writer out) throws IOException {
        writeJSONString(this, out);
    }

    /**
     * Convert a list to JSON text. The result is a JSON array. If this list is
     * also a JSONAware, JSONAware specific behaviours will be omitted at this
     * top level.
     *
     * @see org.json.simple.JSONValue#toJSONString(Object)
     *
     * @param collection
     * @return JSON text, or "null" if list is null.
     */
    public static String toJSONString(Collection collection) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(collection, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(byte[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(byte[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(short[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(short[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(int[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(int[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(long[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(long[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(float[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(float[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(double[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(double[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(boolean[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                out.write(String.valueOf(array[i]));
            }

            out.write("]");
        }
    }

    public static String toJSONString(boolean[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(char[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[\"");
            out.write(String.valueOf(array[0]));

            for (int i = 1; i < array.length; i++) {
                out.write("\",\"");
                out.write(String.valueOf(array[i]));
            }

            out.write("\"]");
        }
    }

    public static String toJSONString(char[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    public static void writeJSONString(Object[] array, Writer out) throws IOException {
        if (array == null) {
            out.write("null");
        } else if (array.length == 0) {
            out.write("[]");
        } else {
            out.write("[");
            JSONValue.writeJSONString(array[0], out);

            for (int i = 1; i < array.length; i++) {
                out.write(",");
                JSONValue.writeJSONString(array[i], out);
            }

            out.write("]");
        }
    }

    public static String toJSONString(Object[] array) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(array, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen for a StringWriter
            throw new JSONException(e);
        }
    }

    @Override
    public String toJSONString() {
        return toJSONString(this);
    }

    /**
     * Returns a string representation of this array. This is equivalent to
     * calling {@link JSONArray#toJSONString()}.
     */
    @Override
    public String toString() {
        return toJSONString();
    }
}
