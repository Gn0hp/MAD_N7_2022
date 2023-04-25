package com.example.myapplication1.utils;

import com.example.myapplication1.models.Attributes;
import com.example.myapplication1.models.Cart;
import com.example.myapplication1.models.Product;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class Api {
    private static String ip = "192.168.1.147";
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build();;

     public static ArrayList<Product> get_all_product(){
         ArrayList<Product> list = new ArrayList();
         String url = "http://"+ip+":6603/product/get_by/?current=1&page_size=10";
         HttpRequest httpRequest = new HttpRequest(url);
         String data = httpRequest.get();
         try {
             JSONObject jsonObject = new JSONObject(data);

//             list = (ArrayList) jsonObject.get("results");
             JSONArray jsonArray = (JSONArray) jsonObject.get("results");
            try{
             for (int i = 0; i < jsonArray.length(); i++) {
//                 arr.getJSONObject(i);
                 JSONObject jsonObject1 = (JSONObject)  jsonArray.get(i);
                 JSONArray jsonArrayAttribute = (JSONArray) jsonObject1.get("attribute");
                 ArrayList<Attributes> listAttribute = new ArrayList<>();
                 for (int j = 0; j < jsonArrayAttribute.length(); j++) {
                     JSONObject jsonObject2 = (JSONObject)  jsonArrayAttribute.get(j);
                     Attributes attributes = new Attributes((int)jsonObject2.get("id"),(String)jsonObject2.get("name"),(String) jsonObject2.get("value"));
                    listAttribute.add(attributes);
                 }
                 Product product = new Product((int)jsonObject1.get("id"),(String) jsonObject1.get("name"),(String) jsonObject1.get("description"),(int)jsonObject1.get("count"),(int)jsonObject1.get("price"),listAttribute);
                 list.add(product);
             }

            } catch (Exception e){
                e.printStackTrace();
            }
         }catch (Exception err){
             err.printStackTrace();
         }
         return list;
     }
     public static ArrayList<Product> get_product_in_cart(int user){
         ArrayList<Product> list = new ArrayList();
         String url = "http://"+ip+":6603/cart/get_by/?user="+user+"&current=1&page_size=10";
         HttpRequest httpRequest = new HttpRequest(url);
         String data = httpRequest.get();

         try {
             JSONObject jsonObject = new JSONObject(data);

//             list = (ArrayList) jsonObject.get("results");
             JSONArray jsonArray = (JSONArray) jsonObject.get("results");
             System.out.println(jsonArray);
             try{
                 for (int i = 0; i < jsonArray.length(); i++) {
//                 arr.getJSONObject(i);
                     JSONObject jsonObject3 = (JSONObject)  jsonArray.get(i);
                     JSONObject jsonObject1 = (JSONObject)  jsonObject3.get("product");
                     System.out.println(jsonObject1);
                     JSONArray jsonArrayAttribute = (JSONArray) jsonObject1.get("attribute");
                     ArrayList<Attributes> listAttribute = new ArrayList<>();
                     for (int j = 0; j < jsonArrayAttribute.length(); j++) {
                         JSONObject jsonObject2 = (JSONObject)  jsonArrayAttribute.get(j);

                         Attributes attributes = new Attributes((int)jsonObject2.get("id"),(String)jsonObject2.get("name"),(String) jsonObject2.get("value"));
                         listAttribute.add(attributes);
                     }
                     Product product = new Product((int)jsonObject1.get("id"),(String) jsonObject1.get("name"),(String) jsonObject1.get("description"),(int)jsonObject1.get("count"),(int)jsonObject1.get("price"),listAttribute);
                     list.add(product);
                 }

             } catch (Exception e){
                 e.printStackTrace();
             }
         }catch (Exception err){
             err.printStackTrace();
         }
         return list;
     }

    public static String post_cart(int product, int cart){
        String url = "http://"+ip+":6603/cart_product/create_by/?quantity=1&cart="+cart+"&product="+product+"";
        HttpRequest httpRequest = new HttpRequest(url);
        String data = httpRequest.get();
        return  data;
    }
    public static String call_ai(String age, String bmi, String glucose){
        String url = "http://"+ip+":6603/ai/tieuduong/?age="+age+"&bmi="+bmi+"&glucose="+glucose+"";
        HttpRequest httpRequest = new HttpRequest(url);
        String data = httpRequest.get();
        return  data;
    }
}
