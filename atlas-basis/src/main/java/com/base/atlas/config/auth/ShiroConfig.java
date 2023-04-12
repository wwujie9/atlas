package com.base.atlas.config.auth;

import com.base.atlas.auth.AuthProperties;
import com.base.atlas.auth.JWTAuthProperties;
import com.base.atlas.auth.Oauth2TokenFilter;
import com.base.atlas.auth.Oauth2TokenRealm;
import com.base.atlas.common.util.CollectionUtils;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author CaiJie Pang
 * @since 2023/1/31
 */
@Configuration
public class ShiroConfig {

  @Resource
  private AuthProperties authProperties;

  @Resource
  private JWTAuthProperties jwtAuthProperties;

  @Bean(name = "shiroFilter")
  public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    shiroFilterFactoryBean.setSecurityManager(securityManager);

    Map<String, Filter> filters = new HashMap<>();
    filters.put("oauth2Auth", oauth2TokenFilter());
    shiroFilterFactoryBean.setFilters(filters);
    // 全部过滤 不校验权限
    Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
    if (CollectionUtils.isNotEmpty(authProperties.getAnonPaths())) {
      for (String anonPath : authProperties.getAnonPaths()) {
        filterChainDefinitionMap.put(anonPath, "anon");
      }
    }
    // 过滤指定的url oauth2
    if (CollectionUtils.isNotEmpty(jwtAuthProperties.getAuthcPaths())) {
      for (String oauth2AuthPath : jwtAuthProperties.getAuthcPaths()) {
        filterChainDefinitionMap.put(oauth2AuthPath, "oauth2Auth");
      }
    }
    // 过滤指定的url authc
    if (CollectionUtils.isNotEmpty(authProperties.getAuthcPaths())) {
      for (String defaultAuthPath : authProperties.getAuthcPaths()) {
        filterChainDefinitionMap.put(defaultAuthPath, "authc");
      }
    }

    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

    return shiroFilterFactoryBean;
  }

  @Bean
  public SecurityManager securityManager() {
    DefaultWebSecurityManager webSecurityManager = new DefaultWebSecurityManager();
    webSecurityManager.setRealm(oauth2TokenRealm());
    return webSecurityManager;
  }

  @Bean
  public Oauth2TokenFilter oauth2TokenFilter() {
    return new Oauth2TokenFilter(jwtAuthProperties);
  }

  @Bean
  public Oauth2TokenRealm oauth2TokenRealm() {
    return new Oauth2TokenRealm();
  }
}
