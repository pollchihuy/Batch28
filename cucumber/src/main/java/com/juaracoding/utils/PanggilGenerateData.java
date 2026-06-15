package com.juaracoding.utils;

import com.juaracoding.utils.GenerateData;

public class PanggilGenerateData{
    public static void main(String[] args){
        GenerateData generateData = new GenerateData();
        for(    int i=0;i<10;i++){
            System.out.println(generateData.dataNamaLengkap());
        }
    }
}