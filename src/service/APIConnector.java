
package service;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * Representa la conexión a una API y sus operaciones.
 * @author Emmanuel Silano
 */
public class APIConnector {
  private final String urlString;
  /**
   * Crea una instancia de una API.
   * @param urlString Es una URL o endpoint de la API.
   */
  public APIConnector(String urlString) {
    this.urlString = urlString;
  }
  /**
   * Crea un JSONArray.
   * @param query intruccion.
   * @return Retorna un JSONArray.
   */
  public JSONArray getJSONArray(String query){
    try {
      URL url = new URL(urlString+query);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
      int responseCode = conn.getResponseCode();
      
      if(responseCode != 200){
        throw new RuntimeException("HttpResponseCode:" + responseCode);
      }else{
        StringBuilder informationString = new StringBuilder();
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()){
          informationString.append(sc.nextLine());
        }
        sc.close();
        JSONParser parse = new JSONParser();
        return (JSONArray) parse.parse(String.valueOf(informationString));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
  /**
   * Crea un JSONObject.
   * @param query instruccion.
   * @return Retorna un JSONObject.
   */
  public JSONObject getJSONObject(String query){
    try {
      URL url = new URL(urlString + query);
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.connect();
      
      int responseCode = conn.getResponseCode();
      if(responseCode != 200){
        throw new RuntimeException("HttpResponse: " + responseCode);
      }else{
        StringBuilder informationString = new StringBuilder();
        Scanner sc = new Scanner(url.openStream());
        while(sc.hasNext()){
          informationString.append(sc.nextLine());
        }
        sc.close();
        JSONParser parse = new JSONParser();
        return (JSONObject) parse.parse(String.valueOf(informationString));
      }
    
    } catch (Exception e) {
     e.printStackTrace();
    }
    return null;
  }
  /**
   * Permite obtener una lista de los código de monedas.
   * @return Retorna una lista de códigos de las monedas.
   */
  public ArrayList<String> getListDivisa(){
      JSONObject jsonObj = getJSONObject("");
      JSONObject jsonRate = (JSONObject) jsonObj.get("rates");
      Set<String> keysCollect = jsonRate.keySet();
      String[] keyArray = keysCollect.toArray(new String[keysCollect.size()]);
      ArrayList<String> listKeys = new ArrayList();
     Collections.addAll(listKeys, keyArray);
     return listKeys;
  }
  /**
   * Obtiene un Map con los códigos y sus respectiva descripción.
   * @return Retorna Map de código de moneda y descripción.
   */
  public Map<String, String> getMapDivisa(){
      JSONObject jsonObject = getJSONObject("");
      JSONObject jsonSymbol = (JSONObject) jsonObject.get("symbols");
      Map<String, JSONObject> mapJson = (Map<String, JSONObject>) jsonSymbol;
      Map<String, String> mapStr = new HashMap<>();
      JSONObject jObj;
      for(Map.Entry<String, JSONObject> m : mapJson.entrySet()){
          jObj = m.getValue();
          mapStr.put(jObj.get("code").toString(), jObj.get("description").toString());
      }
      return mapStr;
  }
  /**
   * Permite obtener el resultado de la conversión de las divisas.
   * @param fromDivisa Código de la moneda que se quiere convertir
   * @param toDivisa Código de la moneda a la cual se desea convertir
   * @param divisaOne Cantidad a convertir
   * @return Retorna el resultado de la conversion monetaria
   */
  public String getResultConverter(String fromDivisa, String toDivisa, Double divisaOne){
      
      String query = "convert?from=" + fromDivisa + "&to=" + toDivisa + "&amount=" + divisaOne + "&places=2";
      String result;
      JSONObject jsonObject = getJSONObject(query);
      result = jsonObject.get("result").toString();
      return result;
  }
  
}

