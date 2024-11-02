package com.memo.entity;





import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    public void onPrePersist(BaseEntity baseEntity) {



        final Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        baseEntity.insertDateTime = LocalDateTime.now();
        baseEntity.lastUpdateDateTime = LocalDateTime.now();

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            baseEntity.insertUserId = principal.getId();
            baseEntity.lastUpdateUserId = principal.getId();
        }
    }

    @PreUpdate
    public void onPreUpdate(BaseEntity baseEntity) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.lastUpdateDateTime = LocalDateTime.now();

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {
            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            baseEntity.lastUpdateUserId = principal.getId();
        }
    }
}