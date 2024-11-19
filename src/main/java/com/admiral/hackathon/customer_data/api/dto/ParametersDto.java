package com.admiral.hackathon.customer_data.api.dto;

public class ParametersDto {
    public String endpoint;
    public String index_name;
    public String semantic_configuration;
    public String query_type;
    //public FieldsMappingDto fields_mapping;
    public boolean in_scope;
    public String role_information;
    public Object filter;
    public int strictness;
    public int top_n_documents;
    public AuthenticationDto authentication;

}
