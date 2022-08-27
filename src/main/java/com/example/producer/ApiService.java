package com.example.producer;

import DTO.Data;

import java.util.List;

public interface ApiService {
    List<Data> getData(Integer limit);
}

