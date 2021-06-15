package ru.stm_labs.marvel.repositories;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import ru.stm_labs.marvel.dto.page.PageData;

import java.util.ArrayList;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<Entity>
        extends JpaRepository<Entity, Long>, JpaSpecificationExecutor<Entity>, QuerydslPredicateExecutor<Entity> {

    default Page<Entity> findAll(Predicate predicate, PageData pageData) {
        if (pageData.getPageable().isUnpaged()) {
            List list = new ArrayList<>();
            list.add(findAll(predicate, pageData.getSort()));
            return new PageImpl<>(list);
        }
        return findAll(predicate, pageData.getPageable());
    }
}
