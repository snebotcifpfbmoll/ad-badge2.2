package com.snebot.fbmoll.helper;

import com.snebot.fbmoll.functional.Function2;
import com.snebot.fbmoll.functional.Function3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.function.Function;
import java.util.function.Supplier;

public class ResponseHelper {
    private static final Logger log = LoggerFactory.getLogger(ResponseHelper.class);

    /**
     * Process a request with no arguments.
     *
     * @param supplier Supplier function.
     * @return Response with result object and assigned status.
     */
    public static Response process(Supplier<Object> supplier) {
        try {
            return process(supplier.get());
        } catch (Exception e) {
            log.error("failed to process request", e);
            return Response.serverError().build();
        }
    }

    /**
     * Process a request with an argument and a function result.
     *
     * @param t        Function parameter.
     * @param function Function to execute.
     * @param <T>      Function parameter type.
     * @return Response with result object and assigned status.
     */
    public static <T> Response process(T t, Function<T, Object> function) {
        try {
            return process(function.apply(t));
        } catch (Exception e) {
            log.error("failed to process request", e);
            return Response.serverError().build();
        }
    }

    /**
     * Process a request with 2 arguments and a function result.
     *
     * @param t1       Function parameter 1.
     * @param t2       Function parameter 2.
     * @param function Function to execute.
     * @param <T1>     Function parameter 1 type.
     * @param <T2>     Function parameter 2 type.
     * @return Response with result object and assigned status.
     */
    public static <T1, T2> Response process(T1 t1, T2 t2, Function2<T1, T2, Object> function) {
        try {
            return process(function.apply(t1, t2));
        } catch (Exception e) {
            log.error("failed to process request", e);
            return Response.serverError().build();
        }
    }

    /**
     * Process a request with 3 arguments and a function result.
     *
     * @param t1       Function parameter 1.
     * @param t2       Function parameter 2.
     * @param t3       Function parameter 3.
     * @param function Function to execute.
     * @param <T1>     Function parameter 1 type.
     * @param <T2>     Function parameter 2 type.
     * @param <T3>     Function parameter 3 type.
     * @return Response with result object and assigned status.
     */
    public static <T1, T2, T3> Response process(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, Object> function) {
        try {
            return process(function.apply(t1, t2, t3));
        } catch (Exception e) {
            log.error("failed to process request", e);
            return Response.serverError().build();
        }
    }

    /**
     * Process result and assign proper response status.
     *
     * @param result Result object.
     * @return Response with result object and assigned status.
     */
    private static Response process(Object result) {
        if (result == null) return Response.serverError().build();
        if (result instanceof Response.Status) return Response.status((Response.Status) result).build();
        return Response.ok(result).build();
    }
}
