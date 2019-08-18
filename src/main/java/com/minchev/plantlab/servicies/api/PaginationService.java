package com.minchev.plantlab.servicies.api;



import org.springframework.data.domain.PageRequest;

import java.awt.print.Pageable;
import java.util.List;

public interface PaginationService {
    PageRequest createPageRequest(Integer page, String sort);
    List<Integer> getPagingNumber();
}
