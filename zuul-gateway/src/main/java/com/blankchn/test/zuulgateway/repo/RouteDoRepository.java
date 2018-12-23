package com.blankchn.test.zuulgateway.repo;

import com.blankchn.test.zuulgateway.model.Route;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties.ZuulRoute;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author BlankCHN
 * @date 2018-12-22 00:54
 */
@Repository
public class RouteDoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Map<String, ZuulRoute> getProperties(){
        Map<String, ZuulRoute> routes = new LinkedHashMap<>();
        List<Route> list = mongoTemplate.findAll(Route.class);
        list.forEach(route -> {
            ZuulRoute zuulRoute = new ZuulRoute();
            BeanUtils.copyProperties(route,zuulRoute);
            routes.put(zuulRoute.getPath(),zuulRoute);
        });
        return routes;
    }

    public boolean exists(Route route){
        Query query = Query.query(Criteria.where("path").is(route.getPath()));
        Route routeResult = mongoTemplate.findOne(query,Route.class);
        if(routeResult!=null){
            return true;
        }

        return false;
    }

}
