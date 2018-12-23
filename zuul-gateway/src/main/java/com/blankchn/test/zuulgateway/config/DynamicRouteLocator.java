package com.blankchn.test.zuulgateway.config;

import com.blankchn.test.zuulgateway.repo.RouteDoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.RefreshableRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author BlankCHN
 * @date 2018-12-22 01:12
 */
public class DynamicRouteLocator extends SimpleRouteLocator implements RefreshableRouteLocator {

    @Autowired
    private ZuulProperties properties;

    @Autowired
    private RouteDoRepository routeDoRepository;

    public DynamicRouteLocator(String servletPath, ZuulProperties properties) {
        super(servletPath, properties);
        this.properties = properties;
    }

    @Override
    public void refresh() {
        doRefresh();
    }

    @Override
    protected Map<String, ZuulProperties.ZuulRoute> locateRoutes() {
        LinkedHashMap<String, ZuulProperties.ZuulRoute> routesMap = new LinkedHashMap<>();
        routesMap.putAll(super.locateRoutes());
        routesMap.putAll(routeDoRepository.getProperties());
        LinkedHashMap<String, ZuulProperties.ZuulRoute> values = new LinkedHashMap<>();
        routesMap.forEach((key, value) -> {
            System.out.println(key+"\n---"+value.toString());
            String path = key;
            if (!path.startsWith("/")) {
                path = "/" + path;
            }
            if (StringUtils.hasText(this.properties.getPrefix())) {
                path = this.properties.getPrefix() + path;
                if (!path.startsWith("/")) {
                    path = "/" + path;
                }
            }
            values.put(path, value);
        });
        return values;
    }
}
