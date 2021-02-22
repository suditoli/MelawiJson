package io.melawi.json.function;

import io.melawi.json.JSONException;

@FunctionalInterface
public interface ThrowingJsonSupplier<T> {

    T get() throws JSONException;
}
