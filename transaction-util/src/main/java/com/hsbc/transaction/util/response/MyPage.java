package com.hsbc.transaction.util.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * MyPage
 *
 * @author Lei
 * @date 2025/6/19 13:48
 */

@NoArgsConstructor
@Data
public class MyPage<T> {

    private Long totalPages;

    private Long totalRecords;

    private Integer pageNo;

    private Integer pageSize;

    private List<T> records;

    private MyPage(Long totalPages, Long totalRecords, Integer pageNo, Integer pageSize, List<T> records) {
        this.totalPages = totalPages;
        this.totalRecords = totalRecords;
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.records = records;
    }

    public static <T> MyPage<T> of (Long totalPages, Long totalRecords, Integer pageNo, Integer pageSize, List<T> records) {
        return new MyPage<>(totalPages, totalRecords, pageNo, pageSize, records);
    }

    public static MyPage<?> ofEmpty() {
        return new MyPage<>(0L, 0L, 1, 10, Collections.emptyList());
    }

    public boolean isEmpty() {
        return CollectionUtils.isEmpty(records);
    }

    public <R> MyPage<R> convert(Function<T, R> converter) {
        return new MyPage<>(totalPages, totalRecords, pageNo, pageSize, records.stream().map(converter).collect(Collectors.toList()));
    }
}
