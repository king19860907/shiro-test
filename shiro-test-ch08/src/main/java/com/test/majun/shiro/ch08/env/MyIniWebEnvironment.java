package com.test.majun.shiro.ch08.env;

import org.apache.shiro.util.ClassUtils;
import org.apache.shiro.web.env.IniWebEnvironment;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilter;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.FilterChainResolver;
import org.apache.shiro.web.filter.mgt.PathMatchingFilterChainResolver;

import javax.servlet.Filter;
import java.util.Arrays;

/**
 * Created by majun on 25/12/2017.
 */
public class MyIniWebEnvironment extends IniWebEnvironment {

    @Override
    protected FilterChainResolver createFilterChainResolver() {

        //在此处扩展自己的FilterChainResolver
        //1.创建FilterChainResolver
        PathMatchingFilterChainResolver filterChainResolver = new PathMatchingFilterChainResolver();

        //2.创建FilterChainManager
        DefaultFilterChainManager filterChainManager = new DefaultFilterChainManager();

        //3.注册Filter,默认的Filter
        Arrays.stream(DefaultFilter.values()).forEach(filter->{
            filterChainManager.addFilter(filter.name(), (Filter) ClassUtils.newInstance(filter.getFilterClass()));
        });

        //4.注册URL-filter的映射关系
        filterChainManager.addToChain("/login.jsp","authc");
        filterChainManager.addToChain("/unauthorized.jsp", "anon");
        filterChainManager.addToChain("/**", "authc");
        filterChainManager.addToChain("/**", "roles", "admin");

        //5.设置Filter属性
        FormAuthenticationFilter authcFilter = (FormAuthenticationFilter)filterChainManager.getFilter("authc");
        authcFilter.setLoginUrl("/login.jsp");
        RolesAuthorizationFilter rolesFilter = (RolesAuthorizationFilter)filterChainManager.getFilter("roles");
        rolesFilter.setUnauthorizedUrl("/unauthorized.jsp");

        filterChainResolver.setFilterChainManager(filterChainManager);

        return filterChainResolver;
    }
}
