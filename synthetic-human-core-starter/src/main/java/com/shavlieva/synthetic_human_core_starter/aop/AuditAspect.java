package com.shavlieva.synthetic_human_core_starter.aop;

import com.shavlieva.synthetic_human_core_starter.service.AuditService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class AuditAspect {
    private final AuditService auditService;
    @Pointcut("@annotation(com.example.bishop.aop.WeylandWatchingYou)")
    public void weylandAuditMethods() {
    }

    @AfterReturning(pointcut = "weylandAuditMethods()", returning = "result")
    public void logMethodCall(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        auditService.audit(methodName, args, result);
    }
}
