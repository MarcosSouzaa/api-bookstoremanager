package com.marcosantoniosouza.bookstoremanager.custominfoendpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.web.WebEndpointResponse;
import org.springframework.boot.actuate.endpoint.web.annotation.EndpointWebExtension;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;



@Component
@EndpointWebExtension(endpoint = InfoEndpoint.class)
public class infoWebPointExtension {

    @Autowired
    private InfoEndpoint delegate; //fazendo a injeção da classe InfoEndpoint e chamar o  objeto de delegate

    //Método info que vai fazer a customização dessa inmformação
    @ReadOperation //porque vamos acessar através de leitura
    public WebEndpointResponse<Map> info(){
        Map<String, Object> info = this.delegate.info();
        Integer status = getStatus(info);
        Map<String, Object> customInfo = new HashMap<>(info);
        customInfo.put("customInfo" , "handOn");
        return new WebEndpointResponse<>(customInfo, status);
    }

    private Integer getStatus(Map<String, Object> info) {
        return 200;
    }
}
