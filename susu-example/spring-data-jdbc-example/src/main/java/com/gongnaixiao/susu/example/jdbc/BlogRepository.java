package com.gongnaixiao.susu.example.jdbc;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    public List<Blog> findByTitle(String title);
}
