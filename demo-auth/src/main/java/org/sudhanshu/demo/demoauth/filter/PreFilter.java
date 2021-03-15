package org.sudhanshu.demo.demoauth.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PreFilter
        //extends ZuulFilter
        {

    private static Logger LOGGER = LoggerFactory.getLogger(PreFilter.class);

    //@Override
    public String filterType() {
        return "pre";
    }
    //@Override
    public int filterOrder() {
        return 1;
    }
    //@Override
    public boolean shouldFilter() {
        return true;
    }
    //@Override
    public Object run() {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        // Add a custom header in the request
//        ctx.addZuulRequestHeader("Authorization",
//                request.getHeader("Authorization"));
//        log.info(String.format("%s request to %s", request.getMethod(),
//                request.getRequestURL().toString()));
        return null;
        }
}
