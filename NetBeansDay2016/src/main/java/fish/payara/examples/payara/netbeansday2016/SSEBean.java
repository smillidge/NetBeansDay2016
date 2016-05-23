/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fish.payara.examples.payara.netbeansday2016;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedScheduledExecutorService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.sse.EventOutput;
import org.glassfish.jersey.media.sse.OutboundEvent;
import org.glassfish.jersey.media.sse.SseBroadcaster;

/**
 *
 * @author steve
 */
@Singleton
@Path("sseexample")
public class SSEBean {
    
    @Resource(name="DefaultManagedScheduledExecutorService")
    private ManagedScheduledExecutorService scheduledExecutor;    
    private SseBroadcaster broadcaster;
    
    @GET
    @Produces("text/event-stream")
    public EventOutput getEvents() {
        EventOutput newOne = new EventOutput();
        this.broadcaster.add(newOne);
        System.out.println("Added Event Output");
        return newOne;
    }
    
    
    @PostConstruct
    public void postConstruct() {
        broadcaster = new SseBroadcaster();
        scheduledExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                OutboundEvent.Builder builder =  new OutboundEvent.Builder();
                OutboundEvent event = builder.name("time").mediaType(MediaType.TEXT_PLAIN_TYPE)
                        .data(String.class,new Date().toString())
                        .build();
                System.out.println("Broadcasting Events");
                broadcaster.broadcast(event);
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
    
    
}
