package com.conan.spring.service.impl;

import com.conan.spring.service.HelloService;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public int globalException() {
        return 1 / 0;
    }
}
