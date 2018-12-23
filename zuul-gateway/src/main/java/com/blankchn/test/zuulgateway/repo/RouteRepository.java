package com.blankchn.test.zuulgateway.repo;

import com.blankchn.test.zuulgateway.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author BlankCHN
 * @date 2018-12-22 00:47
 */
@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
}
