package com.github.suditoli.melawi.json.generate;

import static java.util.Arrays.stream;

/**
 *
 * @author chin
 */
class Appender<T> {

    public String appendString(T[] t) {
        StringBuilder lsb = new StringBuilder();

        stream(t).forEach(n -> lsb.append("\"").append(n).append("\"").append(","));
        return lsb.length() > 0 ? lsb.substring(0, lsb.length() - 1) : "";
    }

    public String append(T[] t) {
        StringBuilder lsb = new StringBuilder();

        stream(t).forEach(n -> lsb.append(n).append(","));
        return lsb.length() > 0 ? lsb.substring(0, lsb.length() - 1) : "";
    }
}
