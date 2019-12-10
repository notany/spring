package com.aop.lamda;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：张红
 * @date ：2019/11/14 16:56
 * @description：
 * @version:
 */
public class Hello {
    String hi;
    String bye;
    /**
     * java 8 排序 & 循环输出
     *
     * @return void
     * @author: 张红
     * @date: 2019/11/14 16:59
     */


    public static void main(String[] args) {
        Field[] field = Hello.class.getDeclaredFields();
        List<Field> fields = Arrays.asList(field);
        fields.stream().map(f->f.getName()).collect(Collectors.toList()).sort(Comparator.comparing(String::toString));
        fields.forEach(System.out::println);
        List<String> s = Arrays.asList("brand_coder",
                "brand_number_code",
                "brand_lower_cn_name",
                "creator_name",
                "description",
                "per_create_time",
                "brand_classify_names",
                "brand_status",
                "oid",
                "brand_lower_en_name",
                "deleter_name",
                "modify_time",
                "brand_classifies",
                "data_specification_type",
                "delete_status",
                "data_specification_type_name",
                "app_key",
                "brand_cn_name",
                "service_key",
                "audit_status_name",
                "brand_logo",
                "brand_name",
                "per_modify_time",
                "brand_alias_name",
                "index_name",
                "brand_lower_name",
                "brand_en_name",
                "platform_id",
                "sort",
                "brand_status_name",
                "brand_href_url",
                "brand_type_name",
                "brand_type",
                "platform_brand_code",
                "brand_first_word",
                "create_time",
                "delete_time",
                "audit_status",
                "modifier_name",
                "per_version",
                "per_delete_status",
                "chain_address");

            s.sort(Comparator.comparing(String::toString));
//        s.forEach(System.out::println);
    }
}
