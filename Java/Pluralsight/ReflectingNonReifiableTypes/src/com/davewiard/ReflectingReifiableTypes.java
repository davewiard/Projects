package com.davewiard;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReflectingReifiableTypes {

    public static class StringList extends ArrayList<String> {

    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        Class<?> arrayList = strings.getClass();
        System.out.println(arrayList);
        System.out.println(arrayList.toString());
        System.out.println(arrayList.toGenericString());

        final TypeVariable<? extends Class<?>>[] typeParameters =
                arrayList.getTypeParameters();
        System.out.println(Arrays.toString(typeParameters));

        final Type arrayListOfString =
                (ParameterizedType) StringList.class.getGenericSuperclass();
        System.out.println(Arrays.toString(((ParameterizedType) arrayListOfString).getActualTypeArguments()));
    }
}
