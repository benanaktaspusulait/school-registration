package io.metadata.schoolregistration.repository.base;

import io.metadata.schoolregistration.exception.AppException;
import io.metadata.schoolregistration.mapper.base.BaseEntityMapper;
import io.metadata.schoolregistration.model.audit.BaseEntity;
import io.metadata.schoolregistration.model.dto.base.BaseDTO;
import io.metadata.schoolregistration.util.StaticApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity, D extends BaseDTO, ID extends Serializable, M extends BaseEntityMapper> extends JpaRepository<T, ID> {

    Logger log = LoggerFactory.getLogger(BaseRepository.class);

    default T findByIdE(ID id) throws ResourceNotFoundException {
        String typeName = ((ParameterizedType) ((Class) getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName();
        log.debug("Request to get {} : {}", typeName, id);

        Optional<T> result = this.findById(id);
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("Resource Type: " + typeName + " - Resource Id: " + id);
        }
        return result.get();
    }

    default D findByIdD(ID id) throws AppException, ClassNotFoundException {
        String mapperName = ((ParameterizedType) ((Class<?>) getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]).getActualTypeArguments()[3].getTypeName();
        BaseEntityMapper mapper = (BaseEntityMapper) StaticApplicationContext.getContext().getBean(Class.forName(mapperName));
        return (D) mapper.toDto(findByIdE(id));

    }

    @Transactional
    default D save(D dto) throws ClassNotFoundException {
        String typeName = ((ParameterizedType) ((Class<?>) getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]).getActualTypeArguments()[3].getTypeName();
        BaseEntityMapper mapper = (BaseEntityMapper) StaticApplicationContext.getContext().getBean(Class.forName(typeName));
        log.debug("Request to save D : {}", dto);
        T t;
        if (dto.getId() != null) {
            t = findByIdE((ID) dto.getId());
            t = (T) mapper.toEntity(dto, t);
        } else {
            t = (T) mapper.toEntity(dto);
        }
        T result = save(t);
        return (D) mapper.toDto(result);
    }

    @Transactional
    default void delete(ID id) throws AppException {
        this.deleteById(id);
    }

    default List<D> findAllD() throws AppException, ClassNotFoundException {
        String typeName = ((ParameterizedType) ((Class<?>) getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]).getActualTypeArguments()[3].getTypeName();
        BaseEntityMapper mapper = (BaseEntityMapper) StaticApplicationContext.getContext().getBean(Class.forName(typeName));
        return mapper.toDto(this.findAll());
    }

    default Page<BaseDTO> findAllD(Pageable page) throws AppException, ClassNotFoundException {
        String typeName = ((ParameterizedType) ((Class<?>) getClass().getGenericInterfaces()[0]).getGenericInterfaces()[0]).getActualTypeArguments()[3].getTypeName();
        BaseEntityMapper mapper = (BaseEntityMapper) StaticApplicationContext.getContext().getBean(Class.forName(typeName));
        return this.findAll(page).map(mapper::toDto);
    }

}