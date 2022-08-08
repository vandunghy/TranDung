package com.example.springboot_thymeleaf_phim.repository;

import com.example.springboot_thymeleaf_phim.entity.Phim;
import com.example.springboot_thymeleaf_phim.entity.PhimViewModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PhimSortRepository extends PagingAndSortingRepository<Phim,String> {
}
