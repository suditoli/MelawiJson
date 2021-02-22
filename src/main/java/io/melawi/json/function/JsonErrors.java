package io.melawi.json.function;

import io.melawi.json.JSONException;

public class JsonErrors {

    private JsonErrors() {
    }

    public static <T> T suppress(ThrowingJsonSupplier<T> s) {
        try {
            return s.get();
        } catch (JSONException e) {
            throw new AssertionError();
        }
    }
}
