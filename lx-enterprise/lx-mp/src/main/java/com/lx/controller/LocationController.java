package com.lx.controller;

import com.lx.entity.Location;
import com.lx.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class LocationController {

    @Autowired
    private LocationRepository locationRepository;

    @ResponseBody
    @RequestMapping("/hello")
    public List<Location> hello() {
        return locationRepository.findAll();
    }
}