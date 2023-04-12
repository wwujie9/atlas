package com.base.atlas.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author CaiJie Pang
 * @since 2023/2/5
 */
@Aspect
@Component
public class EmojiParserAspect {

  @Pointcut("@annotation(com.base.atlas.annotations.EmojiTransfer)")
  public void emojiParserDefaultPointCut() {}

  @Pointcut("target(com.baomidou.mybatisplus.extension.service.IService)")
  public void emojiParserAnnotationPointCut() {}

  @Around("emojiParserAnnotationPointCut()")
  public Object transfer(ProceedingJoinPoint point) throws Throwable {
    System.out.println("hello world");
    Object[] args =  point.getArgs();
    MethodSignature signature = (MethodSignature) point.getSignature();
    return point.proceed();
  }
}
