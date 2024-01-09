package com.gongnaixiao.susu.multiDataSource.repository.postgres;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional("postgresTransactionManager")
public interface BlogRepository extends CrudRepository<Blog, Long> {
}
