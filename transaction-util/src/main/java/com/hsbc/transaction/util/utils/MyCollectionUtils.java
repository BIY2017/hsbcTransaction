package com.hsbc.transaction.util.utils;

import com.hsbc.transaction.util.response.MyPage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * MyCollectionUtils
 *
 * @author Lei
 * @date 2025/6/22 0:33
 */
public class MyCollectionUtils {

    /**
     * list分页
     *
     * @param sourceList 待分页list
     * @param pageNo 页码
     * @param pageSize 分页大小
     * @return 分页后的list
     */
    public static <T> MyPage<T> getPage(List<T> sourceList, int pageNo, int pageSize) {
        if (sourceList == null || sourceList.isEmpty() || pageSize <= 0) {
            return (MyPage<T>) MyPage.ofEmpty();
        }
        int totalSize = sourceList.size();
        int startIndex = (pageNo - 1) * pageSize;
        if (startIndex >= totalSize || pageNo < 1) {
            return (MyPage<T>) MyPage.ofEmpty();
        }
        return MyPage.of((long) ((sourceList.size() - 1) / pageSize + 1), (long) sourceList.size(),
                pageNo, pageSize, IntStream.range(startIndex, Math.min(startIndex + pageSize, totalSize))
                        .mapToObj(sourceList::get)
                        .collect(Collectors.toList()));
    }
}
