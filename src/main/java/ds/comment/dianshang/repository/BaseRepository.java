package ds.comment.dianshang.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @Author: lkx
 * @Date: create in 16:46 2019/5/30
 */
@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T,Long>, JpaSpecificationExecutor<T> {
}
