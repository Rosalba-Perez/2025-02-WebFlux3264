package com.example.demo.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String correoElectronico;

    public JwtResponse() {}

    public JwtResponse(String token, String correoElectronico) {
        this.token = token;
        this.correoElectronico = correoElectronico;
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }
}