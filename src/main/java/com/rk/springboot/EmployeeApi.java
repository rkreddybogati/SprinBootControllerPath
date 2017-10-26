package com.rk.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * Created by srk on 26/10/17.
 */
public interface EmployeeApi {

    @GetMapping(value = "/all1")
    @ResponseBody
    List<String> getAllEmployeeNames();

    @GetMapping("/{priority}")
    @ResponseBody
    String getFirstEmployeeNames(@PathVariable("priority")  String priority);

}
