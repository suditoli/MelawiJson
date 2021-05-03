package com.github.suditoli.melawi.json.function;

import com.github.suditoli.melawi.json.JSONException;

@FunctionalInterface
public interface ThrowingJsonSupplier<T> {

    T get() throws JSONException;
}
