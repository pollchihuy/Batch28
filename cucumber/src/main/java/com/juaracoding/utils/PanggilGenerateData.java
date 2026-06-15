package com.juaracoding.utils;

public class PanggilGenerateData{
    public static void main(String[] args){
        GenerateData generateData = new GenerateData();
        for(    int i=0;i<10;i++){
            System.out.println(generateData.dataNamaLengkap());
        }
    }
}