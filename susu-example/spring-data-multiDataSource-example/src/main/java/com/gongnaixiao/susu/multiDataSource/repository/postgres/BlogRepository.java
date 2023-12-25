package com.gongnaixiao.susu.multiDataSource.repository.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface BlogRepository extends CrudRepository<Blog, Long> {
    @Transactional("postgresTransactionManager")
    Optional<Blog> findById(Long id);
}
