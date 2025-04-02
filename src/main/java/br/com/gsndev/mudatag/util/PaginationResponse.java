package br.com.gsndev.mudatag.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PaginationResponse<T> {
    private List<T> items;
    private int page;
    private int pageSize;
    private int totalPages;
    private long totalItems;

    private PaginationResponse() {}

    private PaginationResponse(List<T> items, int page, int pageSize, int totalPages, long totalItems) {
        this.items = items;
        this.page = page;
        this.pageSize = pageSize;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public static <T> PaginationResponse<T> map(Page<T> page) {
        return new PaginationResponse<>(
                page.getContent(),
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalPages(),
                page.getTotalElements()
        );
    }
}
