package com.btdarcy.wirelessamp;

public class MyPreset {
    private String volume, tone, Pitch;

    public MyPreset(){

    }

    public MyPreset(String volume, String tone){
        this.volume = volume;
        this.tone = tone;
    }

    public String getVolume() {
        return volume;
    }

    public String getTone() {
        return tone;
    }



    public void setvolume(String volume) {
        volume = volume;
    }

    public void settone(String tone) {
        tone = tone;
    }

 /*   public String getPitch() {
        return Pitch;
    }
    public void setPitch(String pitch) {
        Pitch = pitch;
    }
*/


}
