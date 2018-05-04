package com.davewiard;

import java.util.HashMap;

public class CropRatio {

    private double totalWeight;
    private HashMap<String, Double> crops = new HashMap<>();

    public void add(String name, int cropWeight) {
        Double currentCropWeight = crops.get(name);

        if (currentCropWeight == null) {
            currentCropWeight = 0.0;
        }

        currentCropWeight += cropWeight;
        crops.put(name, currentCropWeight);

        totalWeight += cropWeight;
    }

    public double proportion(String name) {
        Double cropWeight = crops.get(name);
        if (cropWeight == null)
            return 0.0;

        return crops.get(name) / totalWeight;
    }

    public static void main(String[] args) {
        CropRatio cropRatio = new CropRatio();

//        cropRatio.add("Wheat", 4);
//        cropRatio.add("Wheat", 5);
//        cropRatio.add("Rice", 1);

        System.out.println("Fraction of wheat: " + cropRatio.proportion("Wheat"));
    }

}
