package todoapp.todoapp_develop.global.domain;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {
    // JPA Auditing 을 활용해서 작성일, 수정일을 자동으로 관리하기 위한 엔티티
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt; // 작성일

    @LastModifiedDate
    private LocalDateTime updatedAt; // 수정일
}
