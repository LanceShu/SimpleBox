package com.example.lance.simplebox.DataBean.WeatherBean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Kiboooo on 2017/12/7.
 *
 * date":"2017-12-07",
 "info":{
 "kongtiao":[
 "较少开启",
 "您将感到很舒适，一般不需要开启空调。"
 ],
 "yundong":[
 "较不宜",
 "天气较好，但考虑天气寒冷，推荐您进行各种室内运动，若在户外运动请注意保暖并做好准备活动。"
 ],
 "ziwaixian":[
 "最弱",
 "属弱紫外线辐射天气，无需特别防护。若长期在户外，建议涂擦SPF在8-12之间的防晒护肤品。"
 ],
 "ganmao":[
 "较易发",
 "昼夜温差较大，较易发生感冒，请适当增减衣服。体质较弱的朋友请注意防护。"
 ],
 "xiche":[
 "较适宜",
 "较适宜洗车，未来一天无雨，风力较小，擦洗一新的汽车至少能保持一天。"
 ],
 "wuran":null,
 "chuanyi":[
 "较冷",
 "建议着厚外套加毛衣等服装。年老体弱者宜着大衣、呢外套加羊毛衫。"
 ]
 }
 */

public class Life {
    public String date;

    @SerializedName("info")
    private INFO Info;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public INFO getInfo() {
        return Info;
    }

    public void setInfo(INFO info) {
        Info = info;
    }

    public class INFO{
        /*下列的温馨提示为数组的形式，下标【0】：表明情况；下标【1】：相对应的建议*/

        @SerializedName("kongtiao")
        List<String> kongtiao;
        @SerializedName("yundong")
        List<String>  yundong;
        @SerializedName("ziwaixian")
        List<String>  ziwaixian;
        @SerializedName("ganmao")
        List<String>  ganmao;
        @SerializedName("xiche")
        List<String> xiche;
        @SerializedName("chuanyi")
        List<String>  chuanyi;

        public List<String> getKongtiao() {
            return kongtiao;
        }

        public void setKongtiao(List<String> kongtiao) {
            this.kongtiao = kongtiao;
        }

        public List<String> getYundong() {
            return yundong;
        }

        public void setYundong(List<String> yundong) {
            this.yundong = yundong;
        }

        public List<String> getZiwaixian() {
            return ziwaixian;
        }

        public void setZiwaixian(List<String> ziwaixian) {
            this.ziwaixian = ziwaixian;
        }

        public List<String> getGanmao() {
            return ganmao;
        }

        public void setGanmao(List<String> ganmao) {
            this.ganmao = ganmao;
        }

        public List<String> getXiche() {
            return xiche;
        }

        public void setXiche(List<String> xiche) {
            this.xiche = xiche;
        }

        public List<String> getChuanyi() {
            return chuanyi;
        }

        public void setChuanyi(List<String> chuanyi) {
            this.chuanyi = chuanyi;
        }
    }

}
