package com.juaracoding.utils;

public class PanggilGenerateData{
    public static void main(String[] args){
        GenerateData generateData = new GenerateData();
        for(    int i=0;i<1000;i++){
            System.out.println(generateData.dataEmail());
        }
    }
}