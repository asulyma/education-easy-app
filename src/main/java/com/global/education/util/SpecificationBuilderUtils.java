package com.global.education.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpecificationBuilderUtils {

    public static boolean doubleCheckOnNotNull(Object o1, Object o2) {
        return checkOnNotNull(o1) && checkOnNotNull(o2);
    }

    public static boolean checkOnNotNull(Object o) {
        return ObjectUtils.isNotEmpty(o);
    }


}
