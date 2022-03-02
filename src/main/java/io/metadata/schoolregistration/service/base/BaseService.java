package io.metadata.schoolregistration.service.base;

import io.metadata.schoolregistration.exception.AppException;
import io.metadata.schoolregistration.exception.ResourceNotFoundException;
import io.metadata.schoolregistration.model.dto.base.BaseDTO;
import io.metadata.schoolregistration.repository.base.BaseRepository;
import io.metadata.schoolregistration.util.StaticApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Slf4j
@Service
public class BaseService<T extends BaseDTO, R extends BaseRepository, ID extends Serializable> {

    public T findById(Long id) throws AppException {
        R repository = StaticApplicationContext.getContext().getBean(getRGenericTypeClass());
        try {
            return (T) repository.findByIdD(id);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }

    public T save(T t) throws AppException {

        R repository = StaticApplicationContext.getContext().getBean(getRGenericTypeClass());
        try {
            return (T) repository.save(t);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }

    public void delete(ID id) throws AppException {
        R repository = StaticApplicationContext.getContext().getBean(getRGenericTypeClass());
        try {
            repository.delete(id);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }

    public List<T> findAll() throws AppException {
        R repository = StaticApplicationContext.getContext().getBean(getRGenericTypeClass());
        try {
            return repository.findAllD();
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }

    public Page<T> findAll(Pageable pageable) throws AppException {
        R repository = StaticApplicationContext.getContext().getBean(getRGenericTypeClass());
        try {
            return repository.findAllD(pageable);
        } catch (ResourceNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new ResourceNotFoundException(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new AppException(e.getMessage());
        }
    }


    private Class<?> getClassType(int i) throws ClassNotFoundException {
        String className = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[i].getTypeName();
        return Class.forName(className);
    }

    @SuppressWarnings("unchecked")
    private Class<R> getRGenericTypeClass() {
        try {
            Class<?> clazz = getClassType(1);
            return (Class<R>) clazz;
        } catch (Exception e) {
            throw new IllegalStateException("Class is not parametrized with generic type!!! Please use extends <> ");
        }
    }


}
