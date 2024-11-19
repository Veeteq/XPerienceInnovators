package com.admiral.hackathon.customer_data.service;

import java.util.List;

public interface PolicyService<T> {

    void savePolicies(List<T> policies);
}
