/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.examples.payara.netbeansday2016;

import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.annotation.Controller;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

/**
 *
 * @author steve
 */
@Controller
@Path("mvcexample")
public class AppController {
    
    @Inject
    PersonBean bean;
    
    @Inject
    Models models;
    
    @GET
    public String timePage(@QueryParam("name") String name) {
        
        if (name != null && !name.isEmpty())
            bean.setName(name);
        return "/time.jsp";
    }
    
}
