/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.examples.payara.netbeansday2016;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.mvc.annotation.View;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author steve
 */
@Singleton
@Controller
@Path("mvcexample")
public class AppController {
    
    @Inject
    PersonBean bean;
    
    @Inject
    RandomNumberCache cacheBean;
    
    @Inject
    Models models;
    
    @GET
    @View(value = "/time.jsp")
    public void timePage(@QueryParam("name") String name) {
        
        if (name != null && !name.isEmpty())
            bean.setName(name);
    }
    
    @GET
    @Path("alternative")
    @View (value = "/alternative.jsp")
    public void alternative() {
    }
    
    @GET
    @Path("decide")
    public String decide(@QueryParam("alternative") boolean alternative) {
        if (alternative) {
            return "/alternative.jsp";
        } else {
            return "/default.jsp";
        }
    }
    
    @GET
    @Path("cache")
    @View(value = "/cache.jsp")
    public void cache() {
        models.put("cacheValue", cacheBean.getRandomNumber());
    }
    
    
    
}
