package com.roman.Views.controller;

import com.roman.Views.dto.ViewsResponse;
import com.roman.Views.services.ViewsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ViewsController {
    private final ViewsServices viewsServices;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ViewsResponse> getMaidData(){
        return  viewsServices.getMaidData();
    }
}
