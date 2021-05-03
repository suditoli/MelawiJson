/*
 * $Id: JSONObject.java,v 1.1 2006/04/15 14:10:48 platform Exp $
 * Created on 2006-4-10
 */
package com.github.suditoli.melawi.json;

import static com.github.suditoli.melawi.json.function.JsonErrors.suppress;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.function.Consumer;

/**
 * A JSON object. Key value pairs are unordered. JSONObject supports
 * java.util.Map interface.
 *
 * @author FangYidong<fangyidong@yahoo.com.cn>
 */
public class JSONObject extends HashMap implements Map, JSONAware, JSONStreamAware {

    private static final long serialVersionUID = -503443796854799292L;

    private static JSONException wrongValueFormatException(String key, String valueType, Throwable cause) {
        return new JSONException("JSONObject[" + key + "] is not a " + valueType + ".", cause);
    }

    public JSONObject() {
        super();
    }

    /**
     * Allows creation of a JSONObject from a Map. After that, both the
     * generated JSONObject and the Map can be modified independently.
     *
     * @param map
     */
    public JSONObject(Map map) {
        super(map);
    }

    public JSONObject addString(String key, String value) {
        requireNonNull(key, JSONConstants.ERROR_NULL_KEY);

        if (Objects.isNull(value)) {
            return this;
        }

        suppress(() -> this.put(key, value));

        return this;
    }

    public JSONObject addNumber(String key, Number value) {
        requireNonNull(key, JSONConstants.ERROR_NULL_KEY);

        if (Objects.isNull(value)) {
            return this;
        }

        suppress(() -> this.put(key, value));

        return this;
    }

    public JSONObject addBoolean(String key, Boolean value) {
        requireNonNull(key, JSONConstants.ERROR_NULL_KEY);

        if (Objects.isNull(value)) {
            return this;
        }

        suppress(() -> this.put(key, value));

        return this;
    }

    public JSONObject addNull(String key) {
        requireNonNull(key, JSONConstants.ERROR_NULL_KEY);

        suppress(() -> this.put(key, null));

        return this;
    }

    public JSONObject object(Consumer<JSONObject> c) {
        JSONObject jsonObject = new JSONObject();
        c.accept(jsonObject);

        return new JSONObject(jsonObject);
    }

    public JSONObject addObject(String key, Consumer<JSONObject> value) {
        requireNonNull(key, JSONConstants.ERROR_NULL_KEY);

        JSONObject jsonObject = new JSONObject();
        value.accept(jsonObject);

        suppress(() -> this.put(key, jsonObject));

        return this;
    }

    public JSONObject addArray(String key, Consumer<JSONArray> value) {
        requireNonNull(key, JSONConstants.ERROR_NULL_KEY);

        JSONArray jsonArray = new JSONArray();
        value.accept(jsonArray);

        suppress(() -> this.put(key, jsonArray));

        return this;
    }

    public boolean getBoolean(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Boolean.valueOf((String) object);
            }
            return (Boolean) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Boolean", null);
        }
    }

    public boolean getBoolean(final String key, boolean defaultValue) {
        try {
            return getBoolean(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public byte getByte(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Byte.parseByte((String) object);
            }
            return (byte) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Byte", null);
        }
    }

    public byte getByte(final String key, byte defaultValue) {
        try {
            return getByte(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public int getInteger(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Integer.parseInt((String) object);
            }
            return (int) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Integer", null);
        }
    }

    public int getInteger(final String key, int defaultValue) {
        try {
            return getInteger(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public long getLong(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Long.parseLong((String) object);
            }
            return (long) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Long", null);
        }
    }

    public long getLong(final String key, long defaultValue) {
        try {
            return getLong(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public BigInteger getBigInteger(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = new BigInteger((String) object);
            }
            return (BigInteger) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "BigInteger", null);
        }
    }

    public BigInteger getBigInteger(final String key, BigInteger defaultValue) {
        try {
            return getBigInteger(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public short getShort(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Short.valueOf((String) object);
            }
            return (short) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Short", null);
        }
    }

    public short getShort(final String key, short defaultValue) {
        try {
            return getShort(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public float getFloat(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Float.valueOf((String) object);
            }
            return (float) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Float", null);
        }
    }

    public float getFloat(final String key, float defaultValue) {
        try {
            return getFloat(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public double getDouble(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = Double.valueOf((String) object);
            }
            return (double) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "Double", null);
        }
    }

    public double getDouble(final String key, double defaultValue) {
        try {
            return getFloat(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public BigDecimal getBigDecimal(final String key) {
        try {
            Object object = this.get(key);
            if (object instanceof String) {
                object = new BigDecimal((String) object);
            }
            return (BigDecimal) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "BigDecimal", null);
        }
    }

    public BigDecimal getBigDecimal(final String key, BigDecimal defaultValue) {
        try {
            return getBigDecimal(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public String getString(final String key) {
        try {
            Object object = this.get(key);
            return (String) object;
        } catch (Exception ex) {
            throw wrongValueFormatException(key, "String", null);
        }
    }

    public String getString(final String key, String defaultValue) {
        try {
            return getString(key);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public JSONObject getJSONObject(String key) {
        try {
            Object object = this.get(key);
            if (object == null) {
                return new JSONObject();
            }
            return (JSONObject) object;
        } catch (Exception ex) {
            if (this.isEmpty(key)) {
                return new JSONObject();
            }
            if (this.get(key) instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) this.get(key);
                return (JSONObject) jsonArray.getJSONObject(0);
            }
            throw wrongValueFormatException(key, "JSONObject", null);
        }
    }

    public JSONArray getJSONArray(String key) {
        try {
            Object object = this.get(key);
            if (object == null) {
                return new JSONArray();
            }
            return (JSONArray) object;
        } catch (Exception ex) {
            if (this.isEmpty(key)) {
                return new JSONArray();
            }
            if (this.get(key) instanceof JSONObject) {
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(this.get(key));
                return jsonArray;
            }
            throw wrongValueFormatException(key, "JSONArray", null);
        }
    }

    public boolean has(String key) {
        return this.containsKey(key);
    }

    public boolean isNull(String key) {
        return has(key) && this.get(key) == null;
    }

    public boolean isEmpty(String key) {
        if (has(key)) {
            Object object = this.get(key);
            if (object instanceof String) {
                return getString(key).isEmpty();
            } else if (object instanceof JSONObject) {
                return getJSONObject(key).isEmpty();
            } else if (object instanceof JSONArray) {
                return getJSONArray(key).isEmpty();
            } else {
                return false;
            }
        } else {
            return true;
        }
    }

    /**
     * Encode a map into JSON text and write it to out. If this map is also a
     * JSONAware or JSONStreamAware, JSONAware or JSONStreamAware specific
     * behaviours will be ignored at this top level.
     *
     * @see org.json.simple.JSONValue#writeJSONString(Object, Writer)
     *
     * @param map
     * @param out
     */
    public static void writeJSONString(Map map, Writer out) throws IOException {
        if (map == null) {
            out.write("null");
            return;
        }

        boolean first = true;
        Iterator iter = map.entrySet().iterator();

        out.write('{');
        while (iter.hasNext()) {
            if (first) {
                first = false;
            } else {
                out.write(',');
            }
            Map.Entry entry = (Map.Entry) iter.next();
            out.write('\"');
            out.write(escape(String.valueOf(entry.getKey())));
            out.write('\"');
            out.write(':');
            JSONValue.writeJSONString(entry.getValue(), out);
        }
        out.write('}');
    }

    @Override
    public void writeJSONString(Writer out) throws IOException {
        writeJSONString(this, out);
    }

    /**
     * Convert a map to JSON text. The result is a JSON object. If this map is
     * also a JSONAware, JSONAware specific behaviours will be omitted at this
     * top level.
     *
     * @see org.json.simple.JSONValue#toJSONString(Object)
     *
     * @param map
     * @return JSON text, or "null" if map is null.
     */
    public static String toJSONString(Map map) {
        final StringWriter writer = new StringWriter();

        try {
            writeJSONString(map, writer);
            return writer.toString();
        } catch (IOException e) {
            // This should never happen with a StringWriter
            throw new JSONException(e);
        }
    }

    @Override
    public String toJSONString() {
        return toJSONString(this);
    }

    @Override
    public String toString() {
        return toJSONString();
    }

    public static String toString(String key, Object value) {
        StringBuffer sb = new StringBuffer();
        sb.append('\"');
        if (key == null) {
            sb.append("null");
        } else {
            JSONValue.escape(key, sb);
        }
        sb.append('\"').append(':');

        sb.append(JSONValue.toJSONString(value));

        return sb.toString();
    }

    /**
     * Escape quotes, \, /, \r, \n, \b, \f, \t and other control characters
     * (U+0000 through U+001F). It's the same as JSONValue.escape() only for
     * compatibility here.
     *
     * @see org.json.simple.JSONValue#escape(String)
     *
     * @param s
     * @return
     */
    public static String escape(String s) {
        return JSONValue.escape(s);
    }
}
