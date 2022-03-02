package io.metadata.schoolregistration.model.audit;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    public BaseEntity() {
    }

    public abstract Long getId();

    public String toString() {
        return this.getClass().getCanonicalName() + "[id=" + this.getId() + "]";
    }

    public boolean equals(Object object) {
        if (!this.getClass().isInstance(object)) {
            return false;
        } else {
            BaseEntity other = (BaseEntity) object;
            return this.getId().equals(other.getId()) || this.getId() != null && this.getId().equals(other.getId());
        }
    }

    public int hashCode() {
        byte hash = 0;
        return hash + (this.getId() != null ? this.getId().hashCode() : 0);
    }
}